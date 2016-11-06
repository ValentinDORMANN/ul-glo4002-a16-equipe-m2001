package ca.ulaval.glo4002.flycheckin.boarding.services.externe;

import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.FlyCheckinApplicationException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerService {

  private ReservationHttpClient reservationHttpClient;

  public PassengerService() {
    this.reservationHttpClient = new ReservationHttpClient();
  }

  public PassengerService(ReservationHttpClient reservationHttpClient) {
    this.reservationHttpClient = reservationHttpClient;
  }

  public Passenger getPassengerByHashInReservation(String passengerHash) throws FlyCheckinApplicationException {
    ReservationDto reservationDto = reservationHttpClient.getReservationDtoFromReservation(passengerHash);
    return new Passenger(reservationDto);
  }
}
