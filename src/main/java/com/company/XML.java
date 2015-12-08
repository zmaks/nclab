package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.IllegalNameException;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Created by Максим on 06.12.2015.
 */
public class XML {

    private static final Logger logger = Logger.getLogger("file");

    public static void createXML(ArrayList<Transport> transports) {
        try {
            Element root = new Element("TransportList");
            Document doc = new Document(root);
            for (Transport t : transports) {
                Element transport = new Element("Transport");
                transport.setAttribute("Name", t.getClass().getSimpleName());
                transport.addContent(new Element("TransportName").addContent(String.valueOf(t.getName())));
                transport.addContent(new Element("Speed").addContent(String.valueOf(t.getSpeed())));
                if (t instanceof PassengerTransport) {
                    transport.addContent(new Element("OneMileCost").addContent(String.valueOf(((PassengerTransport) t).getOneMileCost())));
                }
                if (t instanceof AirTransport) {
                    transport.addContent(new Element("FlightHeight").addContent(String.valueOf(((AirTransport) t).getFlightHeight())));
                }
                root.addContent(transport);
            }

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());

            outputter.output(doc, new FileOutputStream("All transport list create.xml"));
            logger.info("All transport list.xml created");
            System.out.println("All transport list.xml created");
        } catch (IOException io) {
            logger.error("XML-file didn't create: "+io.getMessage());
        } catch (IllegalNameException e) {
            logger.error("XML-file didn't crate: "+e.getMessage());
        }
    }

    public static void parseXML(String path) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(path);

        try {
            Document doc = (Document) builder.build(xmlFile);
            Element root = doc.getRootElement();
            List transport = root.getChildren("Transport");
            System.out.println("Transport List:\n");
            for (int i = 0; i < transport.size(); i++) {
                Element figure = (Element) transport.get(i);
                System.out.println("Type: " + figure.getAttributeValue("Name"));
                System.out.println("Name: " + figure.getChildText("TransportName"));
                System.out.println("Speed: " + figure.getChildText("Speed"));
                if (figure.getAttributeValue("Name").equals("Bus")) {
                    System.out.println("One mile cost: " + figure.getChildText("OneMileCost"));
                }
                if (figure.getAttributeValue("Name").equals("Plane")) {
                    System.out.println("Flight Height: " + figure.getChildText("FlightHeight"));
                }
            }
        } catch (IOException io) {
            logger.error("Cannot load file: " + io.getMessage());
        } catch (JDOMException e) {
            logger.error("Cannot parse file: " + e.getMessage());
        }
    }
}
