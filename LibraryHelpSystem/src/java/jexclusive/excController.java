package jexclusive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.Date;

/**
 *
 * @author Kondrat
 */
public class excController {
    Connection conn;
    private OracleDataSource dataSource;
    PreparedStatement preStat;
    ResultSet result;
    
    public excController(String user, String password) throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        
        dataSource = new OracleDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverType("thin");
        dataSource.setDatabaseName("xe");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(1521);
        conn = dataSource.getConnection();
    }
    public List<String> allUsers() {
        List<String> users = new ArrayList<>();
        try {
            preStat = conn.prepareStatement("SELECT * FROM library_users");
            result = preStat.executeQuery();
            while (result.next()) {
                users.add(result.getString("login"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return users;
    }
    public List<String[]> displayAll() {
        List<String[]> info = new ArrayList<>();
        try {
            preStat = conn.prepareStatement("SELECT * FROM activities");
            result = preStat.executeQuery();
            while (result.next()) {
                String[] event = new String[5];
                event[0] = result.getInt("a_id")+"";
                event[1] = result.getString("title");
                event[2] = result.getString("description");
                event[3] = result.getInt("responsible")+"";
                event[4] = result.getDate("date_time").toString();
                info.add(event);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return info;
    }
    public String[] displayById(int id) {
        String[] info = new String[5];
        try {
            preStat = conn.prepareStatement("SELECT * FROM activities WHERE a_id = ?");
            preStat.setInt(1, id);
            result = preStat.executeQuery();
            while (result.next()) {
                info[0] = result.getInt("a_id")+"";
                info[1] = result.getString("title");
                info[2] = result.getString("description");
                info[3] = result.getInt("responsible")+"";
                info[4] = result.getDate("date_time").toString();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return info;
    }
    public String getLoginById(int id) {
        String login = "Illegal ID";
        try {
            preStat = conn.prepareStatement("SELECT login FROM library_users WHERE user_id = ?");
            preStat.setInt(1, id);
            result = preStat.executeQuery();
            while (result.next()) {
                login = result.getString("login");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return login;
    }
    public void editById(int id, String title, String desc, int resp, String date) {
        try {
            preStat = conn.prepareStatement("UPDATE activities SET title = ? WHERE a_id = ?");
            preStat.setString(1, title);
            preStat.setInt(2, id);
            preStat.executeUpdate();
            preStat = conn.prepareStatement("UPDATE activities SET description = ? WHERE a_id = ?");
            preStat.setString(1, desc);
            preStat.setInt(2, id);
            preStat.executeUpdate();
            preStat = conn.prepareStatement("UPDATE activities SET responsible = ? WHERE a_id = ?");
            preStat.setInt(1, resp);
            preStat.setInt(2, id);
            preStat.executeUpdate();
            preStat = conn.prepareStatement("UPDATE activities SET date_time = ? WHERE a_id = ?");
            preStat.setDate(1, Date.valueOf(date));
            preStat.setInt(2, id);
            preStat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    public void deleteById(int id) {
        try {
            preStat = conn.prepareStatement("DELETE FROM child_activities WHERE a_id = ? OR ca_id = ?");
            preStat.setInt(1, id);
            preStat.setInt(2, id);
            preStat.executeUpdate();
            preStat = conn.prepareStatement("DELETE FROM participants WHERE a_id = ?");
            preStat.setInt(1, id);
            preStat.executeUpdate();
            preStat = conn.prepareStatement("DELETE FROM activities WHERE a_id = ?");
            preStat.setInt(1, id);
            preStat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    public void addEvent(String title, String desc, int resp, String date) {
        try {
            preStat = conn.prepareStatement("INSERT INTO activities VALUES (activities_seq.NEXTVAL, ?, ?, ?, ?)");
            preStat.setString(1, title);
            preStat.setString(2, desc);
            preStat.setInt(3, resp);
            preStat.setDate(4, Date.valueOf(date));
            preStat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
