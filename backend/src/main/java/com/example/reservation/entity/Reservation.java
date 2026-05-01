package com.example.reservation.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<Passenger> passengers;

    private String pnrNumber;
    private String ticketId;

    private String passengerName;
    private int passengerAge;
    private String gender;

    private String fromStation;
    private String toStation;
    private String journeyDate;
    private String bookingDate;
    private String bookingTime;

    private String classType;
    private String coach;
    private String berthType;
    private String berthPreference;

    private String status; // CONFIRMED / WAITING / CANCELLED
    private int seatNumber;

    private String paymentStatus; // SUCCESS / FAILED

    @ManyToOne
    private User user;

    @ManyToOne
    private Train train;

    // ================= GETTERS & SETTERS =================

    public Long getId() { return id; }

    public String getPnrNumber() { return pnrNumber; }
    public void setPnrNumber(String pnrNumber) { this.pnrNumber = pnrNumber; }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getFromStation() { return fromStation; }
    public void setFromStation(String fromStation) { this.fromStation = fromStation; }

    public String getToStation() { return toStation; }
    public void setToStation(String toStation) { this.toStation = toStation; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getBookingTime() { return bookingTime; }
    public void setBookingTime(String bookingTime) { this.bookingTime = bookingTime; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public String getCoach() { return coach; }
    public void setCoach(String coach) { this.coach = coach; }

    public String getBerthType() { return berthType; }
    public void setBerthType(String berthType) { this.berthType = berthType; }

    public String getBerthPreference() { return berthPreference; }
    public void setBerthPreference(String berthPreference) { this.berthPreference = berthPreference; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public void setId(Long id) {
        this.id = id;
    }
    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }

    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }
}