package ca.ulaval.glo4002.flycheckin.boarding.services.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotAllowableLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.passenger.PassengerService;

public class LuggageCheckinService {

  private PassengerService passengerService;
  private Passenger passenger;
  private LuggageDto luggageDto;

  public LuggageCheckinService() {
    this.passengerService = new PassengerService();
  }

  public LuggageCheckinService(PassengerService passengerService, Passenger passenger, LuggageDto luggageDto) {
    this.passengerService = passengerService;
    this.passenger = passenger;
    this.luggageDto = luggageDto;
  }

  public String assignLuggage(String passengerHash, LuggageDto luggageDto) throws BoardingModuleException {
    passenger = passengerService.getPassengerByHash(passengerHash);
    Luggage luggage = createCheckedLuggage(luggageDto);
    passenger.addLuggage(luggage);
    return luggage.getLuggageHash();
  }

  private Luggage createCheckedLuggage(LuggageDto luggageDto) throws NotAllowableLuggageException {
    CheckedLuggage checkedLuggage = new CheckedLuggage(luggageDto.linear_dimension, luggageDto.weight);
    return checkedLuggage;
  }
}
