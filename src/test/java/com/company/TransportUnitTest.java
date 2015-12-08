package com.company;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Максим on 06.12.2015.
 */
public class TransportUnitTest {

    Logger logger;

    @Before
    public void setLogger(){
       logger = Logger.getLogger("test");
    }

    @Test
    public void StartUnitTest() {

        Bus bus1 = new Bus("Bus 1", 200, 8);
        String expected = "You can drive 20 km in 0.1 minutes by BUS and it will be cost 160 $!";
        assertEquals(expected, bus1.information(20));
        logger.info("Bus 1 assertEquals success");
        Automobile auto2 = new Automobile("Auto 2", 200);
        expected = "You can drive 20 km in 0.1 minutes by AUTO";
        assertEquals(expected, auto2.information(20));
        logger.info("Auto 1 assertEquals success");
        Plain plain1 = new Plain("Plain 1", 700, 40, 6000);
        assertEquals(6000, plain1.getFlightHeight());
        logger.info("Plain 1 assertEquals success");
    }


}
