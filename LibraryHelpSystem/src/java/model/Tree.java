/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Nikolay
 */
public class Tree
{

    List<Tree> nodes = new ArrayList<>();
    int id;
    String name;
    int pid;
    String description;

    public Tree(int id, String name, int pid, String description)
    {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.description = description;
    }

    public void addNode(int id, String name, int pid, String description)
    {
        Tree tree = getNode(pid);
        tree.nodes.add(new Tree(id, name, pid, description));
    }

    public List<Tree> getNodes()
    {
        return nodes;
    }

    public String getName()
    {
        return name;
    }

    public void setNodes(List<Tree> nodes)
    {
        this.nodes = nodes;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Tree getNode(int id)
    {
        if (this.id == id)
        {
            return this;
        } else if (nodes.isEmpty())
        {
            return null;
        }
        for (int i = 0; i < nodes.size(); i++)
        {
            if (nodes.get(i).getNode(id) != null)
            {
                return nodes.get(i).getNode(id);
            }
        }
        return null;
    }

    public int size()
    {
        if (nodes.isEmpty())
        {
            return 1;
        }
        int size = 1;
        for (int i = 0; i < nodes.size(); i++)
        {
            size += nodes.get(i).size();
        }
        return size;
    }

    public String toHTML()
    {
        StringBuilder sb = new StringBuilder();
        if (nodes.isEmpty())
        {
            sb.append("<li class=\"file\">")
                    .append("<a href=\"catalog.jsp?category=")
                    .append(id)
                    .append("\">")
                    .append(name)
                    .append("</a>")
                    .append("</li>\n");
        } else
        {
            sb.append("<li>\n"
                    + "<label for=\"" + id + "\">" + name + "</label>\n"
                    + "<input type=\"checkbox\" id=\"" + id + "\" />\n"
                    + "<ol>\n");
            for (int i = 0; i < nodes.size(); i++)
            {
                sb.append(nodes.get(i).toHTML());
            }
            sb.append("\n"
                    + "</ol>\n"
                    + "</li>\n");
        }
        return sb.toString();
    }

    public String toExtendedHTML()
    {
        StringBuilder sb = new StringBuilder();
        if (nodes.isEmpty())
        {
            sb.append("<li class=\"file\">")
                    .append(name)
                    .append("<a href=\"")
                    .append(id)
                    .append("\"><img width=\"10\" height=\"10\" src=\"../images/edit.png\"/></a><a href=\"../pages/categories.jsp?del=")
                    .append(id)
                    .append("\"><img width=\"10\" height=\"10\" src=\"../images/delete.png\"/></a></li>\n");
        } else
        {
            sb.append("<li>\n" + "<label for=\"")
                    .append(id).
                    append("\">")
                    .append(name)
                    .append("</label>\n<input type=\"checkbox\" id=\"")
                    .append(id)
                    .append("\" />\n<ol>\n");
            for (int i = 0; i < nodes.size(); i++)
            {
                sb.append(nodes.get(i).toHTML());
            }
            sb.append("\n"
                    + "</ol>\n"
                    + "</li>\n");
        }
        return sb.toString();
    }
}
