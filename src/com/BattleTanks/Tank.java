package com.BattleTanks;


//-------------------------------------------------------------------------
/**
*  The tank class, controls the functions of the tank object
*
*  @author ankit23
*  @author samja
*  @author mluke93
*  @version April 24, 2013
*/

public class Tank
{
    private int health;
    private float angle;
    private int gas;
    private float power;
    private String name;
    private float loc;

    /**
     * Creates a new tank object
     * @param id - Name
     * @param x - location
     */
    public Tank(String id, float x)
    {
        angle = 0;
        gas = 30;
        health = 100;
        name = id;
        loc = x;
    }

    /**
     * moves the tank
     * @param dir - 1 if right, 0 if left
     */
    public void move(int dir)
    {
        if (gas > 0)
        {
            gas--;
            if (dir == 1)
            {
                loc = loc + 3;
            }
            else
            {
                loc = loc - 3;
            }
        }
    }

    /**
     * calculates the distance of bullet when it is fired
     * @return the range the bullet is fired out of the tank
     */
    public float range()
    {
        return (float)(Math.pow(power, 2) *
            Math.sin(2 * angle * Math.PI / 180) / 10);
    }

    /**
     * Sets the angle
     * @param an - angle
     */
    public void setAngle(int an)
    {
        angle = an;
    }

    /**
     * Sets the power
     * @param pow - Power
     */
    public void setPower(float pow)
    {
        power = pow;
    }

 // Accessor Methods ---------------------------------------------------------

    /**
     * gets the health of the tank
     * @return health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * gets the gas of the tank
     * @return gas
     */
    public int getGas()
    {
        return gas;
    }

    /**
     * gets the name of the tank
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * get the angle the tank is at - UNUSED
     * @return angle
     */
    public float getAngle()
    {
        return angle;
    }

    /**
     * get the location
     * @return loc
     */
    public float getloc()
    {
        return loc;
    }

    /**
     * sets the health of the tank
     * @param newHealth setting the health
     */
    public void setHealth(int newHealth)
    {
        health = newHealth;
    }

    /**
     * sets the gas of the tank - UNUSED
     * @param newGas setting the gas
     */
    public void setGas(int newGas)
    {
        gas = newGas;
    }

    /**
     * gets the power of the tank
     * @return power of the tank
     */
    public float getPower()
    {
        return power;
    }

    /**
     * sets the name of the tank - UNUSED
     * @param newName for the tank
     */
    public void setName(String newName)
    {
        name = newName;
    }
}