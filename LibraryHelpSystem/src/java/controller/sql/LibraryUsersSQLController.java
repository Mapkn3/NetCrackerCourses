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
public class LibraryUsersSQLController extends SQLController
{

    public boolean insert(String login, String password, String firstname, String lastname, String surname, int typeID)
    {
        try
        {
            executeSQL(
                    "call Library_User_Insert(?, ?, ?, ?, ?, ?)",
                    login,
                    password,
                    firstname,
                    lastname,
                    surname,
                    typeID
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean update(int userID, String login, String password, String firstname, String lastname, String surname, int typeID)
    {
        try
        {
            executeSQL(
                    "call Library_User_Update(?, ?, ?, ?, ?, ?, ?)",
                    userID,
                    login,
                    password,
                    firstname,
                    lastname,
                    surname,
                    typeID
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public void updateLogin(int userID, String login)
    {
        try
        {
            executeSQL(
                    "call Library_User_Login_Update(?, ?)",
                    userID,
                    login
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updatePassword(int userID, String password)
    {
        try
        {
            executeSQL(
                    "call Library_User_Password_Update(?, ?)",
                    userID,
                    password
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateFirstName(int userID, String firstname)
    {
        try
        {
            executeSQL(
                    "call Library_User_First_Name_Update(?, ?)",
                    userID,
                    firstname
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateLastName(int userID, String lastname)
    {
        try
        {
            executeSQL(
                    "call Library_User_Last_Name_Update(?, ?)",
                    userID,
                    lastname
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateSurname(int userID, String surname)
    {
        try
        {
            executeSQL(
                    "call Library_User_Surname_Update(?, ?, ?, ?, ?, ?, ?)",
                    userID,
                    surname
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateTypeID(int userID, int typeID)
    {
        try
        {
            executeSQL(
                    "call Library_User_Type_Update(?, ?, ?, ?, ?, ?, ?)",
                    userID,
                    typeID
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByID(int userID)
    {
        try
        {
            executeSQL(
                    "call Library_User_Delete(?)",
                    userID
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
                    "SELECT u.user_id, u.login, u.password, u.first_name, u.last_name, u.surname, t.name "
                    + "FROM library_users u "
                    + "JOIN user_types t ON u.type_id = t.type_id"
            );
            while (resultSet.next())
            {
                String[] user = new String[7];
                user[0] = resultSet.getInt(1) + "";
                user[1] = resultSet.getString(2);
                user[2] = resultSet.getString(3);
                user[3] = resultSet.getString(4);
                user[4] = resultSet.getString(5);
                user[5] = resultSet.getString(6);
                user[6] = resultSet.getString(7);
                list.add(user);
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
            executeSQL(
                    "DELETE FROM library_users"
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public String[] selectByID(int userID)
    {
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * FROM library_users WHERE user_id = ?",
                    userID
            );
            resultSet.next();
            String[] user = new String[7];
            user[0] = resultSet.getInt(1) + "";
            user[1] = resultSet.getString(2);
            user[2] = resultSet.getString(3);
            user[3] = resultSet.getString(4);
            user[4] = resultSet.getString(5);
            user[5] = resultSet.getString(6);
            user[6] = resultSet.getInt(7) + "";
            return user;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public String[] selectByLogin(String login)
    {
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * FROM library_users WHERE login = ?",
                    login
            );
            resultSet.next();
            String[] user = new String[7];
            user[0] = resultSet.getInt(1) + "";
            user[1] = resultSet.getString(2);
            user[2] = resultSet.getString(3);
            user[3] = resultSet.getString(4);
            user[4] = resultSet.getString(5);
            user[5] = resultSet.getString(6);
            user[6] = resultSet.getInt(7) + "";
            return user;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
}
