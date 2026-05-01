package com.example.reservation.controller;

import com.example.reservation.entity.*;
import com.example.reservation.repository.*;
import com.example.reservation.dto.BookingRequest;
import com.example.reservation.dto.PassengerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/reservation")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private UserRepository userRepository;


    // ================= BOOK =================
    @PostMapping("/book")
    public Reservation book(@RequestBody BookingRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Train train = trainRepository
                .findByTrainNumber(request.getTrainNumber())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        Reservation reservation = new Reservation();

        reservation.setUser(user);
        reservation.setTrain(train);
        reservation.setClassType(request.getClassType());
        reservation.setJourneyDate(request.getJourneyDate());
        reservation.setFromStation(train.getFromStation());
        reservation.setToStation(train.getToStation());

        // Generate PNR
        reservation.setPnrNumber(generatePNR());

        int availableSeats = getAvailableSeats(train, request.getClassType());

        if (availableSeats > 0) {
            reservation.setStatus("CONFIRMED");
        } else {
            reservation.setStatus("WAITING");
        }

        List<Passenger> passengerList = new ArrayList<>();

        for (PassengerRequest p : request.getPassengers()) {

            Passenger passenger = new Passenger();

            passenger.setName(p.getName());
            passenger.setAge(p.getAge());
            passenger.setGender(p.getGender());
            passenger.setBerthPreference(p.getBerthPreference());

            // Assign seat if confirmed
            if (reservation.getStatus().equals("CONFIRMED")) {

                int seatNo = getConfirmedCount(train, request.getClassType()) + 1;

                passenger.setSeatNumber(seatNo);

                passenger.setBerthType(assignBerth(seatNo));

                decreaseSeat(train, request.getClassType());

            } else {

                passenger.setSeatNumber(getWaitingCount(train, request.getClassType()) + 1);

            }

            passenger.setReservation(reservation);

            passengerList.add(passenger);
        }

        reservation.setPassengers(passengerList);

        trainRepository.save(train);

        return reservationRepository.save(reservation);
    }


    // ================= CANCEL =================
    @PostMapping("/cancel")
    public String cancel(@RequestParam String pnr) {

        Reservation reservation = reservationRepository
                .findByPnrNumber(pnr)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Train train = reservation.getTrain();
        String classType = reservation.getClassType();

        if (reservation.getStatus().equals("CONFIRMED")) {

            increaseSeat(train, classType);
        }

        reservation.setStatus("CANCELLED");

        reservationRepository.save(reservation);
        trainRepository.save(train);

        return "Ticket Cancelled";
    }


    // ================= STATUS =================
    @GetMapping("/status")
    public List<Reservation> status(@RequestParam String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return reservationRepository.findByUser(user);
    }


    // ================= SEARCH TRAIN =================
    @GetMapping("/search")
    public Train search(@RequestParam String trainNumber) {

        return trainRepository
                .findByTrainNumber(trainNumber)
                .orElseThrow(() -> new RuntimeException("Train not found"));
    }


    // ================= SHOW ALL TRAINS =================
    @GetMapping("/trains")
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }


    // ================= SEARCH BY STATIONS =================
    @GetMapping("/searchStations")
    public List<Train> searchStations(@RequestParam String from,
                                      @RequestParam String to) {

        return trainRepository.findByFromStationAndToStation(from, to);
    }


    // ================= HELPERS =================

    private String generatePNR() {
        long number = (long) (Math.random() * 9000000000L) + 1000000000L;
        return String.valueOf(number);
    }


    private int getAvailableSeats(Train train, String classType) {

        switch (classType.toUpperCase()) {

            case "1AC":
                return train.getAc1Seats();

            case "2AC":
                return train.getAc2Seats();

            case "3AC":
                return train.getAc3Seats();

            case "SLEEPER":
                return train.getSleeperSeats();

            case "2S":
                return train.getSecondSeaterSeats();
        }

        return 0;
    }


    private void decreaseSeat(Train train, String classType) {

        switch (classType.toUpperCase()) {

            case "1AC":
                train.setAc1Seats(train.getAc1Seats() - 1);
                break;

            case "2AC":
                train.setAc2Seats(train.getAc2Seats() - 1);
                break;

            case "3AC":
                train.setAc3Seats(train.getAc3Seats() - 1);
                break;

            case "SLEEPER":
                train.setSleeperSeats(train.getSleeperSeats() - 1);
                break;

            case "2S":
                train.setSecondSeaterSeats(train.getSecondSeaterSeats() - 1);
                break;
        }
    }


    private void increaseSeat(Train train, String classType) {

        switch (classType.toUpperCase()) {

            case "1AC":
                train.setAc1Seats(train.getAc1Seats() + 1);
                break;

            case "2AC":
                train.setAc2Seats(train.getAc2Seats() + 1);
                break;

            case "3AC":
                train.setAc3Seats(train.getAc3Seats() + 1);
                break;

            case "SLEEPER":
                train.setSleeperSeats(train.getSleeperSeats() + 1);
                break;

            case "2S":
                train.setSecondSeaterSeats(train.getSecondSeaterSeats() + 1);
                break;
        }
    }


    private int getConfirmedCount(Train train, String classType){

        return reservationRepository
                .findByTrainAndClassTypeAndStatus(train, classType, "CONFIRMED")
                .size();
    }


    private int getWaitingCount(Train train, String classType){

        return reservationRepository
                .findByTrainAndClassTypeAndStatus(train, classType, "WAITING")
                .size();
    }


    private String assignBerth(int seatNo){

        switch(seatNo % 6){

            case 1: return "LOWER";
            case 2: return "MIDDLE";
            case 3: return "UPPER";
            case 4: return "SIDE LOWER";
            case 5: return "SIDE UPPER";
            default: return "LOWER";
        }
    }
}