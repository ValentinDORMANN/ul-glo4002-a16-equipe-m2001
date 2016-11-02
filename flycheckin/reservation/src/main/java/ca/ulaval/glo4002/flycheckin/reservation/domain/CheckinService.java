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

  public CheckinService(CheckinInMemory checkinInMemory, ReservationInMemory reservationInMemory) {
    this.checkinInMemory = checkinInMemory;
    this.reservationInMemory = reservationInMemory;
  }

  public int saveCheckin(CheckinDto checkinDto) throws FlyCheckinApplicationException {
    String hash = checkinDto.passenger_hash;
    Reservation reservation = reservationInMemory.getReservationByPassengerHash(hash);
    reservation.validateSelfCheckinPeriod(checkinDto.by);
    if (reservation.getPassengerFromHash(hash).isValid())
      return checkinInMemory.doPassengerCheckin(hash);
    throw new FlyCheckinApplicationException("Passenger Information incorrect");
  }

}
