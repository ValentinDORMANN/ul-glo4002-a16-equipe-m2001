package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.boarding.exception.InvalidUnitException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public abstract class Luggage {

  private static final double DIMENSION_CONVERSION_RATE = (double) 158 / 62;
  private static final double WEIGHT_CONVERSION_RATE = (double) 23 / 50;
  private static final String CM = "cm";
  private static final String KG = "kg";
  private static final String PO = "po";
  private static final String LBS = "lbs";
  private int dimensionInCm;
  private int weightInKg;
  private String luggageHash;
  private String type;
  private double price;

  public Luggage(LuggageDto luggageDto) throws IllegalArgumentException {
    this.dimensionInCm = convertDimensionToCmUnit(luggageDto.linear_dimension, luggageDto.linear_dimension_unit);
    this.weightInKg = convertWeightToKgUnit(luggageDto.weight, luggageDto.weight_unit);
    this.luggageHash = UUID.randomUUID().toString();
    this.type = luggageDto.type;
    price = 0;
  }

  private int convertDimensionToCmUnit(int dimension, String dimmensionUnit) throws InvalidUnitException {
    isValidUnitDimension(dimmensionUnit);
    if (dimmensionUnit.equals(CM))
      return dimension;
    return (int) Math.ceil(dimension * DIMENSION_CONVERSION_RATE);
  }

  private void isValidUnitDimension(String dimmensionUnit) {
    if (!(dimmensionUnit.toLowerCase().equals(CM) || dimmensionUnit.toLowerCase().equals(PO)))
      throw new InvalidUnitException();
  }

  private int convertWeightToKgUnit(int weight, String weightUnit) throws InvalidUnitException {
    isValidUnitWeight(weightUnit);
    if (weightUnit.equals(KG))
      return weight;
    return (int) Math.ceil(weight * WEIGHT_CONVERSION_RATE);
  }

  private void isValidUnitWeight(String dimmensionUnit) {
    if (!(dimmensionUnit.toLowerCase().equals(KG) || dimmensionUnit.toLowerCase().equals(LBS)))
      throw new InvalidUnitException();
  }

  public boolean isType(String type) {
    return this.type.equals(type);
  }

  public int getDimensionInCm() {
    return dimensionInCm;
  }

  public int getWeightInKg() {
    return weightInKg;
  }

  public String getLuggageHash() {
    return luggageHash;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
