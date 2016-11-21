package ca.ulaval.glo4002.flycheckin.boarding.rest.dto;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;

public class LuggageInfoDto {

  public double total_price;
  public List<LuggageDto> baggages;

  public LuggageInfoDto(Passenger passenger) {
    baggages = new ArrayList<LuggageDto>();
    for (Luggage luggage : passenger.getLuggages()) {
      baggages.add(new LuggageDto(luggage));
    }
    total_price = passenger.getTotalPrice();
  }
}
