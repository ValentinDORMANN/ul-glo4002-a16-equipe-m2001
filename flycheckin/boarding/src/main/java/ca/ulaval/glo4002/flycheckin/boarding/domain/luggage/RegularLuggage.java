package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

public class RegularLuggage extends Luggage {

  private static final String TYPE = "checked";
  private static final int DIMENSION_LIMIT = 158;
  private static final double SURPLUS_PRICE = 50;

  public RegularLuggage(String category, int linearDimension, int weight) {
    super(category, linearDimension, weight);
  }

  @Override
  public void calculatePrice() {
    this.setPrice(SURPLUS_PRICE);
  }

  @Override
  public boolean isType(String type) {
    return TYPE.equals(type);
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
