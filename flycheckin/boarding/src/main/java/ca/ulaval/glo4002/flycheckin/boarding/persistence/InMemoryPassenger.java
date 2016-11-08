package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;

public class InMemoryPassenger {

  private static Map<String, Passenger> passengerMap = new HashMap<String, Passenger>();
  private static final String MESSAGE_ERROR_RESERVATION = "Error : passenger not found !";

  public void savePassenger(Passenger newPassenger) {
    String passengerHash = newPassenger.getPassengerHash();
    if (!passengerMap.containsKey(passengerHash))
      passengerMap.put(passengerHash, newPassenger);
  }

  public Passenger getPassengerByHash(String passengerHash) throws NotFoundPassengerException {
    if (passengerMap.isEmpty() || !passengerMap.containsKey(passengerHash))
      throw new NotFoundPassengerException(MESSAGE_ERROR_RESERVATION);
    else
      return passengerMap.get(passengerHash);
  }
}
