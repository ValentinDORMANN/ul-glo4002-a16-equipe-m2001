package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.client.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;

public interface LuggageRegistry {

  public void savePassengerLuggage(Passenger newPassenger);
  
  public List<Luggage> getPassengerLuggage(String passengerHash) throws NotFoundPassengerException;
}
