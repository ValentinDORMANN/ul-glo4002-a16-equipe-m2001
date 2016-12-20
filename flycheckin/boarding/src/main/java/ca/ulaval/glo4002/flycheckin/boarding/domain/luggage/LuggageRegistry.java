package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;

public interface LuggageRegistry {

  public void savePassengerLuggage(PassengerLuggage passengerLuggage);

  public List<Luggage> getPassengerLuggage(String passengerHash) throws NotFoundPassengerException;
}
