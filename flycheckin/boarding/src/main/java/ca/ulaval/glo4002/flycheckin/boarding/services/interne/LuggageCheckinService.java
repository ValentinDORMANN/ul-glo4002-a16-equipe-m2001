package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import ca.ulaval.glo4002.flycheckin.boarding.domain.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.InMemoryPassenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.PassengerService;

public class LuggageCheckinService {

  private static final String LUGGAGE_NOT_ALLOWED = "dimension or weight is not allowed";
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
    Passenger passenger = getPassenger(passengerHash);
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

  private Luggage createCheckedLuggage(LuggageDto luggageDto) {
    CheckedLuggage checkedLuggage = new CheckedLuggage(luggageDto);
    if (checkedLuggage.isAllowed())
      return checkedLuggage;
    throw new ExcededCheckedLuggageException(LUGGAGE_NOT_ALLOWED);
  }
}
