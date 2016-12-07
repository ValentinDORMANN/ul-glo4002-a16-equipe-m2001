package ca.ulaval.glo4002.flycheckin.boarding.services.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.client.CheckinHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageFactory;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.PassengerLuggagePersistence;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.passenger.PassengerService;

public class LuggageCheckinService {

  private PassengerService passengerService;
  private Passenger passenger;
  private LuggageFactory luggageFactory;
  private PassengerLuggagePersistence luggagePersistence;
  private CheckinHttpClient checkinHttpClient;

  public LuggageCheckinService() {
    this.passengerService = new PassengerService();
    this.luggageFactory = new LuggageFactory();
    this.luggagePersistence = new PassengerLuggagePersistence();
    this.checkinHttpClient = new CheckinHttpClient();
  }

  public LuggageCheckinService(PassengerService passengerService, Passenger passenger, LuggageFactory luggageFactory,
      PassengerLuggagePersistence luggagePersistence, CheckinHttpClient checkinHttpClient) {
    this.passengerService = passengerService;
    this.passenger = passenger;
    this.luggageFactory = luggageFactory;
    this.luggagePersistence = luggagePersistence;
    this.checkinHttpClient = checkinHttpClient;
  }

  public String assignLuggage(String passengerHash, LuggageDto luggageDto) throws BoardingModuleException {
    passenger = passengerService.getPassengerByHash(passengerHash);
    checkinHttpClient.verifyCheckinFromReservation(passengerHash);
    Luggage luggage = luggageFactory.createLuggage(luggageDto.linear_dimension, luggageDto.weight, luggageDto.type);
    passenger.addLuggage(luggage);
    luggagePersistence.savePassengerLuggage(passenger);
    return luggage.getLuggageHash();
  }
}
