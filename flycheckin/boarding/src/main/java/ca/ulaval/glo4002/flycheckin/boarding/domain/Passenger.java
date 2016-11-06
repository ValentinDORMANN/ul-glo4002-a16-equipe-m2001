package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;


public class Passenger {

  private static final String TYPE_CHECKED = "checked";
  private static final int LIMIT_CHECKED_LUGGAGES = 3;
  private static final String MESSAGE_ERROR_EXCEDED_CHECKED = "Luggage exceded the limit number allowed";
  private String firstName;
  private String lastName;
  private String passportNumber;
  private String seatClass;
  private boolean child;
  private String passengerHash;
  private List<Luggage> luggages;
  private int checkedLuggageNumber = 0;
  
  public Passenger() {
  }

  public Passenger(PassengerDto passengerDto) {
    this.firstName = passengerDto.first_name;
    this.lastName = passengerDto.last_name;
    this.passportNumber = passengerDto.passport_number;
    this.seatClass = passengerDto.seat_class;
    this.child = passengerDto.child;
    this.passengerHash = passengerDto.passenger_hash;
    this.luggages = new ArrayList<Luggage>();
  }

  public void addLuggage(Luggage luggage) throws ExcededCheckedLuggageException {
    if (isNumberLuggageValid())
      luggages.add(luggage); 
    else 
      throw new ExcededCheckedLuggageException(MESSAGE_ERROR_EXCEDED_CHECKED);
  }

  private int countLuggageAlreadyChecked() {
    for (int i = 0; i < luggages.size(); i++) {
      if (luggages.get(i).isType(TYPE_CHECKED))
        checkedLuggageNumber++;
    }
    return checkedLuggageNumber;
  }

  private boolean isNumberLuggageValid() {
    return countLuggageAlreadyChecked() <= LIMIT_CHECKED_LUGGAGES;
  }

  public String getPassengerHash() {
    return passengerHash;
  }

  public void setPassengerHash(String passengerHash) {
    this.passengerHash = passengerHash;
  }

}
