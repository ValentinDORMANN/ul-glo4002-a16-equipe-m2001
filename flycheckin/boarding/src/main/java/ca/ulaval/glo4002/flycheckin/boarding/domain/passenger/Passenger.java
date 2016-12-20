package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.client.CheckinHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.NotAllowableLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public abstract class Passenger {

  private static final double BASE_PRICE = 0;
  private static final int CHECKED_LUGGAGE_LIMIT = 3;
  private static final int VIP_CHECKED_LUGGAGE_LIMIT = 4;
  private static final int CARRY_ON_LUGGAGE_LIMIT = 1;
  private static final double VIP_DISCOUNT = 0.95;
  protected static final String CHECKED_LUGGAGE_TYPE = "checked";
  protected static final String CARRY_ON_LUGGAGE_TYPE = "carry-on";
  private static final String TOO_MUCH_LUGGAGE = "too much luggage !";

  private String flightNumber;
  private Date flightDate;
  private String passengerHash;
  private String seatClass;
  private boolean isVip;
  private boolean isChild;
  private List<Luggage> luggages;

  public Passenger(String flightNumber, Date flightDate, String passengerHash, String seatClass, boolean isVip,
      boolean isChild) {
    this.flightNumber = flightNumber;
    this.flightDate = flightDate;
    this.passengerHash = passengerHash;
    this.seatClass = seatClass;
    this.isVip = isVip;
    this.isChild = isChild;
    this.luggages = new ArrayList<Luggage>();
  }

  public void addLuggage(Luggage luggage) throws NotAllowableLuggageException {
    verifyLuggageAllowableByNumber(luggage);
    verifyLuggageHasStandardDimension(luggage);
    calculateLuggagePrice(luggage);
    luggages.add(luggage);
  }

  protected abstract void verifyLuggageHasStandardDimension(Luggage luggage) throws NotAllowableLuggageException;

  protected abstract void calculateLuggagePrice(Luggage luggage);

  private void verifyLuggageAllowableByNumber(Luggage luggage) throws NotAllowableLuggageException {
    if (luggage.isType(CARRY_ON_LUGGAGE_TYPE))
      verifyAnotherCarryOnLuggageAllowable();
    else
      verifyAnotherCheckedLuggageAllowable();
  }

  private void verifyAnotherCarryOnLuggageAllowable() {
    if (countTypeLuggageAssigned(CARRY_ON_LUGGAGE_TYPE) == CARRY_ON_LUGGAGE_LIMIT)
      throw new NotAllowableLuggageException(TOO_MUCH_LUGGAGE);
  }

  private void verifyAnotherCheckedLuggageAllowable() {
    if (!isVip && countTypeLuggageAssigned(CHECKED_LUGGAGE_TYPE) == CHECKED_LUGGAGE_LIMIT)
      throw new NotAllowableLuggageException(TOO_MUCH_LUGGAGE);
    else if (isVip && countTypeLuggageAssigned(CHECKED_LUGGAGE_TYPE) == VIP_CHECKED_LUGGAGE_LIMIT)
      throw new NotAllowableLuggageException(TOO_MUCH_LUGGAGE);
  }

  protected int countTypeLuggageAssigned(String type) {
    int typeLuggageNumber = 0;
    for (int i = 0; i < luggages.size(); i++) {
      if (luggages.get(i).isType(type))
        typeLuggageNumber++;
    }
    return typeLuggageNumber;
  }

  protected int countFreeLuggage() {
    int freeLuggage = 0;
    for (int i = 0; i < luggages.size(); i++) {
      if (luggages.get(i).isFree())
        freeLuggage++;
    }
    return freeLuggage;
  }

  public double getTotalPrice() {
    double totalPrice = BASE_PRICE;
    for (Luggage luggage : luggages)
      totalPrice += luggage.getPrice();
    if (isVip)
      return appliedVipDiscount(totalPrice);

    return totalPrice;
  }

  private double appliedVipDiscount(double price) {
    return price * VIP_DISCOUNT;
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

  public List<Luggage> getLuggages() {
    return this.luggages;
  }

  public boolean isChild() {
    return this.isChild;
  }

  public void isCheckinDone(CheckinHttpClient checkinHttpClient) throws BoardingModuleException {
    checkinHttpClient.verifyCheckinFromReservation(passengerHash);
  }
}