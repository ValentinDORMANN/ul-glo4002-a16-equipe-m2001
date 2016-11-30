package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

public class LuggageFactory {

  private static final String CHECKED_LUGGAGE = "checked";
  private static final String CARRY_ON_LUGGAGE = "carry-on";

  public Luggage createLuggage(int linearDimension, int weight, String type) {
    Luggage luggage = null;
    switch (type) {
      case CHECKED_LUGGAGE:
        luggage = new CheckedLuggage(linearDimension, weight);
        break;
      case CARRY_ON_LUGGAGE:
        luggage = new CarryOnLuggage(linearDimension, weight);
        break;
      default:
        throw new UndefinedTypeLuggageException();
    }
    return luggage;
  }
}
