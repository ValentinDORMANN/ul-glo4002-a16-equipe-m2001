package ca.ulaval.glo4002.flycheckin.flycheckin_uat.steps;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageFactory;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.PassengerFactory;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.ReservationDto;
import cucumber.api.java8.En;

public class LuggageCheckInSteps implements En {
  private static final String LAST_NAME = "Martin";
  private static final int AGE = 23;
  private static final String PASSPORT_NUMBER = "AB123";
  private static final int RESERVATION_NUMBER = 37999;
  private static final String RESERVATION_DATE = "2016-01-31";
  private static final String RESERVATION_CONFIRMATION = "12345";
  private static final String FLIGHT_DATE = "2016-10-30T00:00:00Z";
  private static final String PAYEMENT_LOCATION = "/payments/daghkjhg";
  private static final SimpleDateFormat DATE_FORMAT_COURT = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat DATE_FORMAT_LONG = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  HibernateReservation fakeHibernateReservation;
  PassengerDto passengerDto = new PassengerDto();
  ReservationDto reservationDto = new ReservationDto();
  Passenger passenger;
  Reservation reservation;

  @cucumber.api.java.Before
  public void beforeScenario() throws ParseException {
    Date reservationDate = DATE_FORMAT_COURT.parse(RESERVATION_DATE);
    Date flightDate = DATE_FORMAT_LONG.parse(FLIGHT_DATE);

    reservationDto.reservation_number = RESERVATION_NUMBER;
    reservationDto.reservation_date = reservationDate;
    reservationDto.reservation_confirmation = RESERVATION_CONFIRMATION;
    reservationDto.flight_date = flightDate;
    reservationDto.payment_location = PAYEMENT_LOCATION;

    passengerDto.last_name = LAST_NAME;
    passengerDto.age = AGE;
    passengerDto.passport_number = PASSPORT_NUMBER;
    fakeHibernateReservation = new FakeHibernateReservationFixture();
  }

  public LuggageCheckInSteps() {
    Given("^a passenger \"([^\"]*)\" with an \"([^\"]*)\" class reservation on flight \"([^\"]*)\"$",
        (String name, String seatClass, String flightNumber) -> {
          passenger = createPassengerBob(name, seatClass, flightNumber);
        });

    Given("^already has a \"([^\"]*)\" luggage meeting the standards$", (String type) -> {
      addStandardLuggage(reservation, type);
    });

    When("^He registers another \"([^\"]*)\" luggage weighing (\\d+)kg and size (\\d+)cm$", (String type, Integer weight, Integer size) -> {
      addSpecificLuggage(size, weight, type);
    });

    Then("^the amount to pay is (\\d+)\\$$", (Double amount) -> {
      assertEquals(amount, passenger.getTotalPrice(), 0.001);
    });

  }

  public Passenger createPassengerBob(String name, String seatClass, String flightNumber) {
    reservationDto.flight_number = flightNumber;
    passengerDto.first_name = name;
    passengerDto.seat_class = seatClass;
    reservationDto.passengers = new ArrayList<PassengerDto>();
    reservationDto.passengers.add(passengerDto);
    reservation = new Reservation(reservationDto);
    fakeHibernateReservation.persisteReservation(reservation);
    return createBoardingPassenger(reservation);
  }

  public void addStandardLuggage(Reservation reservation, String type) {
    int standardSize = 150;
    int standardWeight = 22;
    Luggage luggage = createLuggage(standardSize, standardWeight, type);
    passenger.addLuggage(luggage);
  }

  public void addSpecificLuggage(int size, int weight, String type) {
    Luggage luggage = createLuggage(size, weight, type);
    passenger.addLuggage(luggage);
  }

  private Passenger createBoardingPassenger(Reservation reservation) {
    PassengerFactory passengerFactory = new PassengerFactory();
    String passengerHash = reservation.getPassengers().get(0).getPassengerHash();
    String seatClass = reservation.getPassengers().get(0).getSeatClass();
    return passengerFactory.createPassenger(reservation.getFlightNumber(), reservation.getFlightDate(), passengerHash, seatClass, false,
        false);
  }

  private Luggage createLuggage(int size, int weight, String type) {
    LuggageFactory luggageFactory = new LuggageFactory();
    return luggageFactory.createLuggage(size, weight, type);
  }
}
