package com.company;

import org.jdom.JDOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Максим on 07.12.2015.
 */
public class XPathTest {
    public static void XPathGetSpeed(String xmlPath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, JDOMException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(xmlPath);

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expression = xpath.compile("//Transport[TransportName='Auto 1']/Speed/text()");
        Object result = expression.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println("Auto 1 speed is : "+nodes.item(i).getNodeValue());
        }
    }
}
