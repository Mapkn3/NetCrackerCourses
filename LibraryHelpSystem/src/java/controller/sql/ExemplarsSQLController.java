/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikolay
 */
public class ExemplarsSQLController extends SQLController
{

    public boolean insert(int inventoryNumber, int bookID, int userID)
    {
        try
        {
            executeSQL(
                    "call Exemplar_Insert(?, ?, ?)",
                    inventoryNumber,
                    bookID,
                    userID
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean insert(int inventoryNumber, int bookID)
    {
        try
        {
            executeSQL(
                    "INSERT INTO exemplars VALUES(?, ?, NULL)",
                    inventoryNumber,
                    bookID
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean update(int inventoryNumber, int bookID, int userID)
    {
        try
        {
            executeSQL(
                    "call Exemplar_Update(?, ?, ?)",
                    inventoryNumber,
                    bookID,
                    userID
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateBookID(int inventoryNumber, int bookID)
    {
        try
        {
            executeSQL(
                    "call Exemplar_Book_Update(?, ?)",
                    inventoryNumber,
                    bookID
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public void clearUserID(int inventoryNumber)
    {
        try
        {
            executeSQL(
                    "call Exemplar_User_Id_Delete(?)",
                    inventoryNumber
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateUserID(int inventoryNumber, int userID)
    {
        try
        {
            executeSQL(
                    "call Exemplar_User_Id_Update(?, ?)",
                    inventoryNumber,
                    userID
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByInventoryNumber(int inventoryNumber)
    {
        try
        {
            executeSQL(
                    "call Exemplar_Delete(?)",
                    inventoryNumber
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
            ResultSet resultSet = executeSQL(
                    "SELECT e.inventory_number, b.title, NVL(TO_CHAR(e.user_id), 'нет')\n"
                    + "FROM exemplars e\n"
                    + "JOIN books b\n"
                    + "ON b.book_id = e.book_id"
            );
            while (resultSet.next())
            {
                String[] exemplar = new String[3];
                exemplar[0] = resultSet.getInt(1) + "";
                exemplar[1] = resultSet.getString(2);
                String user = resultSet.getString(3);
                if (!user.equals("нет"))
                {
                    ResultSet rs = executeSQL(
                            "SELECT login FROM library_users "
                            + "WHERE user_id = ?", user
                    );
                    rs.next();
                    exemplar[2] = rs.getString(1);
                } else
                {
                    exemplar[2] = user;
                }
                list.add(exemplar);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return list;
    }

    public List<String[]> selectByUser(int userID)
    {
        List<String[]> list = new LinkedList<>();
        try
        {
            String sql = "SELECT INVENTORY_NUMBER, exemplars.BOOK_ID, TITLE, PUB_DATE, PAGES \n"
                    + "FROM exemplars\n"
                    + "JOIN books ON exemplars.book_id = books.book_id\n"
                    + "WHERE exemplars.USER_ID = ?";
            ResultSet resultSet = executeSQL(sql, userID);
            while (resultSet.next())
            {
                String[] exemplar = new String[5];
                exemplar[0] = resultSet.getInt(1) + "";
                exemplar[1] = resultSet.getString(3);
                CallableStatement statement = LibraryDataSource
                        .getInstance()
                        .getConnection()
                        .prepareCall("{? = call GET_AUTHORS(?)}");
                statement.setInt(2, resultSet.getInt(2));
                statement.registerOutParameter(1, java.sql.Types.VARCHAR);
                statement.execute();
                exemplar[2] = statement.getString(1);
                exemplar[3] = resultSet.getDate(4).toString();
                exemplar[4] = resultSet.getInt(5) + "";
                list.add(exemplar);
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
            executeSQL("DELETE FROM exemplars");
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public String[] selectByInventoryNumber(int inventoryNumber)
    {
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * FROM exemplars WHERE inventory_number = ?",
                    inventoryNumber
            );
            resultSet.next();
            String[] exemplar = new String[3];
            exemplar[0] = resultSet.getInt(1) + "";
            exemplar[1] = resultSet.getInt(2) + "";
            exemplar[2] = resultSet.getInt(3) + "";
            return exemplar;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public int getAvailableExemplar(int bookID)
    {
        int result = -1;
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT inventory_number FROM exemplars WHERE book_id = ? AND user_id IS NULL",
                    bookID
            );
            resultSet.next();
            result = resultSet.getInt(1);
            return result;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return result;
    }
}
