package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.FlyCheckinApplicationException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;

public class CheckinService {

  private static final String MSG_ERROR = "Passenger Information incorrect";
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
    String by = checkinDto.by;
    Reservation reservation = reservationInMemory.getReservationByPassengerHash(hash);
    reservation.validateCheckinPeriod(by);
    if (reservation.getPassengerByHash(hash).isValid())
      return checkinInMemory.doPassengerCheckin(hash);
    throw new FlyCheckinApplicationException(MSG_ERROR);
  }

}
