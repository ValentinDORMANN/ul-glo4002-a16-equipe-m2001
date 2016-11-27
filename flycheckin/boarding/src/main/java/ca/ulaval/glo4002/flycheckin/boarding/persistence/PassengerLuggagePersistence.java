package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;

public class PassengerLuggagePersistence {

  private static Map<String, List<Luggage>> passengerMap = new HashMap<String, List<Luggage>>();
  private static final String MESSAGE_ERROR_RESERVATION = "Error : passenger not found !";

  public void savePassengerLuggage(Passenger newPassenger) {
    String passengerHash = newPassenger.getPassengerHash();
    if (!passengerMap.containsKey(passengerHash))
      passengerMap.put(passengerHash, newPassenger.getLuggages());
    else
      passengerMap.replace(passengerHash, newPassenger.getLuggages());
  }

  public List<Luggage> getPassengerLuggage(String passengerHash) throws NotFoundPassengerException {
    if (passengerMap.isEmpty() || !passengerMap.containsKey(passengerHash))
      throw new NotFoundPassengerException(MESSAGE_ERROR_RESERVATION);
    return passengerMap.get(passengerHash);
  }

  public void clearPassengerMap() {
    passengerMap.clear();
  }
}
