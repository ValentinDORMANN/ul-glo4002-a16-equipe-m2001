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
    String flightInfos = this.flightNumber + this.flightDate.toString();
    for (int i = 0; i < reservationDto.passengers.length; i++) {
      Passenger passenger = new Passenger(reservationDto.passengers[i], flightInfos);
      this.passengers.add(passenger);
    }
    createReservation();
  }

  private void createReservation() throws IllegalArgumentReservationException {
    reservationInMemory.saveNewReservation(this);
  }

  public Reservation readReservationByNumber(int reservationNumber) {
    return reservationInMemory.getReservationByNumber(reservationNumber);
  }

  public int getReservationNumber() {
    return reservationNumber;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public Date getFlightDate() {
    return flightDate;
  }

  public List<Passenger> getPassengers() {
    return passengers;
  }

}
