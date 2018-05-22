
import controller.sql.SectionsSQLController;
import java.util.Arrays;
import model.CommentNode;
import model.Tree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolay
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Tree cn = new SectionsSQLController().getTree();
        Tree tree = cn.getNode(15);
        System.out.println(tree.getName());
        System.out.println(cn.size());
    }
    
}
