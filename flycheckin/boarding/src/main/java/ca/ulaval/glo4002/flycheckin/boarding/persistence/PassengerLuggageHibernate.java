package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageRegistry;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.PassengerLuggage;

public class PassengerLuggageHibernate implements LuggageRegistry {

  public void savePassengerLuggage(PassengerLuggage passengerLugage) {
  }

  public PassengerLuggage getPassengerLuggage(String passengerHash) {
    return new PassengerLuggage();
  }

}
