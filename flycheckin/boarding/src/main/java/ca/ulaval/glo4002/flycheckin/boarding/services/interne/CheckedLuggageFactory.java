package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Luggage;

public class CheckedLuggageFactory extends LuggageFactory {

  public final static String CHECKED_LUGGAGE = "checked";
  public final static String CARRY_ON_LUGGAGE = "carry-on";
  
  @Override
  public Luggage createLuggage(String luggageType) {
    Luggage luggage = null;
    switch (luggageType) {
      case 
    }
    return luggage;
  }
}
