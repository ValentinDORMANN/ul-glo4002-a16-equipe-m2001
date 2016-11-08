package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

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

  public void assignLuggage(String passengerHash, LuggageDto luggageDto) {

  }
}
