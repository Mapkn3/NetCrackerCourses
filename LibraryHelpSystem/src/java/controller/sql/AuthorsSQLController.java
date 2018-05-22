/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sql;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikolay
 */
public class AuthorsSQLController extends SQLController
{
    public boolean insert(String firstname, String lastname, String surname, Date birthDate)
    {
        try
        {
            executeSQL(
                    "call Author_Insert(?, ?, ?, ?)",
                    firstname,
                    lastname,
                    surname,
                    birthDate
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean update(int authorID, String firstname, String lastname, String surname, Date birthDate)
    {
        try
        {
            executeSQL(
                    "call Author_Update(?, ?, ?, ?, ?)",
                    authorID,
                    firstname,
                    lastname,
                    surname,
                    birthDate
            );
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public void updateFirstName(int authorID, String firstname)
    {
        try
        {
            executeSQL(
                    "call Author_First_Name_Update(?, ?)",
                    authorID,
                    firstname
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateLastName(int authorID, String lastname)
    {
        try
        {
            executeSQL(
                    "call Author_Last_Name_Update(?, ?)",
                    authorID,
                    lastname
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateSurname(int authorID, String surname)
    {
        try
        {
            executeSQL(
                    "call Author_Surname_Update(?, ?)",
                    authorID,
                    surname
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateBirthDate(int authorID, Date birthDate)
    {
        try
        {
            executeSQL(
                    "call Author_Update(?, ?)",
                    authorID,
                    birthDate
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByID(int authorID)
    {
        try
        {
            executeSQL(
                    "call Author_Delete(?)",
                    authorID
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
            ResultSet resultSet = executeSQL("SELECT * FROM authors");
            while (resultSet.next())
            {
                String[] author = new String[5];
                author[0] = resultSet.getInt(1) + "";
                author[1] = resultSet.getString(2);
                author[2] = resultSet.getString(3);
                author[3] = resultSet.getString(4);
                author[4] = resultSet.getDate(5).toString();
                list.add(author);
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
            executeSQL("DELETE FROM authors");
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public String[] selectByID(int authorID)
    {
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * FROM authors WHERE author_id = ?",
                    authorID
            );
            resultSet.next();
            String[] author = new String[5];
            author[0] = resultSet.getInt(1) + "";
            author[1] = resultSet.getString(2);
            author[2] = resultSet.getString(3);
            author[3] = resultSet.getString(4);
            author[4] = resultSet.getDate(5).toString();
            return author;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
}
