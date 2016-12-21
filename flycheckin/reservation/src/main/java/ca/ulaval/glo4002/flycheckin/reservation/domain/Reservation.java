package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonFormat;

import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.NotFoundReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.ReservationDto;

@Entity
public class Reservation {

  private static final String MSG_INVALID_PASSENGER = "Error : passenger not found !";
  private static final String MSG_INVALID_CHECKIN_DATE = "Error: immpossible to checkin at this moment !";
  private static final String SELF = "SELF";
  private static final int CONVERT_HOUR_TO_MILLISECOND = 3600000;
  private static final int SELF_CHECKIN_START_TIME = 48 * CONVERT_HOUR_TO_MILLISECOND;
  private static final int SELF_CHECKIN_END_TIME = 6 * CONVERT_HOUR_TO_MILLISECOND;

  @Id
  @Column(name = "reservationNumber", unique = true, nullable = false)
  private int reservationNumber;
  @Column(name = "flightNumber")
  private String flightNumber;
  @Column(name = "reservationDate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date flightDate;
  @OneToMany
  @JoinColumn(name = "reservationNumber")
  @Cascade(value = { CascadeType.ALL })
  @ElementCollection(targetClass = Passenger.class)
  private List<Passenger> passengers;
  
  @Transient
  private HibernateReservation hibernateReservation;

  public Reservation() {
    this.hibernateReservation = new HibernateReservation();
  }

  public Reservation(HibernateReservation hibernateReservation, ReservationDto reservationDto,
      List<Passenger> passengers) {
    this.reservationNumber = reservationDto.reservation_number;
    this.flightNumber = reservationDto.flight_number;
    this.flightDate = reservationDto.flight_date;
    this.passengers = passengers;
    this.hibernateReservation = hibernateReservation;
  }

  public Reservation(ReservationDto reservationDto) throws IllegalArgumentReservationException {
    this.hibernateReservation = new HibernateReservation();
    this.passengers = new ArrayList<Passenger>();
    this.reservationNumber = reservationDto.reservation_number;
    this.flightNumber = reservationDto.flight_number;
    this.flightDate = reservationDto.flight_date;
    
    for (int i = 0; i < reservationDto.passengers.size(); i++) {
      Passenger passenger = new Passenger(reservationDto.passengers.get(i));
      passenger.setReservation(this);
      this.passengers.add(passenger);
    }
  }

  private void storeReservation() throws IllegalArgumentReservationException {
    hibernateReservation.persisteReservation(this);
  }

  public Reservation readReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    return hibernateReservation.findReservationByNumber(reservationNumber);
  }

  public Reservation searchReservationByPassengerHash(String passenger_hash) throws NotFoundPassengerException {
    return hibernateReservation.findReservationByPassengerHash(passenger_hash);
  }

  public boolean isThisHashInReservation(String passengerHash) {
    for (Passenger passenger : passengers) {
      if (passenger.hasThisHash(passengerHash))
        return true;
    }
    return false;
  }

  public boolean isPassengerInfosValid(String passengerHash) throws NotFoundPassengerException {
    Passenger passenger = getPassengerByHash(passengerHash);
    return passenger.isValid();
  }

  public Passenger getPassengerByHash(String passengerHash) {
    for (Passenger passenger : passengers) {
      if (passenger.hasThisHash(passengerHash))
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
    if ((flightDateInMillisecond - SELF_CHECKIN_START_TIME > todayInMillisecond)
        || (todayInMillisecond > flightDateInMillisecond - SELF_CHECKIN_END_TIME))
      throw new NotTimeToCheckinException(MSG_INVALID_CHECKIN_DATE);
  }

  public void changePassengerVipStatus(String hash, boolean isVip) {
    getPassengerByHash(hash).changeVipStatus(isVip);
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
