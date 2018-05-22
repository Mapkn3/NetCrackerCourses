/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nikolay
 */
public abstract class SQLController
{
    protected ResultSet executeSQL(String sql, Object ... params) throws SQLException
    {
        StringBuilder str = new StringBuilder(sql);
        int counter = 0;
        while (str.lastIndexOf("?") != -1)
        {
            str = str.delete(str.indexOf("?"), str.indexOf("?") + 3);
            counter++;
        }
        if (params.length != counter)
        {
            throw new IllegalArgumentException();
        }
        PreparedStatement statement = LibraryDataSource.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++)
        {
            Object param = params[i];
            if (param instanceof Integer)
            {
                statement.setInt(i + 1, (int) param);
            } else if (param instanceof String)
            {
                statement.setString(i + 1, (String) param);
            } else if (param instanceof Date)
            {
                statement.setDate(i + 1, (Date) param);
            }
        }
        return statement.executeQuery();
    }
    
}
