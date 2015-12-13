package com.company;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Максим on 23.11.2015.
 */
public class Main {

    public static final Logger logger = Logger.getLogger("file");

    public static void main(String[] args) {

        Bus bus1 = new Bus("Bus 1", 200, 8);
        logger.info("Bus 1 created");
        Bus bus2 = new Bus("Bus 2", 120, 3);
        logger.info("Bus 2 created");
        Automobile auto1 = new Automobile("Auto 1", 240);
        logger.info("Auto 1 created");
        Automobile auto2 = new Automobile("Auto 2", 200);
        logger.info("Auto 2 created");
        Plain plain1 = new Plain("Plain 1", 700, 40, 6000);
        logger.info("Plain 1 created");
        Plain plain2 = new Plain("Plain 2", 500, 30, 4000);
        logger.info("Plain 2 created");

        ArrayList<Transport> transports = new ArrayList<Transport>();

        transports.add(bus1);
        transports.add(bus2);
        transports.add(auto1);
        transports.add(auto2);
        transports.add(plain1);
        transports.add(plain2);
        logger.info("All transport added into ArrayList");

        for(Transport i : transports){
            logger.info(i.getName());
            logger.info(i.information(20));
            if(i instanceof AirTransport) logger.info("Flight height is "+((AirTransport) i).getFlightHeight());
        }

        //Create XML
        Scanner in = new Scanner(System.in);
        System.out.println("Create XML? (Y/N)");
        if(in.next().equals("Y")) XML.createXML(transports);

        //Parse XML
        System.out.println("Parse XML? (Y/N)");
        if(in.next().equals("Y")) XML.parseXML("All transport listt.xml");

        //XPath
        System.out.println("Run xPath? (Y/N)");
        if(in.next().equals("Y")){
            System.out.println("Write transport name:");
            String name =in.next();
            try {
                XPathTest.XPathGetSpeed("All transport list.xml");
            } catch (ParserConfigurationException e) {
                logger.error(e.getMessage());
            } catch (SAXException e) {
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
            } catch (XPathExpressionException e) {
                logger.error(e.getMessage());
            } catch (JDOMException e) {
                logger.error(e.getMessage());
            }
        }
        in.close();
    }


}
