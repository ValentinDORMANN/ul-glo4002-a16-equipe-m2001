package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageRegistry;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;

public class PassengerLuggagePersistence implements LuggageRegistry {

  private static final Map<String, List<Luggage>> passengerMap = new HashMap<String, List<Luggage>>();

  @Override
  public void savePassengerLuggage(Passenger newPassenger) {
    String passengerHash = newPassenger.getPassengerHash();
    if (!passengerMap.containsKey(passengerHash))
      passengerMap.put(passengerHash, newPassenger.getLuggages());
    else
      passengerMap.replace(passengerHash, newPassenger.getLuggages());
  }

  @Override
  public List<Luggage> getPassengerLuggage(String passengerHash) throws NotFoundPassengerException {
    if (passengerMap.containsKey(passengerHash))
      return passengerMap.get(passengerHash);
    return new ArrayList<Luggage>();
  }

  public void clearPassengerMap() {
    passengerMap.clear();
  }
}
