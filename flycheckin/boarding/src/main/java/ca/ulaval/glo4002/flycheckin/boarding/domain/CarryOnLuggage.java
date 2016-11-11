package ca.ulaval.glo4002.flycheckin.boarding.domain;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CarryOnLuggage extends Luggage {

  private static final String LUGGAGE_DIMENSION_NOT_ALLOWED = "The size of luggage is over boundary";
  private static final String LUGGAGE_WEIGHT_NOT_ALLOWED = "The weight of luggage is over boundary";
  private static final int WEIGHT_LIMIT = 10;
  private static final int DIMENSION_LIMIT = 118;
  private static final String TYPE = "carry-on";
  private static final int MAX_LUGGAGE_ALLOWED = 1;
  private static final int BASE_PRICE = 30;
  
  public CarryOnLuggage(int linearDimension, String linearDimensionUnit, int weight, String weightUnit) {
    super(linearDimension, linearDimensionUnit, weight, weightUnit);
  }
  
  @Override
  public void checkLuggageAllowable() throws ExcededLuggageException {
    checkLuggageWeight();
    checkLuggageDimension();
  }

  private void checkLuggageWeight() {
    if (getWeightInKg() > WEIGHT_LIMIT)
      throw new ExcededLuggageException(LUGGAGE_WEIGHT_NOT_ALLOWED);
  }

  private void checkLuggageDimension() {
    if (getDimensionInCm() > DIMENSION_LIMIT)
      throw new ExcededLuggageException(LUGGAGE_DIMENSION_NOT_ALLOWED);
  }
  
  @Override
  double calculatePrice(int luggageNumber) {
    return BASE_PRICE;
  }
  
  @Override
  public boolean isType(String type) {
    return TYPE.equals(type);
  }
  
  @Override
  public int getMaxLuggageAllowed(){
    return MAX_LUGGAGE_ALLOWED;
  }
}
