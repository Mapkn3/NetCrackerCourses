/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sql;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikolay
 */
public class BooksSQLController extends SQLController
{

    public boolean insert(String title, String[] authors, Date date, int pages, int sectionID)
    {
        try
        {

            CallableStatement statement = LibraryDataSource
                    .getInstance()
                    .getConnection()
                    .prepareCall("{? = call Book_Insert(?, ?, ?, ?)}");
            statement.setString(2, title);
            statement.setDate(3, date);
            statement.setInt(4, pages);
            statement.setInt(5, sectionID);
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.execute();
            int book_id = statement.getInt(1);
            for (String author : authors)
            {
                executeSQL("call AUTHORS_BOOKS_INSERT(?, ?)", author, book_id);
            }
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean update(int id, String title, Date date, int pages, int sectionID)
    {
        try
        {
            executeSQL(
                    "call Book_Update(?, ?, ?, ?, ?)",
                    id,
                    title,
                    date,
                    pages,
                    sectionID
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public void updateTitle(int id, String title)
    {
        try
        {
            executeSQL(
                    "call Book_Title_Update(?, ?)",
                    id,
                    title
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateDate(int id, Date date)
    {
        try
        {
            executeSQL(
                    "call Book_Date_Update(?, ?)",
                    id,
                    date
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updatePages(int id, int pages)
    {
        try
        {
            executeSQL(
                    "call Book_Pages_Update(?, ?)",
                    id,
                    pages
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateSection(int id, int sectionID)
    {
        try
        {
            executeSQL(
                    "call Book_Section_Update(?, ?)",
                    id,
                    sectionID
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByID(int bookID)
    {
        try
        {
            executeSQL(
                    "call Book_Delete(?)",
                    bookID
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public List<String[]> select()
    {

        List<String[]> list = new LinkedList<>();
        try
        {
            ResultSet resultSet1 = executeSQL("SELECT b.book_id, b.title, b.pub_date, b.pages, s.name "
                    + "FROM BOOKS b JOIN sections s ON b.section_id = s.section_id");
            while (resultSet1.next())
            {
                String[] book = new String[6];
                book[0] = resultSet1.getInt(1) + "";
                book[1] = resultSet1.getString(2);
                CallableStatement statement = LibraryDataSource
                        .getInstance()
                        .getConnection()
                        .prepareCall("{? = call GET_AUTHORS(?)}");
                statement.setInt(2, resultSet1.getInt(1));
                statement.registerOutParameter(1, java.sql.Types.VARCHAR);
                statement.execute();
                book[2] = statement.getString(1);
                book[3] = resultSet1.getDate(3).toString();
                book[4] = resultSet1.getInt(4) + "";
                book[5] = resultSet1.getString(5);
                list.add(book);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return list;
    }

    public void delete()
    {
        try
        {
            executeSQL("DELETE FROM books");
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public String[] selectByID(int id)
    {
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * "
                    + "FROM BOOKS "
                    + "WHERE book_id = ?",
                    id
            );
            resultSet.next();
            String[] book = new String[6];
            book[0] = resultSet.getInt(1) + "";
            book[1] = resultSet.getString(2);
            ResultSet rs = executeSQL("SELECT author_id FROM authors_books WHERE book_id = ?", book[0]);
            book[2] = "";
            while (rs.next())
            {
                book[2] += rs.getInt(1) + " ";
            }
            book[3] = resultSet.getDate(3).toString();
            book[4] = resultSet.getInt(4) + "";
            book[5] = resultSet.getInt(5) + "";
            return book;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public List<String[]> selectAvailableBySection(int section_id)
    {

        List<String[]> list = new LinkedList<>();
        try
        {
            ResultSet resultSet1 = executeSQL("SELECT * FROM BOOKS WHERE section_id = ?", section_id);
            while (resultSet1.next())
            {
                ResultSet rs = executeSQL(
                        "SELECT COUNT(inventory_number) FROM exemplars WHERE book_id = ? AND user_id IS NULL",
                        resultSet1.getInt(1)
                );
                rs.next();
                int count = rs.getInt(1);
                if (count <= 0)
                {
                    break;
                }
                String[] book = new String[6];
                book[0] = resultSet1.getInt(1) + "";
                book[1] = resultSet1.getString(2);
                CallableStatement statement = LibraryDataSource
                        .getInstance()
                        .getConnection()
                        .prepareCall("{? = call GET_AUTHORS(?)}");
                statement.setInt(2, resultSet1.getInt(1));
                statement.registerOutParameter(1, java.sql.Types.VARCHAR);
                statement.execute();
                book[2] = statement.getString(1);
                book[3] = resultSet1.getDate(3).toString();
                book[4] = resultSet1.getInt(4) + "";
                book[5] = count + "";
                list.add(book);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return list;
    }
}
