package com.company;

/**
 * Created by ћаксим on 23.11.2015.
 */
public class Plain extends Transport implements PassengerTransport, AirTransport{

    private int oneMileCost; //стоимость проезда одной мили
    private int flightHeight;
    private int ticketCost;

    public Plain(String name, int speed, int oneMileCost, int flightHeight)
    {
        super(name, speed);
        this.oneMileCost = oneMileCost;
        this.flightHeight = flightHeight;
    }


    @Override
    public void setSpeed(int speed) {
        if (speed > 1000) this.speed = 1000;
        else this.speed = speed;
    }

    @Override
    public String information(int trip) {
        float time = (float)trip/(float)speed*1.7f;
        setTicketCost(trip);
        return "You can drive "+trip+" km in "+time+" minutes by PLAIN and it will be cost "+ticketCost+" $!";
    }

    public int getTicketCost(int trip) {
        return oneMileCost*trip+1000;
    }

    public int getFlightHeight() {
        return flightHeight;
    }
    public int getOneMileCost() {return oneMileCost;}

    public void setTicketCost(int trip) {
        ticketCost = trip*oneMileCost;
    }
}
