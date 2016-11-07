package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public abstract class Luggage {

  private static final double DIMENSION_CONVERSION_RATE = (double) 62 / 158;
  private static final double WEIGHT_CONVERSION_RATE = (double) 50 / 23;
  private int dimensionInInch;
  private int weightInPound;
  private String luggageHash;
  private String type;

  public Luggage(LuggageDto luggageDto) throws IllegalArgumentException {
    this.dimensionInInch = convertDimensionToInchUnit(luggageDto.linear_dimension, luggageDto.linear_dimension_unit);
    this.weightInPound = convertWeightToPoundUnit(luggageDto.weight, luggageDto.weight_unit);
    this.luggageHash = UUID.randomUUID().toString();
    this.type = luggageDto.type;
  }

  private int convertDimensionToInchUnit(int dimension, String dimmensionUnit) {
    if (dimmensionUnit.equals("po"))
      return dimension;
    return (int) Math.floor(dimension * DIMENSION_CONVERSION_RATE);
  }

  private int convertWeightToPoundUnit(int weight, String weightUnit) {
    if (weightUnit.equals("lbs"))
      return weight;
    return (int) Math.floor(weight * WEIGHT_CONVERSION_RATE);
  }

  public boolean isType(String type) {
    return this.type.equals(type);
  }

  protected int getDimensionInInch() {
    return dimensionInInch;
  }

  protected int getWeightInPound() {
    return weightInPound;
  }
}
