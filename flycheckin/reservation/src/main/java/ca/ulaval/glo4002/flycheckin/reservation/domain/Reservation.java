package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.Date;
import java.util.List;

public class Reservation {
  private int reservationNumber;
  private Date reservationDate;
  private String reservationConfirmation;
  private String flightNumber;
  private Date flightDate;
  private String paymentLocation;
  private List<Passenger> passengers;

  public Reservation() {
  }

  public Reservation(int reservationNumber, Date reservationDate, String reservationConfirmation, String flightNumber,
      Date flightDate, String paymentLocation, List<Passenger> passengers) {
    this.reservationNumber = reservationNumber;
    this.reservationDate = reservationDate;
    this.reservationConfirmation = reservationConfirmation;
    this.flightNumber = flightNumber;
    this.flightDate = flightDate;
    this.paymentLocation = paymentLocation;
    this.passengers = passengers;
  }

  public int getReservationNumber() {
    return reservationNumber;
  }

  public void setReservationNumber(int reservationNumber) {
    this.reservationNumber = reservationNumber;
  }

  public void setReservationDate(Date reservationDate) {
    this.reservationDate = reservationDate;
  }

  public void setReservationConfirmation(String reservationConfirmation) {
    this.reservationConfirmation = reservationConfirmation;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public void setFlightDate(Date flightDate) {
    this.flightDate = flightDate;
  }

  public void setPaymentLocation(String paymentLocation) {
    this.paymentLocation = paymentLocation;
  }
}
