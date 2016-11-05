package ca.ulaval.glo4002.flycheckin.boarding.ams;

import java.util.Random;

public class ServiceAms {

  private static final String BOEING = "boeing-777-300";
  private static final String FLIGHT_BOEING = "QK-432";
  private static final String DASH = "dash-8";
  private static final String FLIGHT_DASH = "QK-918";
  private static final String AIRBUS = "a320";
  private static final String FLIGHT_AIRBUS = "NK-750";
  private static final String[] MODELS = { AIRBUS, DASH, BOEING };

  public String getPlaneModelByFlightNumber(String flightNumber) {
    switch (flightNumber) {
      case FLIGHT_BOEING:
        return BOEING;
      case FLIGHT_DASH:
        return DASH;
      case FLIGHT_AIRBUS:
        return AIRBUS;
      default:
        return getRandomModel();
    }
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
