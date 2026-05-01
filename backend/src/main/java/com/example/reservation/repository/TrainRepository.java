package com.example.reservation.repository;

import com.example.reservation.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {

   Optional<Train> findByTrainNumber(String trainNumber);

    List<Train> findByFromStationAndToStation(String fromStation, String toStation);

}