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

  public LuggageCheckinService() {
    this.passengerService = new PassengerService();
  }

  public LuggageCheckinService(PassengerService passengerService) {
    this.passengerService = passengerService;
  }

  public String assignLuggage(String passengerHash, LuggageDto luggageDto) throws BoardingModuleException {
    Passenger passenger = passengerService.getPassengerByHash(passengerHash);
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
