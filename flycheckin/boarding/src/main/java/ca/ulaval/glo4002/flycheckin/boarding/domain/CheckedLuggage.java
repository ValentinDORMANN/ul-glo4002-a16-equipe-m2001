package ca.ulaval.glo4002.flycheckin.boarding.domain;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggage extends Luggage {

  private static final String LUGGAGE_DIMENSION_NOT_ALLOWED = "The size of luggage is over boundary";
  private static final String LUGGAGE_WEIGHT_NOT_ALLOWED = "The weight of luggage is over boundary";
  private static final int WEIGHT_LIMIT = 23;
  private static final int DIMENSION_LIMIT = 158;
  private static final String TYPE = "checked";
  private static final int MAX_LUGGAGE_ALLOWED = 3;
  private static final double SURPLUS_PRICE = 50;
  private static final int LUGGAGE_WITHOUT_ADDITIONAL_FEE = 1;
  private static final double BASE_PRICE = 0;
  
  public CheckedLuggage(LuggageDto luggageDto) {
    super(luggageDto);
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
  public boolean isType(String type) {
    return TYPE.equals(type);
  }
  
  @Override
  public int getMaxLuggageAllowed(){
    return MAX_LUGGAGE_ALLOWED;
  }

  @Override
  double getPrice(int luggageNumber) {
    return (luggageNumber <= LUGGAGE_WITHOUT_ADDITIONAL_FEE) ? BASE_PRICE : SURPLUS_PRICE;
  }
}
