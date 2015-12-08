package com.company;

/**
 * Created by Максим on 23.11.2015.
 */
public abstract class Transport {

    public Transport(String name, int speed)
    {
        this.name = name;
        this.speed = speed;
    }

    String name;
    int speed;

    public String getName()
    {
        return this.name;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public abstract void setSpeed(int speed);

    public abstract String information(int trip);

}
