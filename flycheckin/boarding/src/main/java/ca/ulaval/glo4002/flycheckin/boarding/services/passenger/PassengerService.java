package ca.ulaval.glo4002.flycheckin.boarding.services.passenger;

import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.PassengerFactory;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.PassengerLuggagePersistence;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerService {

  private static final int SINGLE_INDEX = 0;
  private ReservationHttpClient reservationHttpClient;
  private PassengerLuggagePersistence passengerLuggagePersistence;
  private PassengerFactory passengerFactory;

  public PassengerService() {
    reservationHttpClient = new ReservationHttpClient();
    passengerLuggagePersistence = new PassengerLuggagePersistence();
    passengerFactory = new PassengerFactory();
  }

  public PassengerService(ReservationHttpClient reservationHttpClient,
      PassengerLuggagePersistence passengerLuggagePersistence, PassengerFactory passengerFactory) {
    this.reservationHttpClient = reservationHttpClient;
    this.passengerLuggagePersistence = passengerLuggagePersistence;
    this.passengerFactory = passengerFactory;
  }

  public Passenger getPassengerByHash(String passengerHash) throws BoardingModuleException {
    Passenger passenger = getPassengerByHashInReservation(passengerHash);
    passengerLuggagePersistence.savePassengerLuggage(passenger);
    return passenger;
  }

  private Passenger getPassengerByHashInReservation(String passengerHash) throws BoardingModuleException {
    ReservationDto reservationDto = reservationHttpClient.getReservationDtoFromReservation(passengerHash);
    return createPassengerFromDto(reservationDto);
  }

  private Passenger createPassengerFromDto(ReservationDto reservationDto) {
    return passengerFactory.createPassenger(reservationDto.flight_number, reservationDto.flight_date,
        reservationDto.passengers[SINGLE_INDEX].passenger_hash, reservationDto.passengers[SINGLE_INDEX].seat_class,
        reservationDto.passengers[SINGLE_INDEX].isVip);
  }
}
