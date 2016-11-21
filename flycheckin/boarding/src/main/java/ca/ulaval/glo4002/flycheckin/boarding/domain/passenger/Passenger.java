package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotAllowableLuggageException;

public abstract class Passenger {

  private static final double BASE_PRICE = 0;
  private static final int CHECKED_LUGGAGE_LIMIT = 3;
  private static final int VIP_CHECKED_LUGGAGE_LIMIT = 4;
  private static final int CARRY_ON_LUGGAGE_LIMIT = 1;
  private String flightNumber;
  private Date flightDate;
  private String passengerHash;
  private String seatClass;
  private boolean isVip;
  private List<Luggage> luggages;

  public Passenger(String flightNumber, Date flightDate, String passengerHash, String seatClass, boolean isVip) {
    this.flightNumber = flightNumber;
    this.flightDate = flightDate;
    this.passengerHash = passengerHash;
    this.seatClass = seatClass;
    this.isVip = isVip;
    luggages = new ArrayList<Luggage>();
  }

  public void addLuggage(Luggage luggage) throws NotAllowableLuggageException {
    verifyLuggageAllowable(luggage);
    luggage.setPrice(calculateLuggagePrice());
    luggages.add(luggage);
  }

  protected abstract double calculateLuggagePrice();

  private void verifyLuggageAllowable(Luggage luggage) {
    verifyLuggageLimitNumberReached(luggage);
    verifyLuggageDimensionAllowable(luggage);
    verifyLuggageWeightAllowable(luggage);
  }

  private void verifyLuggageLimitNumberReached(Luggage luggage) {
    // TODO
    // if(isVip)
  }

  protected abstract void verifyLuggageDimensionAllowable(Luggage luggage);

  protected abstract void verifyLuggageWeightAllowable(Luggage luggage);

  private int countTypeLuggageAssigned(String type) {
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
    return this.passengerHash;
  }

  public String getFlightNumber() {
    return this.flightNumber;
  }

  public Date getFlightDate() {
    return this.flightDate;
  }

  public String getSeatClass() {
    return this.seatClass;
  }

  public boolean getIsVip() {
    return this.isVip;
  }

  public List<Luggage> getLuggages() {
    return this.luggages;
  }
}