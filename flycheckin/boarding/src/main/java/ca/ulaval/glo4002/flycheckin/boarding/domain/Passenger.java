package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;


public class Passenger {

  private static final double BASE_PRICE = 0;
  private static final String TYPE_CHECKED = "checked";
  private static final String TYPE_CARRY_ON = "carry-on";
  private static final String LUGGAGES_LIMIT_EXCEDED_ERROR = "Error : Luggage limit number reached";
  private String flightNumber;
  private Date flightDate;
  private String passengerHash;
  private String seatClass;
  private List<Luggage> luggages;

  public Passenger(String flightNumber, Date flightDate, String passengerHash, String seatClass) {
    this.flightNumber = flightNumber;
    this.flightDate = flightDate;
    this.passengerHash = passengerHash;
    this.seatClass = seatClass;
    luggages = new ArrayList<Luggage>();
  }

  public void addLuggage(Luggage luggage) throws ExcededLuggageException {
    /*if (!isNumberLuggageValidByType(luggageType, luggage.getMaxLuggageAllowed()))
      throw new ExcededLuggageException(LUGGAGES_LIMIT_EXCEDED_ERROR);*/
    if (!isFirstCheckedLuggage())
      //luggage.setPrice(SURPLUS_PRICE_LUGGAGE);
    luggages.add(luggage);
  }
  
  private boolean isNumberLuggageValidByType(String type, int maxAllowed) {
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

  public double getTotalPrice() {
    double totalPrice = BASE_PRICE;
    for (Luggage luggage : luggages)
      totalPrice += luggage.getPrice();
    return totalPrice;
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
}