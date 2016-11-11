package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import ca.ulaval.glo4002.flycheckin.boarding.domain.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.InMemoryPassenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.PassengerService;

public class LuggageCheckinService {

  private InMemoryPassenger inMemoryPassenger;
  private PassengerService passengerService;

  public LuggageCheckinService() {
    this.inMemoryPassenger = new InMemoryPassenger();
    this.passengerService = new PassengerService();
  }

  public LuggageCheckinService(InMemoryPassenger inMemoryPassenger, PassengerService passengerService) {
    this.inMemoryPassenger = inMemoryPassenger;
    this.passengerService = passengerService;
  }

  public String assignLuggage(String passengerHash, LuggageDto luggageDto) throws BoardingModuleException {
    Passenger passenger = passengerService.getPassengerByHash(passengerHash);
    Luggage luggage = createCheckedLuggage(luggageDto);
    passenger.addLuggage(luggage);
    return luggage.getLuggageHash();
  }

  private Passenger getPassenger(String passengerHash) throws NotFoundPassengerException {
    try {
      return inMemoryPassenger.getPassengerByHash(passengerHash);
    } catch (NotFoundPassengerException ex) {
      return getPassengerFromReservationAndInsertInMemory(passengerHash);
    }
  }

  private Passenger getPassengerFromReservationAndInsertInMemory(String passengerHash)
      throws NotFoundPassengerException {
    Passenger passenger = passengerService.getPassengerByHashInReservation(passengerHash);
    inMemoryPassenger.savePassenger(passenger);
    return passenger;
  }


  private Luggage createCheckedLuggage(LuggageDto luggageDto) throws ExcededLuggageException {
    CheckedLuggage checkedLuggage = new CheckedLuggage(luggageDto.linear_dimension, luggageDto.linear_dimension_unit,
        luggageDto.weight, luggageDto.weight_unit);
    checkedLuggage.checkLuggageAllowable();
    return checkedLuggage;
  }
}
