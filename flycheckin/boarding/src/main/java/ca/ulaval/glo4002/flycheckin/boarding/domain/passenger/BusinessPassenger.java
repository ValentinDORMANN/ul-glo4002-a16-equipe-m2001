package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.Date;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;

public class BusinessPassenger extends Passenger {

  private static final int FREE_CHECKED_LUGGAGE_LIMIT = 2;
  private static final int CHECKED_LUGGAGE_MAX_KG = 30;
  private static final int CHECKED_LUGGAGE_MAX_CM = 158;
  private static final int CARRY_ON_LUGGAGE_MAX_KG = 10;
  private static final int CARRY_ON_LUGGAGE_MAX_CM = 118;
  private static final double EXCEEDING_CHECKED_LUGGAGE_PRICE = 50;
  private static final double CARRY_ON_LUGGAGE_PRICE = 30;
  private static final double FREE = 0;

  public BusinessPassenger(String flightNumber, Date flightDate, String passengerHash, String seatClass,
      boolean isVip) {
    super(flightNumber, flightDate, passengerHash, seatClass, isVip);
  }

  @Override
  protected double calculateLuggagePrice(Luggage luggage) {
    if (luggage.isType(CHECKED_LUGGAGE_TYPE)) {
      if (countTypeLuggageAssigned(CHECKED_LUGGAGE_TYPE) < FREE_CHECKED_LUGGAGE_LIMIT)
        return FREE;
      else
        return EXCEEDING_CHECKED_LUGGAGE_PRICE;
    }
    return CARRY_ON_LUGGAGE_PRICE;
  }

  @Override
  protected void verifyLuggageDimensionAllowable(Luggage luggage) {
    if (luggage.isType(CHECKED_LUGGAGE_TYPE))
      luggage.verifyAllowableDimension(CHECKED_LUGGAGE_MAX_CM);
    else
      luggage.verifyAllowableDimension(CARRY_ON_LUGGAGE_MAX_CM);
  }

  @Override
  protected void verifyLuggageWeightAllowable(Luggage luggage) {
    if (luggage.isType(CHECKED_LUGGAGE_TYPE))
      luggage.verifyAllowableWeight(CHECKED_LUGGAGE_MAX_KG);
    else
      luggage.verifyAllowableWeight(CARRY_ON_LUGGAGE_MAX_KG);
  }

}
