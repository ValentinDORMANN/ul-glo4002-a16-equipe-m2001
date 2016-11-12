package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import ca.ulaval.glo4002.flycheckin.boarding.domain.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.PassengerService;

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

  private Luggage createCheckedLuggage(LuggageDto luggageDto) throws ExcededCheckedLuggageException {
    CheckedLuggage checkedLuggage = new CheckedLuggage(luggageDto.linear_dimension, luggageDto.linear_dimension_unit,
        luggageDto.weight, luggageDto.weight_unit);
    checkedLuggage.checkLuggageAllowable();
    return checkedLuggage;
  }
}
