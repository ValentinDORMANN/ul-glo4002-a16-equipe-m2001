package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import javax.persistence.Entity;

@Entity
public class RegularLuggage extends Luggage {

  private static final int DIMENSION_LIMIT = 158;
  private static final double SURPLUS_PRICE = 50;

  public RegularLuggage(String category, int linearDimension, int weight) {
    super(category, linearDimension, weight);
  }

  public RegularLuggage() {
    super();
  }

  @Override
  public void calculatePrice() {
    this.setPrice(SURPLUS_PRICE);
  }

  @Override
  public void verifyAllowableDimension() throws NotAllowableLuggageException {
    if (getDimensionInCm() > DIMENSION_LIMIT)
      applyPenalty();
  }

  @Override
  public void verifyAllowableWeight(double limit) throws NotAllowableLuggageException {
    if (getWeightInKg() > limit)
      applyPenalty();
  }

  @Override
  public void assignLuggageFree() {
    this.setPrice(0);
  }
}
