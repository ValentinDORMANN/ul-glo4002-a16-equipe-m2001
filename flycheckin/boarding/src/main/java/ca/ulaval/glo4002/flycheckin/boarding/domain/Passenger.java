package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class Passenger {

  private static final int SINGLE_INDEX = 0;
  private static final double BASE_PRICE = 0;
  private static final double SURPLUS_PRICE_LUGGAGE = 50;
  private static final double BASE_CARRY_ON_LUGGAGE = 30;		// TODO attribut of CarryOnLuggage ?
  private static final String TYPE_CHECKED = "checked";
  private static final String TYPE_CARRY_ON = "carry-on";
  private static final int CHECKED_LUGGAGES_LIMIT = 3;
  private static final int CARRY_ON_LUGGAGES_LIMIT = 1;
  private static final String LUGGAGES_LIMIT_EXCEDED_ERROR = "Error : Luggage limit number reached";
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
    passengerHash = reservationDto.passengers[SINGLE_INDEX].passenger_hash;
    seatClass = reservationDto.passengers[SINGLE_INDEX].seat_class;
    luggages = new ArrayList<Luggage>();
  }

  public void addLuggage(Luggage luggage) throws ExcededLuggageException {
    //luggage.checkLuggageAllowable();
    String luggageType = TYPE_CHECKED;//luggage.getType();  // TODO Not TDtA
    int maxLuggageAlowed = 0;
    switch (luggageType) {
      case TYPE_CHECKED:
          maxLuggageAlowed = CHECKED_LUGGAGES_LIMIT;
        if (!isFirstCheckedLuggage())
            luggage.setPrice(SURPLUS_PRICE_LUGGAGE);
        break;
      case TYPE_CARRY_ON:
          maxLuggageAlowed = CARRY_ON_LUGGAGES_LIMIT;
          luggage.setPrice(BASE_CARRY_ON_LUGGAGE);
        break;
      default:
        break;
    }
    if (!IsNumberLuggageValidByType(luggageType, maxLuggageAlowed))
      throw new ExcededLuggageException(LUGGAGES_LIMIT_EXCEDED_ERROR);
    luggages.add(luggage);
  }
  
  private boolean IsNumberLuggageValidByType(String type, int maxAllowed) {
    return countLuggageAlreadyCheckedByType(type) < maxAllowed;
  }
  
  private boolean isFirstCheckedLuggage() {
    return countLuggageAlreadyCheckedByType(TYPE_CHECKED) == 0;
  }
  private int countLuggageAlreadyCheckedByType(String type) {
    int checkedLuggageNumber = 0;
    for (int i = 0; i < luggages.size(); i++) {
      if (luggages.get(i).isType(type))
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

  public List<Luggage> getLuggages() {
    return luggages;
  }

  public double getTotalPrice() {
    double totalPrice = BASE_PRICE;
    for (Luggage luggage : luggages)
      totalPrice += luggage.getPrice();
    return totalPrice;
  }
}