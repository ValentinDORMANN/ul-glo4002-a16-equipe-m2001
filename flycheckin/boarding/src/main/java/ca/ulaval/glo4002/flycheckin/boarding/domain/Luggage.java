package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public abstract class Luggage {

  private static final double DIMENSION_CONVERSION_RATE = 62 / 158;
  private static final double WEIGHT_CONVERSION_RATE = 50 / 23;
  private int dimensionInInch;
  private int weightInPound;
  private String luggageHash;
  private String type;
  
  public Luggage(LuggageDto luggageDto) throws IllegalArgumentException {
    this.luggageHash = UUID.randomUUID().toString();
    this.dimensionInInch = convertDimensionToInchUnit(luggageDto.dimension, luggageDto.dimension_unit);
    this.weightInPound = convertWeightToPoundUnit(luggageDto.weight, luggageDto.weight_unit);
    this.type = luggageDto.type;
    this.luggageHash = UUID.randomUUID().toString();
  }

  private int convertDimensionToInchUnit(int dimension, String dimmensionUnit) {
    if(dimmensionUnit.equals("po"))
      return dimension;
    else
      return (int) Math.floor(dimension * DIMENSION_CONVERSION_RATE);
  }

  private int convertWeightToPoundUnit(int weight, String weightUnit) {
    if(weightUnit.equals("lbs"))
      return weight;
    else
      return (int) Math.floor(weight * WEIGHT_CONVERSION_RATE);
  }

  public boolean isType(String type) {
    return type.equals(type);
  }
  
  public int getDimensionInInch() {
    return dimensionInInch;
  }
  
  public int getWeightInPound() {
    return weightInPound;
  }
}
