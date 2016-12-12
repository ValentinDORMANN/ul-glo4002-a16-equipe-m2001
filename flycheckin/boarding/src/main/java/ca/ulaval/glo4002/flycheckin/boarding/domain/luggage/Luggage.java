package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import java.util.UUID;

public abstract class Luggage {

  protected static final String LUGGAGE_DIMENSION_NOT_ALLOWED = "The size of luggage is over boundary";
  protected static final String LUGGAGE_WEIGHT_NOT_ALLOWED = "The weight of luggage is over boundary";
  private static final double FIRST_STAGE_PENALTY_FEE_IN_PERCENTAGE = 10;
  private static final double SECOND_STAGE_PENALTY_FEE_IN_PERCENTAGE = 21;
  private int dimensionInCm;
  private int weightInKg;
  private String luggageHash;
  private double price;
  private double penaltyFee;
  private String category;

  public Luggage(String category, int linearDimension, int weight) throws IllegalArgumentException {
    this.luggageHash = UUID.randomUUID().toString();
    this.category = category;
    this.dimensionInCm = linearDimension;
    this.weightInKg = weight;
    this.penaltyFee = 0;
  }

  public abstract void calculatePrice();

  public abstract void assignLuggageFree();

  public abstract void verifyAllowableDimension() throws NotAllowableLuggageException;

  public int getDimensionInCm() {
    return dimensionInCm;
  }

  public abstract void verifyAllowableWeight(double limit) throws NotAllowableLuggageException;

  public int getWeightInKg() {
    return weightInKg;
  }

  public String getLuggageHash() {
    return luggageHash;
  }

  public void setPrice(double price) {
    this.price = price + price * penaltyFee / 100;
  }

  public double getPrice() {
    return price;
  }

  public boolean isFree() {
    return this.price == 0 ? true : false;
  }

  public String getCategory() {
    return category;
  }

  public boolean hasSameCategory(Luggage luggage) {
    return category.equals(luggage.category);
  }

  protected void applyPenalty() {
    if (penaltyFee == FIRST_STAGE_PENALTY_FEE_IN_PERCENTAGE)
      penaltyFee = SECOND_STAGE_PENALTY_FEE_IN_PERCENTAGE;
    else
      penaltyFee = FIRST_STAGE_PENALTY_FEE_IN_PERCENTAGE;
  }

}
