package ca.ulaval.glo4002.flycheckin.reservation.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Passenger;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PassengerDto {

  @JsonIgnore
  private static final int CHILD_AGE = 15;
  public String first_name;
  public String last_name;
  public int age;
  public String passport_number;
  public String seat_class;
  public boolean child;
  public String passenger_hash;

  public PassengerDto() {
  }

  public PassengerDto(Passenger passenger) {
    this.first_name = passenger.getFirstName();
    this.last_name = passenger.getLastName();
    this.passport_number = passenger.getPassportNumber();
    this.seat_class = passenger.getSeatClass();
    this.child = passenger.isChild();
    // TODO
    // this.child = passenger.getAge() < CHILD_AGE;
    this.passenger_hash = passenger.getPassengerHash();
  }
}
