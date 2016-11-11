package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public abstract class LuggageFactory {

  public abstract Luggage createLuggage(LuggageDto luggageDto);
}
