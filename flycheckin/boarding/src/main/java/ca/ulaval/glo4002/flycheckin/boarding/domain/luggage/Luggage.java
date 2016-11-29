package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import java.util.UUID;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NotAllowableLuggageException;

public abstract class Luggage {

	protected static final String LUGGAGE_DIMENSION_NOT_ALLOWED = "The size of luggage is over boundary";
	protected static final String LUGGAGE_WEIGHT_NOT_ALLOWED = "The weight of luggage is over boundary";
	private int dimensionInCm;
	private int weightInKg;
	private String luggageHash;
	private double price;

	public Luggage(int linearDimension, int weight) throws IllegalArgumentException {
		this.luggageHash = UUID.randomUUID().toString();
		this.price = 0;
	}

	public abstract boolean isType(String type);

	public abstract void calculatePrice();

	public abstract void verifyAllowableDimension()throws NotAllowableLuggageException;

	public int getDimensionInCm() {
		return dimensionInCm;
	}

	public abstract void verifyAllowableWeight(double limit)throws NotAllowableLuggageException;

	public int getWeightInKg() {
		return weightInKg;
	}

	public String getLuggageHash() {
		return luggageHash;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
	
	public boolean isFree(){
		return this.price==0?true:false;
	}

}
