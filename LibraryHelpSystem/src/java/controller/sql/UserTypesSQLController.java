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
public class UserTypesSQLController extends SQLController
{
    public void insert(String name)
    {
        try
        {
            executeSQL(
                    "call User_Type_Insert(?)",
                    name
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateName(int typeID, String name)
    {
        try
        {
            executeSQL(
                    "call User_Type_Update(?, ?)",
                    typeID,
                    name
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByID(int typeID)
    {
        try
        {
            executeSQL(
                    "call User_Type_Delete(?)",
                    typeID
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
            ResultSet resultSet = executeSQL("SELECT * FROM user_types");
            while (resultSet.next())
            {
                String[] exemplar = new String[2];
                exemplar[0] = resultSet.getInt(1) + "";
                exemplar[1] = resultSet.getString(2);
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
            executeSQL("DELETE FROM user_types");
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public String[] selectByID(int typeID)
    {
        try
        {
            ResultSet resultSet = executeSQL(
                    "SELECT * FROM user_types WHERE type_id = ?",
                    typeID
            );
            resultSet.next();
            String[] exemplar = new String[2];
            exemplar[0] = resultSet.getInt(1) + "";
            exemplar[1] = resultSet.getString(2);
            return exemplar;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
}
