package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public abstract class Luggage {

  private static final double DIMENSION_CONVERSION_RATE = 62 / 158;
  private static final double WEIGHT_CONVERSION_RATE = 50 / 23;
  protected int dimensionInInch;
  protected int weightInPound;
  private String luggageHash;

  private int convertWeightToPoundUnit(int weightInKg) {
    return (int) Math.floor(weightInKg * WEIGHT_CONVERSION_RATE);
  }

  private int convertDimensionToInchUnit(int dimensionInCm) {
    return (int) Math.floor(dimensionInCm * DIMENSION_CONVERSION_RATE);
  }
  
  public Luggage(LuggageDto luggageDto) throws IllegalArgumentException {
    if(isDimensionInInch(luggageDto))
    	this.dimensionInInch = luggageDto.dimension;
    else
    	this.dimensionInInch = convertDimensionToInchUnit(luggageDto.dimension);
    if(isWeightInPound(luggageDto))
    	this.weightInPound = luggageDto.weight;
    else
    	this.weightInPound = convertWeightToPoundUnit(luggageDto.weight);   
    this.luggageHash = UUID.randomUUID().toString();
  }
  
  private boolean isDimensionInInch(LuggageDto luggageDto) {
  	return luggageDto.dimension_unit.equals("po");
}
  private boolean isWeightInPound(LuggageDto luggageDto) {
  	return luggageDto.weight_unit.equals("lbs");
}
  abstract public boolean isWeightAllowed();
  abstract public boolean isDimensionAllowed();
  
}
