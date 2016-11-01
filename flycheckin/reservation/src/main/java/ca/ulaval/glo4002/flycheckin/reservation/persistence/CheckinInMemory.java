package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ApplicationException;

public class CheckinInMemory {

  private static int checkinId = 100;
  private static Map<Integer, String> checkinMap = new HashMap<Integer, String>();

  public int doPassengerCheckin(String passengerHash) {
    if (checkinMap.containsValue(passengerHash)) {
      throw new ApplicationException();
    }
    checkinMap.put(checkinId++, passengerHash);
    return checkinId;
  }
}
