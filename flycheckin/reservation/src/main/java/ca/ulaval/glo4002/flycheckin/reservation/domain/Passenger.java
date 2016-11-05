package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.PassengerDto;

@Entity
public class Passenger {

  private static final int CHILD_AGE = 15;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String passengerHash;
  @Column(name = "firstname")
  private String firstName;
  @Column(name = "lastname")
  private String lastName;
  @Column(name = "age")
  private int age;
  @Column(name = "passeportNumber")
  private String passportNumber;
  @Column(name = "seatClass")
  private String seatClass;

  public Passenger(){
  }
  
  public Passenger(PassengerDto passengerDto) throws IllegalArgumentException {
    this.firstName = passengerDto.first_name;
    this.lastName = passengerDto.last_name;
    this.age = passengerDto.age;
    this.passportNumber = passengerDto.passport_number;
    this.seatClass = passengerDto.seat_class;
    this.passengerHash = UUID.randomUUID().toString();
  }

  public boolean isValid() {
    return !(firstName.trim().equals("") || lastName.trim().equals("") || passportNumber.trim().equals(""));
  }

  public boolean isChild() {
    return this.age < CHILD_AGE;
  }

  public int getAge() {
    return age;
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