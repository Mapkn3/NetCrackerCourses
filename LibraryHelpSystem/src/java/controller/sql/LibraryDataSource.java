/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Nikolay
 */
public class LibraryDataSource
{

    private static LibraryDataSource instance;
    private OracleDataSource dataSource;

    private LibraryDataSource() throws SQLException
    {
        Locale.setDefault(Locale.ENGLISH);
        dataSource = new OracleDataSource();
        dataSource.setUser("markiz");
        dataSource.setPassword("25031996");
        dataSource.setDriverType("thin");
        dataSource.setDatabaseName("xe");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(1521);
    }

    public static LibraryDataSource getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new LibraryDataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
}
