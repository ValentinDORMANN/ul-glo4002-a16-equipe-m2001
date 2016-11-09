package ca.ulaval.glo4002.flycheckin.boarding.domain;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggage extends Luggage {

  private static final String LUGGAGE_DIMENSION_NOT_ALLOWED = "The size of luggage is over boundary";
  private static final String LUGGAGE_WEIGHT_NOT_ALLOWED = "The weight of luggage is over boundary";
  private static final int WEIGHT_LIMIT = 23;
  private static final int DIMENSION_LIMIT = 158;

  public CheckedLuggage(LuggageDto luggageDto) {
    super(luggageDto);
  }

  public void checkLuggageAllowable() throws ExcededCheckedLuggageException {
    checkLuggageWeight();
    checkLuggageDimension();
  }

  private void checkLuggageWeight() {
    if (getWeightInKg() > WEIGHT_LIMIT)
      throw new ExcededCheckedLuggageException(LUGGAGE_WEIGHT_NOT_ALLOWED);
  }

  private void checkLuggageDimension() {
    if (getDimensionInCm() > DIMENSION_LIMIT)
      throw new ExcededCheckedLuggageException(LUGGAGE_DIMENSION_NOT_ALLOWED);
  }
}
