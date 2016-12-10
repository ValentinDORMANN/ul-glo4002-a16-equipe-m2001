package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinService {

  private static final String MESSAGE_ERROR = "Passenger Information incorrect";
  private CheckinInMemory checkinInMemory;
  private ReservationRegistry reservationRegistry;

  public CheckinService(CheckinInMemory checkinInMemory, ReservationRegistry reservationRegistry) {
    this.checkinInMemory = checkinInMemory;
    this.reservationRegistry = reservationRegistry;
  }

  public int saveCheckin(CheckinDto checkinDto) throws ReservationModuleException {
    String by = checkinDto.by;
    String hash = checkinDto.passenger_hash;
    boolean isVip = checkinDto.vip;
    validateCheckin(by, hash, isVip);
    return checkinInMemory.doPassengerCheckin(hash);
  }

  private void validateCheckin(String by, String hash, boolean isVip) {
    Reservation reservation = reservationRegistry.getReservationByPassengerHash(hash);
    reservation.validateCheckinPeriod(by);
    reservation.changePassengerVipStatus(hash, isVip);
    if (!reservation.isPassengerInfosValid(hash))
      throw new ReservationModuleException(MESSAGE_ERROR);
  }

  public void isCheckInPassengerDone(String passengerHash) throws NotCheckedinException {
    checkinInMemory.isCheckinDone(passengerHash);
  }
}
