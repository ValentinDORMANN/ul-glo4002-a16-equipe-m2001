package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

public class PassengerDto {

  public String first_name;
  public String last_name;
  public String passport_number;
  public String seat_class;
  public boolean child;

  public String getFirst_name() {
    return first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public String getPassport_number() {
    return passport_number;
  }

  public String getSeat_class() {
    return seat_class;
  }

  public boolean isChild() {
    return child;
  }

  public String getPassenger_hash() {
    return passenger_hash;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public void setPassport_number(String passport_number) {
    this.passport_number = passport_number;
  }

  public void setSeat_class(String seat_class) {
    this.seat_class = seat_class;
  }

  public void setChild(boolean child) {
    this.child = child;
  }

  public void setPassenger_hash(String passenger_hash) {
    this.passenger_hash = passenger_hash;
  }

  public String passenger_hash;
}
