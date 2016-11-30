package ca.ulaval.glo4002.flycheckin.boarding.services.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageFactory;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.passenger.PassengerService;

public class LuggageCheckinService {

  private PassengerService passengerService;
  private Passenger passenger;
  private LuggageFactory luggageFactory;

  public LuggageCheckinService() {
    this.passengerService = new PassengerService();
    this.luggageFactory = new LuggageFactory();
  }

  public LuggageCheckinService(PassengerService passengerService, Passenger passenger, LuggageFactory luggageFactory) {
    this.passengerService = passengerService;
    this.passenger = passenger;
    this.luggageFactory = luggageFactory;
  }

  public String assignLuggage(String passengerHash, LuggageDto luggageDto) throws BoardingModuleException {
    passenger = passengerService.getPassengerByHash(passengerHash);
    Luggage luggage = luggageFactory.createLuggage(luggageDto.linear_dimension, luggageDto.weight, luggageDto.type);
    passenger.addLuggage(luggage);
    return luggage.getLuggageHash();
  }
}
