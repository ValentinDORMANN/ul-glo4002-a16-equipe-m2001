package ca.ulaval.glo4002.flycheckin.boarding.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AmsMapClient {

  private static final String MODEL_BOEING_PLANE = "boeing-777-300";
  private static final String FLIGHT_NUMBER_BOEING = "QK-432";
  private static final String MODEL_DASH_PLANE = "dash-8";
  private static final String FLIGHT_NUMBER_DASH = "QK-918";
  private static final String MODEL_AIRBUS_PLANE = "a320";
  private static final String FLIGHT_NUMBER_AIRBUS = "NK-750";
  private static final String[] MODELS = { FLIGHT_NUMBER_AIRBUS, FLIGHT_NUMBER_DASH , MODEL_BOEING_PLANE };
  private static Map<String, String> associationFlightPlane = new HashMap<String, String>();

  public AmsMapClient() {
    if (associationFlightPlane.isEmpty()) {
      associationFlightPlane.put(FLIGHT_NUMBER_BOEING, MODEL_BOEING_PLANE);
      associationFlightPlane.put(FLIGHT_NUMBER_DASH, MODEL_DASH_PLANE);
      associationFlightPlane.put(FLIGHT_NUMBER_AIRBUS, MODEL_AIRBUS_PLANE);
    }
  }

  public String getPlaneModelByFlightNumber(String flightNumber) {
    if (associationFlightPlane.containsKey(flightNumber))
      return associationFlightPlane.get(flightNumber);
    return getRandomModel();
  }

  private String getRandomModel() {
    return MODELS[randomIndex()];
  }

  private int randomIndex() {
    Random random = new Random();
    int randomIndex = random.nextInt(MODELS.length);
    return randomIndex;
  }
}
