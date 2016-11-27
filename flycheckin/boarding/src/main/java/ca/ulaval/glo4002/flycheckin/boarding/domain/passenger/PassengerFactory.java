package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.Date;

public class PassengerFactory {

  private static final String SEAT_ECONOMIC = "economic";

  public Passenger createPassenger(String flightNumber, Date flightDate, String passengerHash, String seatClass,
      boolean isVip) {
    if (seatClass == SEAT_ECONOMIC)
      return createRegularPassenger(flightNumber, flightDate, passengerHash, seatClass, isVip);

    return createBusinessPassenger(flightNumber, flightDate, passengerHash, seatClass, isVip);
  }

  private Passenger createRegularPassenger(String flightNumber, Date flightDate, String passengerHash, String seatClass,
      boolean isVip) {
    return new RegularPassenger(flightNumber, flightDate, passengerHash, seatClass, isVip);
  }

  private Passenger createBusinessPassenger(String flightNumber, Date flightDate, String passengerHash,
      String seatClass, boolean isVip) {
    return new BusinessPassenger(flightNumber, flightDate, passengerHash, seatClass, isVip);
  }
}
