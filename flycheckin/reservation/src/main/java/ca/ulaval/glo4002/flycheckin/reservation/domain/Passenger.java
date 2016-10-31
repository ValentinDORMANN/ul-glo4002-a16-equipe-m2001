package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.PassengerDto;

public class Passenger {

  private static final int CHILD_AGE = 15;

  private String passengerHash;
  private String firstName;
  private String lastName;
  private int age;
  private String passportNumber;
  private String seatClass;

  public Passenger(PassengerDto passengerDto, String flightInfos) throws IllegalArgumentException {
    this.firstName = passengerDto.first_name;
    this.lastName = passengerDto.last_name;
    this.age = passengerDto.age;
    this.passportNumber = passengerDto.passport_number;
    this.seatClass = passengerDto.seat_class;
    this.passengerHash = UUID.randomUUID().toString();
  }

  public boolean getChild() {
    return this.age < CHILD_AGE;
  }

  public String getPassengerHash() {
    return passengerHash;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public String getSeatClass() {
    return seatClass;
  }
}