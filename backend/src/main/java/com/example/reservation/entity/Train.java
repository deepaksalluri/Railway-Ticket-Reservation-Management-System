package com.example.reservation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "train_number", unique = true, nullable = false)
    private String trainNumber;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "from_station")
    private String fromStation;

    @Column(name = "to_station")
    private String toStation;

    // ================= Seat Availability =================

    @Column(name = "ac1_seats", nullable = false)
    private int ac1Seats = 50;

    @Column(name = "ac2_seats", nullable = false)
    private int ac2Seats = 50;

    @Column(name = "ac3_seats", nullable = false)
    private int ac3Seats = 50;

    @Column(name = "sleeper_seats", nullable = false)
    private int sleeperSeats = 50;

    @Column(name = "second_seater_seats", nullable = false)
    private int secondSeaterSeats = 50;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public int getAc1Seats() {
        return ac1Seats;
    }

    public void setAc1Seats(int ac1Seats) {
        this.ac1Seats = ac1Seats;
    }

    public int getAc2Seats() {
        return ac2Seats;
    }

    public void setAc2Seats(int ac2Seats) {
        this.ac2Seats = ac2Seats;
    }

    public int getAc3Seats() {
        return ac3Seats;
    }

    public void setAc3Seats(int ac3Seats) {
        this.ac3Seats = ac3Seats;
    }

    public int getSleeperSeats() {
        return sleeperSeats;
    }

    public void setSleeperSeats(int sleeperSeats) {
        this.sleeperSeats = sleeperSeats;
    }

    public int getSecondSeaterSeats() {
        return secondSeaterSeats;
    }

    public void setSecondSeaterSeats(int secondSeaterSeats) {
        this.secondSeaterSeats = secondSeaterSeats;
    }
}