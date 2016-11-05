package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;


public class Passenger {
	
  private String firstName;
  private String lastName;
  private String passportNumber;
  private String seatClass;
  private boolean child;
  private String passengerHash;
  private List<Luggage> luggages;
  
	public Passenger() {
	}

	public Passenger(PassengerDto passengerDto) {
		this.firstName = passengerDto.first_name;
		this.lastName = passengerDto.last_name;
		this.passportNumber = passengerDto.passport_number;
		this.seatClass = passengerDto.seat_class;
		this.child = passengerDto.child;
		this.passengerHash = passengerDto.passenger_hash;
		this.luggages = new ArrayList<Luggage>();
	}
}
