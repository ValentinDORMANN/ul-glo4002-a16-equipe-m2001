package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.FlyCheckinApplicationException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.InMemoryCheckin;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.InMemoryReservation;

public class CheckinService {

  private static final String MSG_ERROR = "Passenger Information incorrect";
  private InMemoryCheckin checkinInMemory;
  private InMemoryReservation reservationInMemory;

  public CheckinService() {
    this.checkinInMemory = new InMemoryCheckin();
    this.reservationInMemory = new InMemoryReservation();
  }

  public CheckinService(InMemoryCheckin checkinInMemory, InMemoryReservation reservationInMemory) {
    this.checkinInMemory = checkinInMemory;
    this.reservationInMemory = reservationInMemory;
  }

  public int saveCheckin(CheckinDto checkinDto) throws FlyCheckinApplicationException {
    String hash = checkinDto.passenger_hash;
    String by = checkinDto.by;
    Reservation reservation = reservationInMemory.getReservationByPassengerHash(hash);
    reservation.validateCheckinPeriod(by);
    if (reservation.isPassengerInfosValid(hash))
      return checkinInMemory.doPassengerCheckin(hash);
    throw new FlyCheckinApplicationException(MSG_ERROR);
  }
}
