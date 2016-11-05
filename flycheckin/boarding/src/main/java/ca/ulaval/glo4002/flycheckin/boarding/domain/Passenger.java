package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.List;


public class Passenger {
	
  private String firstName;
  private String lastName;
  private String passportNumber;
  private String seatClass;
  private boolean child;
  private String passengerHash;
  private List<CheckedLuggage> luggages;

}
