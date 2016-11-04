package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class Reservation {

  private static final String MSG_INVALID_PASSENGER = "Error : passenger not found !";
  private static final String MSG_INVALID_CHECKIN_DATE = "Error: immpossible to checkin at this moment !";
  private static final int FOURTY_EIGHT_HOUR = 48 * 60 * 60 * 1000;
  private static final int SIX_HOUR = 6 * 60 * 60 * 1000;
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

  public Reservation(ReservationInMemory reservationInMemory, ReservationDto reservationDto,
      List<Passenger> passengers) {
    this.reservationNumber = reservationDto.reservation_number;
    this.reservationDate = reservationDto.reservation_date;
    this.reservationConfirmation = reservationDto.reservation_confirmation;
    this.flightNumber = reservationDto.flight_number;
    this.flightDate = reservationDto.flight_date;
    this.paymentLocation = reservationDto.payment_location;
    this.passengers = passengers;
    this.reservationInMemory = reservationInMemory;
  }

  public Reservation(ReservationDto reservationDto) throws IllegalArgumentReservationException {
    this.passengers = new ArrayList<Passenger>();
    this.reservationNumber = reservationDto.reservation_number;
    this.reservationDate = reservationDto.reservation_date;
    this.reservationConfirmation = reservationDto.reservation_confirmation;
    this.flightNumber = reservationDto.flight_number;
    this.flightDate = reservationDto.flight_date;
    this.paymentLocation = reservationDto.payment_location;
    for (int i = 0; i < reservationDto.passengers.size(); i++) {
      Passenger passenger = new Passenger(reservationDto.passengers.get(i));
      this.passengers.add(passenger);
    }
    createReservation();
  }

  private void createReservation() throws IllegalArgumentReservationException {
    reservationInMemory.saveNewReservation(this);
  }

  public Reservation readReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    return reservationInMemory.getReservationByNumber(reservationNumber);
  }

  public List<String> getPassengerHashListInReservation() {
    List<String> hashs = new ArrayList<String>();
    for (Passenger passenger : passengers) {
      hashs.add(passenger.getPassengerHash());
    }
    return hashs;
  }

  public Passenger getPassengerFromHash(String hash) {
    for (Passenger passenger : passengers) {
      if (passenger.getPassengerHash().equals(hash))
        return passenger;
    }
    throw new NotFoundPassengerException(MSG_INVALID_PASSENGER);
  }

  public void validateCheckinPeriod(String by) throws NotTimeToCheckinException {
    if (by.equals("SELF")) {
      validateSelfCheckinPeriod();
    }
  }

  private void validateSelfCheckinPeriod() {
    long todayInMillisecond = new Date().getTime();
    long flightDateInMillisecond = this.getFlightDate().getTime();
    if (!((flightDateInMillisecond - FOURTY_EIGHT_HOUR <= todayInMillisecond)
        && (todayInMillisecond <= flightDateInMillisecond - SIX_HOUR))) {
      throw new NotTimeToCheckinException(MSG_INVALID_CHECKIN_DATE);
    }
  }

  public int getReservationNumber() {
    return reservationNumber;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  public Date getFlightDate() {
    return flightDate;
  }

  public void setFlightDate(Date flightDate) {
    this.flightDate = flightDate;
  }

  public List<Passenger> getPassengers() {
    return passengers;
  }

}
