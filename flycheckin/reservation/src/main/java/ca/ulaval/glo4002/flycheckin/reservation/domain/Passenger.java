package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.PassengerDto;

public class Passenger {
  private String passengerHash;
  private String firstName;
  private String lastName;
  private int age;
  private String passportNumber;
  private String seatClass;

  public Passenger(PassengerDto passengerDto, String flightInfos) {
    this.firstName = passengerDto.first_name;
    this.lastName = passengerDto.last_name;
    this.age = passengerDto.age;
    this.passportNumber = passengerDto.passport_number;
    this.seatClass = passengerDto.seat_class;
    this.passengerHash = createPassengerHash(passengerDto.toString(), flightInfos);
  }

  private String createPassengerHash(String passengerInfos, String flightInfos) throws IllegalArgumentException {
    String combinePassengerFlight = passengerInfos + flightInfos;
    return UUID.fromString(combinePassengerFlight).toString();
  }
}