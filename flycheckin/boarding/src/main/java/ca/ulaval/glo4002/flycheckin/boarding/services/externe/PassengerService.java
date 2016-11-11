package ca.ulaval.glo4002.flycheckin.boarding.services.externe;

import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.InMemoryPassenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerService {

  private static final int SINGLE_INDEX = 0;
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

  public Passenger getPassengerByHash(String passengerHash) throws NotFoundPassengerException {
    try {
      return inMemoryPassenger.getPassengerByHash(passengerHash);
    } catch (NotFoundPassengerException ex) {
      Passenger passenger = getPassengerByHashInReservation(passengerHash);
      inMemoryPassenger.savePassenger(passenger);
      return passenger;
    }
  }

  public Passenger getPassengerByHashInReservation(String passengerHash) throws BoardingModuleException {
    ReservationDto reservationDto = reservationHttpClient.getReservationDtoFromReservation(passengerHash);
    return createPassengerFromDto(reservationDto);
  }

  private Passenger createPassengerFromDto(ReservationDto reservationDto) {
    return new Passenger(reservationDto.flight_number, reservationDto.flight_date,
        reservationDto.passengers[SINGLE_INDEX].passenger_hash, reservationDto.passengers[SINGLE_INDEX].seat_class);
  }
}
