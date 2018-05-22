package XML.SAX;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;

public class CorrectedStudentAverageSAX extends DefaultHandler
{
    private final static int INDENT = 4;
    private int indent = 0;
    private String doctype;
    private OutputStream out;
    private double marks = 0;
    private int countSubject = 0;
    private double average = 0;
    private boolean isAverage = false;
    private boolean needCloseTag = true;

    public CorrectedStudentAverageSAX(String doctype, OutputStream out)
    {
        this.doctype = doctype;
        this.out = out;
    }

    public void startDocument() throws SAXException
    {
        printString("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n" + this.doctype);
    }

    public void endDocument() throws SAXException
    {
        System.out.println("Обработка завершена!");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        StringBuilder str = new StringBuilder();
        str.append("<").append(qName);
        if (qName.equals("subject"))
        {
            this.countSubject++;
            this.marks += Integer.parseInt(attributes.getValue("mark"));
            this.needCloseTag = false;
        }
        if (qName.equals("average"))
        {
            this.isAverage = true;
            this.needCloseTag = true;
        }
        for (int i = 0; i < attributes.getLength(); i++)
        {
            str.append(" ").append(attributes.getQName(i)).append("=\"").append(attributes.getValue(i)).append("\"");
        }
        str.append(this.needCloseTag ? ">" : "/>");
        printString(str.toString());
        indent += INDENT;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        indent -= INDENT;
        if (this.needCloseTag)
        {
            printString("</" + qName + ">");
        }
    }

    public void warning(SAXParseException e) throws SAXException
    {
        System.out.println("Предупреждение: " + e.getPublicId());
    }

    public void error(SAXParseException e) throws SAXException
    {
        System.out.println("Ошибка: " + e.getPublicId());
    }

    public void fatalError(SAXParseException e) throws SAXException
    {
        System.out.println("Фатальная ошибка : " + e.getPublicId());
    }

    private String checkAverage(String average)
    {
        double xmlAverage = Double.parseDouble(average);
        this.average = this.marks / this.countSubject;
        if (xmlAverage == this.marks / this.countSubject)
        {
            return average;
        }
        else
        {
            return String.format("%.2f", this.average).replace(',', '.');
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException
    {
        String str = new String(ch, start, length).trim();
        if (isAverage) {
            str = this.checkAverage(str);
            this.marks = 0;
            this.countSubject = 0;
            this.average = 0;
            this.isAverage = false;
        }
        if (!str.equals(""))
        {
            printString(str);
        }
    }

    private void printString(String str)
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
            out.write((ind_s + str + "\n").getBytes());
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
