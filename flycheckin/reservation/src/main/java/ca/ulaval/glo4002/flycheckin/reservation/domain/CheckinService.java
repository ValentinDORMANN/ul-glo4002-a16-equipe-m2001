package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinService {

  private static final String MESSAGE_ERROR = "Passenger Information incorrect";
  private CheckinInMemory checkinInMemory;
  private ReservationInMemory reservationInMemory;

  public CheckinService(CheckinInMemory checkinInMemory, ReservationInMemory reservationInMemory) {
    this.checkinInMemory = checkinInMemory;
    this.reservationInMemory = reservationInMemory;
  }

  public int saveCheckin(CheckinDto checkinDto) throws ReservationModuleException {
    String hash = checkinDto.passenger_hash;
    String by = checkinDto.by;
    boolean isVip = checkinDto.vip;
    Reservation reservation = reservationInMemory.getReservationByPassengerHash(hash);
    reservation.validateCheckinPeriod(by);
    reservation.changePassengerVipStatus(hash, isVip);
    if (reservation.isPassengerInfosValid(hash))
      return checkinInMemory.doPassengerCheckin(hash);
    throw new ReservationModuleException(MESSAGE_ERROR);
  }

  public void isCheckInPassengerDone(String passengerHash) throws NotCheckedinException {
    checkinInMemory.isCheckinDone(passengerHash);
  }
}
