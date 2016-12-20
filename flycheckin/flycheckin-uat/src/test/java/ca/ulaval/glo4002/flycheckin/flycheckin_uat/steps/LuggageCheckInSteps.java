package ca.ulaval.glo4002.flycheckin.flycheckin_uat.steps;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.PassengerDto;
import cucumber.api.PendingException;
import cucumber.api.java8.En;

public class LuggageCheckInSteps implements En {
  private static final String LAST_NAME = "mary";
  private static final String FIRST_NAME = "john";
  private static final int AGE = 23;
  private static final String PASSPORT_NUMBER = "AB123";
  private static final String SEAT_CLASS = "economic";
  PassengerDto passengerDto;

  @cucumber.api.java.Before
  public void beforeScenario() {
    passengerDto = new PassengerDto();
    passengerDto.last_name = LAST_NAME;
    passengerDto.first_name = FIRST_NAME;
    passengerDto.age = AGE;
    passengerDto.passport_number = PASSPORT_NUMBER;
    passengerDto.seat_class = SEAT_CLASS;
  }

  public LuggageCheckInSteps() {
    Given("^a passenger Bob with an economy class reservation on flight AC-(\\d+)$", (String flightNumber) -> {
      Passenger bob = new Passenger(passengerDto);
      throw new PendingException();
    });

    Given("^already has a checked luggage meeting the standards$", () -> {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
    });

    When("^He registers another checked luggage weighing (\\d+)kg and size (\\d+)cm$", (Integer weight, Integer size) -> {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
    });

    Then("^the amount to pay is (\\d+)\\$$", (Float amount) -> {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
    });

  }
}
