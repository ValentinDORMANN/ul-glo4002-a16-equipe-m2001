package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class Passenger {

  private static final String TYPE_CHECKED = "checked";
  private static final int LIMIT_CHECKED_LUGGAGES = 3;
  private static final String MESSAGE_ERROR_EXCEDED_CHECKED = "Luggage exceded the limit number allowed";
  private String flightNumber;
  private Date flightDate;
  private String passengerHash;
  private String seatClass;
  private List<Luggage> luggages;

  public Passenger() {
  }

  public Passenger(ReservationDto reservationDto) {
    flightNumber = reservationDto.flight_number;
    flightDate = reservationDto.flight_date;
    passengerHash = reservationDto.passengers[0].passenger_hash;
    seatClass = reservationDto.passengers[0].seat_class;
    this.luggages = new ArrayList<Luggage>();
  }

  public void addLuggage(Luggage luggage) throws ExcededCheckedLuggageException {
    if (!isNumberLuggageValid())
      throw new ExcededCheckedLuggageException(MESSAGE_ERROR_EXCEDED_CHECKED);
    luggages.add(luggage);
  }

  private boolean isNumberLuggageValid() {
    return countLuggageAlreadyChecked() < LIMIT_CHECKED_LUGGAGES;
  }

  private int countLuggageAlreadyChecked() {
    int checkedLuggageNumber = 0;
    for (int i = 0; i < luggages.size(); i++) {
      if (luggages.get(i).isType(TYPE_CHECKED))
        checkedLuggageNumber++;
    }
    return checkedLuggageNumber;
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