package com.company;

/**
 * Created by Максим on 23.11.2015.
 */
public class Automobile extends Transport {

    public Automobile(String name, int speed)
    {
        super(name, speed);
    }
    @Override
    public void setSpeed(int speed) {
        if (speed > 300) this.speed = 300;
        else this.speed = speed;
    }

    @Override
    public String information(int trip) {
        float time = (float)trip/(float)speed;
        return "You can drive "+trip+" km in "+time+" minutes by AUTO";
    }
}
