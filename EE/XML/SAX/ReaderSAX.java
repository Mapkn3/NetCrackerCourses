package XML.SAX;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class ReaderSAX extends DefaultHandler
{
    final static int INDENT = 4;
    private int indent = 0;
    private String doctype;

    public ReaderSAX()
    {
        this.doctype = "";
    }

    public ReaderSAX(String doctype)
    {
        this.doctype = doctype;
    }

    public void startDocument() throws SAXException
    {
        //printString("Начало документа");
        printString("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n" + this.doctype);
    }

    public void endDocument() throws SAXException
    {
        //printString("Конец документа");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        //indent += INDENT;
        //printString("Элемент " + qName + ":");
        StringBuilder str = new StringBuilder();
        str.append("<" + qName);
        //indent += INDENT;
        for (int i = 0; i < attributes.getLength(); i++)
        {
            str.append(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
        }
        //indent -= INDENT;
        str.append(">");
        printString(str.toString());
        indent += INDENT;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        indent -= INDENT;
        printString("</" + qName + ">");
        //indent -= INDENT;
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

    public void characters(char[] ch, int start, int length) throws SAXException
    {
        //indent += INDENT;
        String str = new String(ch, start, length);
        printString(str);
        //indent -= INDENT;
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
        System.out.println(ind_s + str);
    }
}
