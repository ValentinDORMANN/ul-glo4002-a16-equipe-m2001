package ca.ulaval.glo4002.flycheckin.boarding.domain;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggage extends Luggage {

  private static final int WEIGHT_LIMIT = 50;
  private static final int DIMENSION_LIMIT = 62;

  public CheckedLuggage(LuggageDto luggageDto) {
    super(luggageDto);
  }

  public boolean isAllowed() {
    return isWeightAllowed() && isDimensionAllowed();
  }

  private boolean isWeightAllowed() {
    return getWeightInPound() < WEIGHT_LIMIT;
  }

  private boolean isDimensionAllowed() {
    return getDimensionInInch() < DIMENSION_LIMIT;
  }
}
