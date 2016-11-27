package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.Date;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotAllowableLuggageException;

public class RegularPassenger extends Passenger{
  
  private static final int FREE_CHECKED_LUGGAGE_LIMIT = 1;
  private static final double EXCEEDED_CHECKED_LUGGAGE_PRICE = 50;
  private static final double CARRY_ON_LUGGAGE_PRICE = 30;
  private static final int CHECKED_LUGGAGE_WEIGHT_MAX_KG = 23;
  private static final int CHECKED_LUGGAGE_DIMENSION_MAX_CM = 158;
  private static final double FREE = 0;

  public RegularPassenger(String flightNumber, Date flightDate, String passengerHash, String seatClass, boolean isVip) {
    super(flightNumber, flightDate, passengerHash, seatClass, isVip);
  }

  @Override
  protected double calculateLuggagePrice(Luggage luggage) {
    if (luggage.isType(CHECKED_LUGGAGE_TYPE)) {
      if (countTypeLuggageAssigned(CHECKED_LUGGAGE_TYPE) < FREE_CHECKED_LUGGAGE_LIMIT)
        return FREE;
      else
        return EXCEEDED_CHECKED_LUGGAGE_PRICE;
    } else
      return CARRY_ON_LUGGAGE_PRICE;
  }

  @Override
  protected void verifyLuggageDimensionAllowable(Luggage luggage) throws NotAllowableLuggageException{
    if(luggage.isType(CHECKED_LUGGAGE_TYPE))
      luggage.verifyAllowableDimension(CHECKED_LUGGAGE_DIMENSION_MAX_CM);
    else
      luggage.checkLuggageAllowable();
  }

  @Override
  protected void verifyLuggageWeightAllowable(Luggage luggage) throws NotAllowableLuggageException{
    if(luggage.isType(CHECKED_LUGGAGE_TYPE))
      luggage.verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_MAX_KG);
    else
      luggage.checkLuggageAllowable();
  }

}
