package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

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

  public Reservation(ReservationDto reservationDto) {
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
    save();
  }

  public int getReservationNumber() {
    return reservationNumber;
  }

  public void save() {
    reservationInMemory.saveNewReservation(this);
  }
}
