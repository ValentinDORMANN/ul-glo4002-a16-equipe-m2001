package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.Date;

public class PassengerFactory {

  private static final String SEAT_ECONOMIC = "economic";

  public Passenger createPassenger(String flightNumber, Date flightDate, String passengerHash, String seatClass) {
    if (seatClass == SEAT_ECONOMIC)
      return createRegularPassenger(flightNumber, flightDate, passengerHash, seatClass);

    return createBusinessPassenger(flightNumber, flightDate, passengerHash, seatClass);
  }

  private Passenger createRegularPassenger(String flightNumber, Date flightDate, String passengerHash,
      String seatClass) {
    return new RegularPassenger(flightNumber, flightDate, passengerHash, seatClass);
  }

  private Passenger createBusinessPassenger(String flightNumber, Date flightDate, String passengerHash,
      String seatClass) {
    return new BusinessPassenger(flightNumber, flightDate, passengerHash, seatClass);
  }
}
