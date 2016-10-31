package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.CheckinDto;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;

public class CheckinService {

  private CheckinInMemory checkinInMemory;

  public CheckinService() {
    this.checkinInMemory = new CheckinInMemory();
  }
  
  public int saveCheckin(CheckinDto checkindto) throws NotFoundReservationException {
    return checkinInMemory.doPassengerCheckin(checkindto.passenger_hash);
  }
}
