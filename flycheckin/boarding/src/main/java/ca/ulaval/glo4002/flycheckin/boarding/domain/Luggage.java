package ca.ulaval.glo4002.flycheckin.boarding.domain;

public abstract class Luggage {

  private static final double DIMENSION_CONVERSION_RATE = 62 / 158;
  private static final double WEIGHT_CONVERSION_RATE = 50 / 23;
  private int dimensionInInch;
  private int weightInPound;

  private int convertWeightToPoundUnit(int weightInKg) {
    return (int) Math.floor(weightInKg * WEIGHT_CONVERSION_RATE);
  }

  private int convertDimensionToInchUnit(int dimensionInCm) {
    return (int) Math.floor(dimensionInCm * DIMENSION_CONVERSION_RATE);
  }
}
