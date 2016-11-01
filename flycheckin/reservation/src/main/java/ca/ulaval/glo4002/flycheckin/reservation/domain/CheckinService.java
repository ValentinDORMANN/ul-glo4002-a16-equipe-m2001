package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.FlyCheckinApplicationException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class CheckinService {

  private CheckinInMemory checkinInMemory;
  private ReservationInMemory reservationInMemory;

  public CheckinService() {
    this.checkinInMemory = new CheckinInMemory();
    this.reservationInMemory = new ReservationInMemory();
  }

  public int saveCheckin(CheckinDto checkindto) throws FlyCheckinApplicationException {
    if (reservationInMemory.getPassengerByPassengerHash(checkindto.passenger_hash) == null)
      throw new FlyCheckinApplicationException("Passenger Not Found");
    return checkinInMemory.doPassengerCheckin(checkindto.passenger_hash);
  }

}
