package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.Date;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class Passenger {

  private String flightNumber;
  private Date flightDate;
  private String passengerHash;
  private String seatClass;

  public Passenger() {
  }

  public Passenger(ReservationDto reservationDto) {
    flightNumber = reservationDto.flight_number;
    flightDate = reservationDto.flight_date;
    passengerHash = reservationDto.passengers[0].passenger_hash;
    seatClass = reservationDto.passengers[0].seat_class;
  }

  public String getPassengerHash() {
    return passengerHash;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public Date getFlightDate() {
    return flightDate;
  }

  public String getSeatClass() {
    return seatClass;
  }

}
