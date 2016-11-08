package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public abstract class Luggage {

  private static final double DIMENSION_CONVERSION_RATE = (double) 158 / 62;
  private static final double WEIGHT_CONVERSION_RATE = (double) 23 / 50;
  private static final String CM = "cm";
  private static final String KG = "kg";
  private int dimensionInCm;
  private int weightInKg;
  private String luggageHash;
  private String type;

  public Luggage(LuggageDto luggageDto) throws IllegalArgumentException {
    this.dimensionInCm = convertDimensionToCmUnit(luggageDto.linear_dimension, luggageDto.linear_dimension_unit);
    this.weightInKg = convertWeightToKgUnit(luggageDto.weight, luggageDto.weight_unit);
    this.luggageHash = UUID.randomUUID().toString();
    this.type = luggageDto.type;
  }

  private int convertDimensionToCmUnit(int dimension, String dimmensionUnit) {
    if (dimmensionUnit.equals(CM))
      return dimension;
    return (int) Math.ceil(dimension * DIMENSION_CONVERSION_RATE);
  }

  private int convertWeightToKgUnit(int weight, String weightUnit) {
    if (weightUnit.equals(KG))
      return weight;
    return (int) Math.ceil(weight * WEIGHT_CONVERSION_RATE);
  }

  public boolean isType(String type) {
    return this.type.equals(type);
  }

  protected int getDimensionInCm() {
    return dimensionInCm;
  }

  protected int getWeightInKg() {
    return weightInKg;
  }

  public String getLuggageHash() {
    return luggageHash;
  }
}
