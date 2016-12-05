package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.Date;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.NotAllowableLuggageException;

public class RegularPassenger extends Passenger {

  private static final int FREE_CHECKED_LUGGAGE_LIMIT = 1;
  private static final int CHECKED_LUGGAGE_WEIGHT_MAX_KG = 23;

  public RegularPassenger(String flightNumber, Date flightDate, String passengerHash, String seatClass, boolean isVip,boolean isChild) {
    super(flightNumber, flightDate, passengerHash, seatClass, isVip,isChild);
  }

  @Override
  protected void calculateLuggagePrice(Luggage luggage) {
    luggage.calculatePrice();
    if (countFreeLuggage() < FREE_CHECKED_LUGGAGE_LIMIT)
      luggage.assignLuggageFree();
  }

  @Override
  protected void verifyLuggageHasStandardDimension(Luggage luggage) throws NotAllowableLuggageException {
    luggage.verifyAllowableDimension();
    luggage.verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_MAX_KG);
  }
}
