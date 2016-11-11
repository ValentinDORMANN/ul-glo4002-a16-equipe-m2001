package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Luggage;

public abstract class LuggageFactory {

  public abstract Luggage createLuggage(String luggageType);
}
