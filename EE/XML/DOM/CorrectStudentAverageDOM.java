package XML.DOM;

import org.w3c.dom.*;

import java.io.*;

public class CorrectStudentAverageDOM
{
    private final static int INDENT = 4;
    private int indent = -8;
    private double marks;
    private int count;
    private boolean isAverage;
    private String doctype;
    private OutputStream out;

    public CorrectStudentAverageDOM(String doctype, OutputStream out)
    {
        this.marks = 0;
        this.count = 0;
        this.isAverage = false;
        this.doctype = doctype;
        this.out = out;
    }

    public void myPrint(Document document)
    {
        printTag("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n" + this.doctype);
        printNodeInfo(document);
    }

    private void printNodeInfo(Node node)
    {
        StringBuilder tagBuilder = new StringBuilder();
        indent += INDENT;
        if (node instanceof Element)
        {
            tagBuilder.append("<").append(node.getNodeName());
            if (node.hasAttributes())
            {
                NamedNodeMap nnm = node.getAttributes();
                for (int i = 0; i < nnm.getLength(); i++)
                {
                    Attr attr = (Attr)nnm.item(i);
                    tagBuilder.append(" ").append(attr.getName()).append("=\"").append(attr.getValue()).append("\"");
                }
            }
            if (node.getNodeName().equals("subject"))
            {
                marks += Integer.parseInt(((Element)node).getAttribute("mark"));
                count++;
            }
            if (node.getNodeName().equals("average"))
            {
                isAverage = true;
            }
            if (!node.hasChildNodes())
            {
                tagBuilder.append("/>");
            }
            else
            {
                tagBuilder.append(">");
            }
        }
        else if (node instanceof Text)
        {
            if (!((Text)node).getData().trim().equals(""))
            {
                if (isAverage)
                {
                    double average = marks/count;
                    if (average != Double.parseDouble(((Text)node).getData()))
                    {
                        printTag(String.format("%.2f", average).replace(',', '.'));
                    }
                    else
                    {
                        printTag(((Text)node).getData());
                    }
                    isAverage = false;
                    marks = 0;
                    count = 0;
                }
            }
        }
        printTag(tagBuilder.toString());
        NodeList nl = node.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++)
        {
            printNodeInfo(nl.item(i));
        }
        if (node.hasChildNodes() && node instanceof Element)
        {
            tagBuilder.setLength(0);
            tagBuilder.append("</").append(node.getNodeName()).append(">");
            printTag(tagBuilder.toString());
        }
        indent -= INDENT;
    }

    private void printTag(String tagBuilder)
    {
        if (tagBuilder.length() > 0)
        {
            String ind_s;
            if (indent > 0)
            {
                char[] ind = new char[indent];
                java.util.Arrays.fill(ind, ' ');
                ind_s = new String(ind, 0, indent);
            }
            else
            {
                ind_s = "";
            }
            try
            {
                out.write((ind_s + tagBuilder + "\n").getBytes());
                out.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
