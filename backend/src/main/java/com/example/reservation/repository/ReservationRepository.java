package com.example.reservation.repository;

import com.example.reservation.entity.Reservation;
import com.example.reservation.entity.Train;
import com.example.reservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByPnrNumber(String pnrNumber);

    List<Reservation> findByUser(User user);

    List<Reservation> findByUserUsername(String username);

    List<Reservation> findByTrainAndClassTypeAndStatusOrderBySeatNumberAsc(
            Train train,
            String classType,
            String status
    );

    List<Reservation> findByTrainAndClassTypeAndStatus(
            Train train,
            String classType,
            String status
    );
}