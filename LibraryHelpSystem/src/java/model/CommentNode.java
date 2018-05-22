/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Nikolay
 */
public class CommentNode
{

    int id;
    int pid;
    String name;
    String description;
    CommentNode parent;
    Collection<CommentNode> children = new LinkedList<>();

    public CommentNode(int id, int pid, String name, String description)
    {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.description = description;
    }

    

    public void accept(Map<Integer, CommentNode> ctx)
    {
        CommentNode parent = ctx.get(pid);
        if (parent != null)
        {
            parent.addChild(this);
        }
        ctx.put(this.id, this);
    }

    public void addChild(CommentNode child)
    {
        child.parent = this;
        getChildren().add(child);
    }

    public Collection<CommentNode> getChildren()
    {
        return children;
    }
}
