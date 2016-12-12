package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

public class LuggageFactory {

  private static final String REGULAR_LUGGAGE = "checked";
  private static final String CARRY_ON_LUGGAGE = "carry-on";
  private static final String CARRY_ON_CATEGORY = "standard";

  public Luggage createLuggage(int linearDimension, int weight, String type) {
    Luggage luggage = null;
    switch (type) {
      case REGULAR_LUGGAGE:
        luggage = new CheckedLuggage(linearDimension, weight, REGULAR_LUGGAGE);
        break;
      case CARRY_ON_LUGGAGE:
        luggage = new CarryOnLuggage(linearDimension, weight, CARRY_ON_CATEGORY);
        break;
      default:
        throw new UndefinedTypeLuggageException();
    }
    return luggage;
  }
}
