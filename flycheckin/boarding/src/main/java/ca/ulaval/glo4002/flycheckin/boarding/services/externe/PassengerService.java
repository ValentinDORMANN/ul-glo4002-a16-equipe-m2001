package ca.ulaval.glo4002.flycheckin.boarding.services.externe;

import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.InMemoryPassenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerService {

  private ReservationHttpClient reservationHttpClient;
  private InMemoryPassenger inMemoryPassenger;

  public PassengerService() {
    reservationHttpClient = new ReservationHttpClient();
    inMemoryPassenger = new InMemoryPassenger();
  }

  public PassengerService(ReservationHttpClient reservationHttpClient, InMemoryPassenger inMemoryPassenger) {
    this.reservationHttpClient = reservationHttpClient;
    this.inMemoryPassenger = inMemoryPassenger;
  }

  public Passenger getPassenger(String passengerHash) throws NotFoundPassengerException {
    return inMemoryPassenger.getPassengerByHash(passengerHash);
  }

  public Passenger getPassengerByHashInReservation(String passengerHash) throws BoardingModuleException {
    ReservationDto reservationDto = reservationHttpClient.getReservationDtoFromReservation(passengerHash);
    return new Passenger(reservationDto);
  }
}
