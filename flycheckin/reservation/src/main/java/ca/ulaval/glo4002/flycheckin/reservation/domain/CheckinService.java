package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinService {

  private static final String MESSAGE_ERROR = "Passenger Information incorrect";

  private CheckinInMemory checkinInMemory;
  private HibernateReservation hibernateReservation;

  public CheckinService(CheckinInMemory checkinInMemory, HibernateReservation hibernateReservation) {
    this.checkinInMemory = checkinInMemory;
    this.hibernateReservation = hibernateReservation;
  }

  public int saveCheckin(CheckinDto checkinDto) throws ReservationModuleException {
    String passengerHash = checkinDto.passenger_hash;
    String by = checkinDto.by;
    boolean isVip = checkinDto.vip;
    Reservation reservation = hibernateReservation.findReservationByPassengerHash(passengerHash);

    reservation.validateCheckinPeriod(by);
    if (reservation.isPassengerInfosValid(passengerHash)) {
      reservation.changePassengerVipStatus(passengerHash, isVip);
      if (isVip)
        hibernateReservation.update(reservation);
      return checkinInMemory.doPassengerCheckin(passengerHash);
    }
    throw new ReservationModuleException(MESSAGE_ERROR);
  }

  public void isCheckInPassengerDone(String passengerHash) throws NotCheckedinException {
    checkinInMemory.isCheckinDone(passengerHash);
  }
}