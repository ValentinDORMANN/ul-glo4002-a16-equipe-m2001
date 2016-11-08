package ca.ulaval.glo4002.flycheckin.boarding.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AmsMapClient {

  private static final String BOEING = "boeing-777-300";
  private static final String FLIGHT_BOEING = "QK-432";
  private static final String DASH = "dash-8";
  private static final String FLIGHT_DASH = "QK-918";
  private static final String AIRBUS = "a320";
  private static final String FLIGHT_AIRBUS = "NK-750";
  private static final String[] MODELS = { AIRBUS, DASH, BOEING };
  private static Map<String, String> associationFlightPlane = new HashMap<String, String>();

  public AmsMapClient() {
    if (associationFlightPlane.isEmpty()) {
      associationFlightPlane.put(FLIGHT_BOEING, BOEING);
      associationFlightPlane.put(FLIGHT_DASH, DASH);
      associationFlightPlane.put(FLIGHT_AIRBUS, AIRBUS);
    }
  }

  public String getPlaneModelByFlightNumber(String flightNumber) {
    if (associationFlightPlane.containsKey(flightNumber))
      return associationFlightPlane.get(flightNumber);
    return getRandomModel();
  }

  private String getRandomModel() {
    return MODELS[randInt()];
  }

  private int randInt() {
    Random rand = new Random();
    int randomNum = rand.nextInt(MODELS.length);
    return randomNum;
  }
}
