package com.BattleTanks;

import student.TestCase;

//-------------------------------------------------------------------------
/**
*  The test tank class, tests the controls of the functions of the tank object
*
*  @author ankit23
*  @author samja
*  @author mluke93
*  @version May 4, 2013
*/
public class TankTest extends TestCase
{
    Tank testT;

    /**
     * Sets up the tank for testing
     */
    public void setUp()
    {
        testT = new Tank("Player 1", 4);
    }

    /**
     * Tests the move method to see if the tank moves
     */
    public void testMove()
    {
        testT.move(1);
        assertEquals(7, testT.getloc(), 0.0001);

        testT.move(0);
        assertEquals(4, testT.getloc(), 0.0001);

        testT.setGas(0);
        testT.move(1);
        assertEquals(4, testT.getloc(), 0.0001);
    }

    /**
     * Tests the range method
     */
    public void testRange()
    {
        testT.setPower(0);
        testT.setAngle(90);

        assertEquals(0, testT.range(), 0.0001);

        testT.setPower(80);
        testT.setAngle(45);

        assertEquals(640, testT.range(), 0.0001);
    }

    /**
     * tests the set and get angle methods
     */
    public void testGetAndSetAngle()
    {
        assertEquals(0, testT.getAngle(), 0.0001);

        testT.setAngle(90);
        assertEquals(90, testT.getAngle(), 0.0001);
    }

    /**
     * tests the set and get power methods
     */
    public void testSetAndGetPower()
    {
        assertEquals(0, testT.getPower(), 0.001);

        testT.setPower(9);
        assertEquals(9, testT.getPower(), 0.001);
    }

    /**
     * tests the set and get health methods
     */
    public void testSetAndGetHealth()
    {
        assertEquals(100, testT.getHealth());

        testT.setHealth(90);
        assertEquals(90, testT.getHealth());
    }

    /**
     * tests the set and get gas methods
     */
    public void testSetAndGetGas()
    {
        assertEquals(30, testT.getGas());

        testT.setGas(9);
        assertEquals(9, testT.getGas());
    }

    /**
     * tests the set and get name methods
     */
    public void testSetAndGetName()
    {
        assertEquals("Player 1", testT.getName());

        testT.setName("Player 3");
        assertEquals("Player 3", testT.getName());
    }

    /**
     * tests the get location method
     */
    public void testGetLoc()
    {
        assertEquals(4, testT.getloc(), 0.0001);
    }
}
