package ca.ulaval.glo4002.flycheckin.boarding.services.passenger;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.client.CheckinHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageRegistry;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.PassengerLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.PassengerLuggageAssembler;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.PassengerFactory;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.PassengerLuggageHibernate;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerService {

  private static final int SINGLE_INDEX = 0;

  private ReservationHttpClient reservationHttpClient;
  private LuggageRegistry luggageRegistry;
  private PassengerFactory passengerFactory;
  private CheckinHttpClient checkinHttpClient;
  private PassengerLuggageAssembler passengerLuggageAssembler;

  public PassengerService() {
    reservationHttpClient = new ReservationHttpClient();
    luggageRegistry = new PassengerLuggageHibernate();
    passengerFactory = new PassengerFactory();
    checkinHttpClient = new CheckinHttpClient();
    passengerLuggageAssembler = new PassengerLuggageAssembler();
  }

  public PassengerService(ReservationHttpClient reservationHttpClient, LuggageRegistry luggageRegistry,
      PassengerFactory passengerFactory, CheckinHttpClient checkinHttpClient,
      PassengerLuggageAssembler passengerLuggageAssembler) {
    this.reservationHttpClient = reservationHttpClient;
    this.luggageRegistry = luggageRegistry;
    this.passengerFactory = passengerFactory;
    this.checkinHttpClient = checkinHttpClient;
    this.passengerLuggageAssembler = passengerLuggageAssembler;
  }

  public Passenger getCheckedPassengerByHash(String passengerHash) throws BoardingModuleException {
    Passenger passenger = getPassengerByHash(passengerHash);
    passenger.isCheckinDone(checkinHttpClient);
    return passenger;
  }

  public Passenger getPassengerByHash(String passengerHash) throws BoardingModuleException {
    Passenger passenger = getPassengerByHashInReservation(passengerHash);
    PassengerLuggage passengerLuggage = luggageRegistry.getPassengerLuggage(passengerHash);
    List<Luggage> luggageList = passengerLuggageAssembler.createLuggageList(passengerLuggage);

    for (Luggage luggage : luggageList)
      passenger.getLuggages().add(luggage);

    return passenger;
  }

  private Passenger getPassengerByHashInReservation(String passengerHash) throws BoardingModuleException {
    ReservationDto reservationDto = reservationHttpClient.getReservationDtoFromReservation(passengerHash);
    return createPassengerFromDto(reservationDto);
  }

  private Passenger createPassengerFromDto(ReservationDto reservationDto) {
    return passengerFactory.createPassenger(reservationDto.flight_number, reservationDto.flight_date,
        reservationDto.passengers[SINGLE_INDEX].passenger_hash, reservationDto.passengers[SINGLE_INDEX].seat_class,
        reservationDto.passengers[SINGLE_INDEX].isVip, reservationDto.passengers[SINGLE_INDEX].child);
  }
}
