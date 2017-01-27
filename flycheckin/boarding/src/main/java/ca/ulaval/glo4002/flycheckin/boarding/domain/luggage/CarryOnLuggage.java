package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import javax.persistence.Entity;

@Entity
public class CarryOnLuggage extends Luggage {

  private static final int WEIGHT_LIMIT = 10;
  private static final int DIMENSION_LIMIT = 118;
  private static final int BASE_PRICE = 30;

  public CarryOnLuggage() {
    super();
  }

  public CarryOnLuggage(String category, int linearDimension, int weight) {
    super(category, linearDimension, weight);
  }

  @Override
  public void verifyAllowableWeight(double limit) throws NotAllowableLuggageException {
    if (getWeightInKg() > WEIGHT_LIMIT)
      throw new NotAllowableLuggageException(LUGGAGE_WEIGHT_NOT_ALLOWED);
  }

  @Override
  public void verifyAllowableDimension() throws NotAllowableLuggageException {
    if (getDimensionInCm() > DIMENSION_LIMIT)
      throw new NotAllowableLuggageException(LUGGAGE_DIMENSION_NOT_ALLOWED);
  }

  @Override
  public void calculatePrice() {
    setPrice(BASE_PRICE);
  }

  @Override
  public void assignLuggageFree() {
    setPrice(BASE_PRICE);
  }
}
