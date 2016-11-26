package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.Date;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;

public class BusinessPassenger extends Passenger {

  private static final int FREE_CHECKED_LUGGAGE_LIMIT = 2;
  private static final int CHECKED_LUGGAGE_MAX_KG = 30;
  private static final int CHECKED_LUGGAGE_MAX_CM = 158;
  private static final double EXCEDING_CHECKED_LUGGAGE_PRICE = 50;

  public BusinessPassenger(String flightNumber, Date flightDate, String passengerHash, String seatClass,
      boolean isVip) {
    super(flightNumber, flightDate, passengerHash, seatClass, isVip);
  }

  @Override
  protected void verifyLuggageDimensionAllowable(Luggage luggage) {
    luggage.verifyAllowableDimension(CHECKED_LUGGAGE_MAX_CM);
  }

  @Override
  protected void verifyLuggageWeightAllowable(Luggage luggage) {
    luggage.verifyAllowableWeight(CHECKED_LUGGAGE_MAX_KG);
  }

}
