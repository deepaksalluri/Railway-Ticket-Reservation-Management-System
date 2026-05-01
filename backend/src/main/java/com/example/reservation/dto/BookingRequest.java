package com.example.reservation.dto;

import java.util.List;

public class BookingRequest {

    private String username;
    private String trainNumber;
    private String classType;
    private String journeyDate;

    private List<PassengerRequest> passengers;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public List<PassengerRequest> getPassengers() { return passengers; }
    public void setPassengers(List<PassengerRequest> passengers) { this.passengers = passengers; }
}