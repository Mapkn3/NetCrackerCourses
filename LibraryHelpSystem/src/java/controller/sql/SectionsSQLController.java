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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CommentNode;
import model.Tree;

/**
 *
 * @author Nikolay
 */
public class SectionsSQLController extends SQLController
{

    public void insert(String name, int parent_id, String discription)
    {
        try
        {
            executeSQL(
                    "call Section_Insert(?, ?, ?)",
                    name,
                    parent_id,
                    discription
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void update(int sectionID, String name, int parentID, String discription)
    {
        try
        {
            executeSQL(
                    "call Section_Update(?, ?, ?, ?)",
                    sectionID,
                    name,
                    parentID,
                    discription
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateName(int sectionID, String name)
    {
        try
        {
            executeSQL(
                    "call Section_Name_Update(?, ?)",
                    sectionID,
                    name
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateParentID(int sectionID, int parentID)
    {
        try
        {
            executeSQL(
                    "call Section_Parent_Id_Update(?, ?)",
                    sectionID,
                    parentID
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void updateDescription(int sectionID, String discription)
    {
        try
        {
            executeSQL(
                    "call Section_Description_Update(?, ?)",
                    sectionID,
                    discription
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void delete()
    {
        try
        {
            executeSQL(
                    "DELETE FROM sections"
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void deleteByID(int sectionID)
    {
        try
        {
            executeSQL(
                    "call Section_Delete(?)",
                    sectionID
            );
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public Tree getTree()
    {
        try
        {
            ResultSet rs = executeSQL(
                    "SELECT section_id, name, parent_id, description "
                    + "FROM sections "
                    + "START WITH parent_id is null "
                    + "CONNECT BY PRIOR section_id = parent_id "
                    + "ORDER BY section_id"
            );
            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int parent = rs.getInt(3);
            String description = rs.getString(4);
            Tree tree = new Tree(id, name, parent, description);
            while (rs.next())
            {
                id = rs.getInt(1);
                name = rs.getString(2);
                parent = rs.getInt(3);
                description = rs.getString(4);
                tree.addNode(id, name, parent, description);
            }
            return tree;
        } catch (SQLException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public List<String[]> selectLeafs()
    {
        List<String[]> list = new LinkedList<>();
        try
        {
            ResultSet rs = executeSQL(
                    "SELECT * FROM sections "
                    + "WHERE CONNECT_BY_ISLEAF = 1 "
                    + "START WITH parent_id is null "
                    + "CONNECT BY PRIOR section_id = parent_id"
            );
            while (rs.next())
            {
                String[] section = new String[4];
                section[0] = rs.getInt(1) + "";
                section[1] = rs.getString(2);
                section[2] = rs.getInt(3) + "";
                section[3] = rs.getString(4);
                list.add(section);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(SectionsSQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
