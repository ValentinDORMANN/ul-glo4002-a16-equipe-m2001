package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotTimeToCheckinException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;

@Entity
public class Reservation {

  private static final String MSG_INVALID_PASSENGER = "Error : passenger not found !";
  private static final String MSG_INVALID_CHECKIN_DATE = "Error: immpossible to checkin at this moment !";
  private static final int CONVERT_HOUR_TO_MILLISECOND = 3600000;
  private static final int SELF_CHECKIN_START_TIME = 48 * CONVERT_HOUR_TO_MILLISECOND;
  private static final int SELF_CHECKIN_END_TIME = 6 * CONVERT_HOUR_TO_MILLISECOND;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int reservationNumber;
  @Column(name = "reservationDate")
  @Temporal(TemporalType.DATE)
  private Date reservationDate;
  @Column(name = "reservationConfirmation")
  private String reservationConfirmation;
  @Column(name = "flightNumber")
  private String flightNumber;
  @Column(name = "flightDate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date flightDate;
  @Column(name = "paymentLocation")
  private String paymentLocation;
  @Column(name = "passengers")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Passenger> passengers;
  private HibernateReservation hibernateReservation = new HibernateReservation();

  public Reservation() {
  }

  public Reservation(HibernateReservation hibernateReservation, ReservationDto reservationDto,
      List<Passenger> passengers) {
    this.reservationNumber = reservationDto.reservation_number;
    this.reservationDate = reservationDto.reservation_date;
    this.reservationConfirmation = reservationDto.reservation_confirmation;
    this.flightNumber = reservationDto.flight_number;
    this.flightDate = reservationDto.flight_date;
    this.paymentLocation = reservationDto.payment_location;
    this.passengers = passengers;
    this.hibernateReservation = hibernateReservation;
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
    hibernateReservation.insertNewReservation(this);
  }

  public Reservation searchReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    return hibernateReservation.findReservationByNumber(reservationNumber);
  }

  public Reservation searchReservationByPassengerHash(String passengerHash) throws NotFoundPassengerException {
    return hibernateReservation.findReservationByPassengerHash(passengerHash);
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
    if (by.equals("SELF")) {
      validateSelfCheckinPeriod();
    }
  }

  private void validateSelfCheckinPeriod() {
    long todayInMillisecond = new Date().getTime();
    long flightDateInMillisecond = this.getFlightDate().getTime();
    if (!((flightDateInMillisecond - SELF_CHECKIN_START_TIME <= todayInMillisecond)
        && (todayInMillisecond <= flightDateInMillisecond - SELF_CHECKIN_END_TIME))) {
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
