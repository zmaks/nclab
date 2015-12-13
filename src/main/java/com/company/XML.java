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

    private static final Logger logger = Logger.getLogger(XML.class.getName());


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
        } catch (IOException io) {
            logger.error(io.getMessage());
        } catch (IllegalNameException e) {
            logger.error(e.getMessage());
        }
    }

    public static void parseXML(String path) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(path);

        try {
            Document doc = (Document) builder.build(xmlFile);
            Element root = doc.getRootElement();
            List transport = root.getChildren("Transport");
            logger.info("Parse XML:");
            logger.info("Transport List:");
            for (int i = 0; i < transport.size(); i++) {
                Element figure = (Element) transport.get(i);
                logger.info("Type: " + figure.getAttributeValue("Name"));
                logger.info("Name: " + figure.getChildText("TransportName"));
                logger.info("Speed: " + figure.getChildText("Speed"));
                if (figure.getAttributeValue("Name").equals("Bus")) {
                    logger.info("One mile cost: " + figure.getChildText("OneMileCost"));
                }
                if (figure.getAttributeValue("Name").equals("Plane")) {
                    logger.info("Flight Height: " + figure.getChildText("FlightHeight"));
                }
            }
        } catch (IOException io) {
            logger.error(io.getMessage());
        } catch (JDOMException e) {
            logger.error(e.getMessage());
        }
    }
}
