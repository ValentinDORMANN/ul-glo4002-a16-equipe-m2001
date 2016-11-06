package ca.ulaval.glo4002.flycheckin.boarding.domain;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggage extends Luggage {

  private static final int WEIGHT_LIMIT = 50;
  private static final int DIMENSION_LIMIT = 62;
  private static final String TYPE_CHECKED = "checked";
 
  
	public CheckedLuggage(LuggageDto luggageDto){
		super(luggageDto);
	  luggageDto.type = TYPE_CHECKED;
	}
	
	@Override
	public boolean isWeightAllowed() {
		return weightInPound < WEIGHT_LIMIT;
	}
	
	@Override
	public boolean isDimensionAllowed() {
		return dimensionInInch <  DIMENSION_LIMIT;
	}
}
