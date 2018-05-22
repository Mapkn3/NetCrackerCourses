/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikolay
 */
public class AuthorsBooksSQLController extends SQLController
{
    public void insert(int authorID, int bookID)
    {
        try
        {
            executeSQL(
                    "call Authors_Books_Insert(?, ?)", 
                    authorID, 
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
            ResultSet resultSet = executeSQL("SELECT * FROM authors_books");
            while (resultSet.next())
            {
                list.add(new String[]
                {
                    resultSet.getInt(1) + "", resultSet.getInt(2) + ""
                });
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
            executeSQL("DELETE FROM authors_books");
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByAuthor(int authorID)
    {
        try
        {
            executeSQL("DELETE FROM authors_books WHERE author_id = ?", authorID);
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByBook(int bookID)
    {
        try
        {
            executeSQL("DELETE FROM authors_books WHERE book_id = ?", bookID);
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public List<String[]> selectByAuthorID(int authorID)
    {
        List<String[]> list = new LinkedList<>();
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * FROM authors_books WHERE author_id = ?",
                    authorID
            );
            while (resultSet.next())
            {
                list.add(new String[]
                {
                    resultSet.getInt(1) + "", resultSet.getInt(2) + ""
                });
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return list;
    }

    public List<String[]> selectByBookID(int bookID)
    {
        List<String[]> list = new LinkedList<>();
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * FROM authors_books WHERE book_id = ?",
                    bookID
            );
            while (resultSet.next())
            {
                list.add(new String[]
                {
                    resultSet.getInt(1) + "", resultSet.getInt(2) + ""
                });
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return list;
    }
}
