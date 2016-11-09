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
  private static final String SELF = "SELF";
  private static final int CONVERT_HOUR_TO_MILLISECOND = 3600000;
  private static final int SELF_CHECKIN_START_TIME = 48 * CONVERT_HOUR_TO_MILLISECOND;
  private static final int SELF_CHECKIN_END_TIME = 6 * CONVERT_HOUR_TO_MILLISECOND;
  private static ReservationInMemory reservationInMemory = new ReservationInMemory();
  private int reservationNumber;
  private Date reservationDate;
  private String reservationConfirmation;
  private String flightNumber;
  private Date flightDate;
  private String paymentLocation;
  private List<Passenger> passengers;

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
    storeReservation();
  }

  private void storeReservation() throws IllegalArgumentReservationException {
    reservationInMemory.saveNewReservation(this);
  }

  public Reservation readReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    return reservationInMemory.getReservationByNumber(reservationNumber);
  }

  public Reservation searchReservationByPassengerHash(String passenger_hash) throws NotFoundPassengerException {
    return reservationInMemory.getReservationByPassengerHash(passenger_hash);
  }

  public List<String> getPassengerHashListInReservation() {
    List<String> passengerHashs = new ArrayList<String>();
    for (Passenger passenger : passengers) {
      passengerHashs.add(passenger.getPassengerHash());
    }
    return passengerHashs;
  }

  public boolean isPassengerInfosValid(String hash) throws NotFoundPassengerException {
    Passenger passenger = getPassengerByHash(hash);
    return passenger.isValid();
  }

  public Passenger getPassengerByHash(String hash) {
    for (Passenger passenger : passengers) {
      if (passenger.getPassengerHash().equals(hash))
        return passenger;
    }
    throw new NotFoundPassengerException(MSG_INVALID_PASSENGER);
  }

  public void validateCheckinPeriod(String by) throws NotTimeToCheckinException {
    if (by.equals(SELF))
      validateSelfCheckinPeriod();
  }

  private void validateSelfCheckinPeriod() {
    long todayInMillisecond = new Date().getTime();
    long flightDateInMillisecond = this.getFlightDate().getTime();
    if (!((flightDateInMillisecond - SELF_CHECKIN_START_TIME <= todayInMillisecond)
        && (todayInMillisecond <= flightDateInMillisecond - SELF_CHECKIN_END_TIME)))
      throw new NotTimeToCheckinException(MSG_INVALID_CHECKIN_DATE);
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
