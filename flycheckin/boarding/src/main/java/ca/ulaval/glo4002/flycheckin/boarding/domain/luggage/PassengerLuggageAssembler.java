package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;

public class PassengerLuggageAssembler {

  public PassengerLuggage createPassengerLuggage(Passenger passenger) {
    return new PassengerLuggage(passenger.getPassengerHash(), passenger.getLuggages());
  }

  public List<Luggage> createLuggageList(PassengerLuggage passengerLuggage) {
    return passengerLuggage.getLuggage();
  }
}
