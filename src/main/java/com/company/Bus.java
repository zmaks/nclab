package com.company;

/**
 * Created by ћаксим on 23.11.2015.
 */
public class Bus extends Transport implements PassengerTransport{

    private int oneMileCost; //стоимость проезда одной мили
    private int ticketCost;
    public Bus(String name, int speed, int oneMileCost)
    {
        super(name, speed);
        this.oneMileCost = oneMileCost;

    }

    @Override
    public void setSpeed(int speed) {
        if (speed > 150) this.speed = 150;
        else this.speed = speed;
    }

    @Override
    public String information(int trip) {
        float time = (float)trip/(float)speed;
        setTicketCost(trip);
        return "You can drive "+trip+" km in "+time+" minutes by BUS and it will be cost "+ticketCost+" $!";
    }

    public  int getOneMileCost() {return oneMileCost;}

    public void setTicketCost(int trip) {
        ticketCost = trip*oneMileCost;
    }

    public int getTicketCost(int trip) {
        return oneMileCost*trip;
    }
}
