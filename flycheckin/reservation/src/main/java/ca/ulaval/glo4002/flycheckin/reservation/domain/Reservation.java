package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.*;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;
import ca.ulaval.glo4002.flycheckin.reservation.exception.*;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.*;

public class Reservation {
  private int reservationNumber;
  private Date reservationDate;
  private String reservationConfirmation;
  private String flightNumber;
  private Date flightDate;
  private String paymentLocation;
  private List<Passenger> passengers;
  private ReservationInMemory reservationInMemory = new ReservationInMemory();

  public Reservation() {
  }

  public Reservation(ReservationDto reservationDto) throws IllegalArgumentReservationException {
    this.passengers = new ArrayList<Passenger>();
    this.reservationNumber = reservationDto.reservation_number;
    this.reservationDate = reservationDto.reservation_date;
    this.reservationConfirmation = reservationDto.reservation_confirmation;
    this.flightNumber = reservationDto.flight_number;
    this.flightDate = reservationDto.flight_date;
    this.paymentLocation = reservationDto.payment_location;
    for (int i = 0; i < reservationDto.passengers.length; i++) {
      Passenger passenger = new Passenger(reservationDto.passengers[i]);
      this.passengers.add(passenger);
    }
    save();
  }

  public int getReservationNumber() {
    return reservationNumber;
  }

  public void save() throws IllegalArgumentReservationException {
    reservationInMemory.saveNewReservation(this);
  }
}
