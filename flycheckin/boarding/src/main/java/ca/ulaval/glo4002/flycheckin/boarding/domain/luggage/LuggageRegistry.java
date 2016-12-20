package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.persistence.NotFoundPassengerException;

public interface LuggageRegistry {

  public void savePassengerLuggage(PassengerLuggage passengerLuggage);

  public PassengerLuggage getPassengerLuggage(String passengerHash) throws NotFoundPassengerException;
}
