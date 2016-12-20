package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Luggage {

  protected static final String LUGGAGE_DIMENSION_NOT_ALLOWED = "The size of luggage is over boundary";
  protected static final String LUGGAGE_WEIGHT_NOT_ALLOWED = "The weight of luggage is over boundary";
  private static final double FIRST_STAGE_PENALTY_FEE_IN_PERCENTAGE = 10;
  private static final double SECOND_STAGE_PENALTY_FEE_IN_PERCENTAGE = 21;
  @Id
  @Column(name = "luggageHash")
  private String luggageHash;
  @Column(name = "dimensionInCm")
  private int dimensionInCm;
  @Column(name = "weightInKg")
  private int weightInKg;
  @Column(name = "price")
  private double price;
  @Column(name = "penaltyFee")
  private double penaltyFee;

  public Luggage(int linearDimension, int weight) throws IllegalArgumentException {
    this.luggageHash = UUID.randomUUID().toString();
    this.dimensionInCm = linearDimension;
    this.weightInKg = weight;
    this.penaltyFee = 0;
  }

  public Luggage() {
  }

  public abstract boolean isType(String type);

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

  protected void applyPenalty() {
    if (penaltyFee == FIRST_STAGE_PENALTY_FEE_IN_PERCENTAGE)
      penaltyFee = SECOND_STAGE_PENALTY_FEE_IN_PERCENTAGE;
    else
      penaltyFee = FIRST_STAGE_PENALTY_FEE_IN_PERCENTAGE;
  }

}
