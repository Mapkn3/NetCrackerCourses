package XML;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import XML.DOM.*;
import XML.SAX.*;
import javax.xml.parsers.*;
import java.io.*;

public class TryXML
{
    private static void mySAXparser()
    {
        DefaultHandler handler = null;
        try
        {
            handler = new CorrectedStudentAverageSAX("<!DOCTYPE group SYSTEM \"D:\\IdeaProject\\Java8\\src\\xml\\xml\\group.dtd\">",
                    new FileOutputStream(new File("XML\\xml\\correctedGroupSAX.xml")));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = saxFactory.newSAXParser();
            parser.parse("XML\\xml\\myGroup.xml", handler);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void myDOMparser()
    {
        try
        {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document document = builder.parse("XML\\xml\\myGroup.xml");
            CorrectStudentAverageDOM obj = new CorrectStudentAverageDOM("<!DOCTYPE group SYSTEM \"D:\\IdeaProject\\Java8\\src\\xml\\xml\\group.dtd\">",
                    new FileOutputStream(new File("XML\\xml\\correctedGroupDOM.xml")));
            obj.myPrint(document);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        mySAXparser();
        myDOMparser();
    }
}
