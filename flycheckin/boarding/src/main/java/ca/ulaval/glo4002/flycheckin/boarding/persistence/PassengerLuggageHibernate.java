package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageRegistry;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.PassengerLuggage;

public class PassengerLuggageHibernate implements LuggageRegistry {

  public void savePassengerLuggage(PassengerLuggage passengerLugage) {
  }

  public List<Luggage> getPassengerLuggage(String passengerHash) {
    return new ArrayList<Luggage>();
  }

}
