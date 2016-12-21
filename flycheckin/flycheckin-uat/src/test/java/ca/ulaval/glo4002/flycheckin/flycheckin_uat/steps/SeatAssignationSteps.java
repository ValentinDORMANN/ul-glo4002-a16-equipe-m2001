package ca.ulaval.glo4002.flycheckin.flycheckin_uat.steps;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.flycheckin.boarding.client.AmsMapEncoded;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.PassengerFactory;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.NoSeatAvailableException;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignationRepository;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy.SeatAssignationStrategyFactory;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.SeatAssignationHibernate;
import ca.ulaval.glo4002.flycheckin.boarding.services.external.PlaneModelService;
import ca.ulaval.glo4002.flycheckin.boarding.services.seat.SeatAssignationService;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.EntityManagerProvider;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.ReservationDto;
import cucumber.api.java8.En;

public class SeatAssignationSteps implements En {

	private static final int AGE = 18;
	private static final String FIRST_NAME = "Alice";
	private static final String SEAT_CLASS = "economy";
	private static final String PASSPORT_NUMBER = "AL987";
	private static final int RESERVATION_NUMBER = 38000;
	private static final String RESERVATION_DATE = "2016-12-15";
	private static final String RESERVATION_CONFIRMATION = "98765";
	private static final String FLIGHT_DATE = "2017-01-15T00:00:00Z";
	private static final String PAYEMENT_LOCATION = "/payments/kjshkjhdkjnkd";
	private static final String NO_SEAT_AVAILABLE = "Aucun siège de la catégorie du passager n'est disponible";
	private static final SimpleDateFormat DATE_FORMAT_COURT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DATE_FORMAT_LONG = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private static final String DOUBLE_RESERVATION_ERROR = "Error : This reservation exists already.";

	PassengerDto passengerDto = new PassengerDto();
	ReservationDto reservationDto = new ReservationDto();
	Passenger passenger;
	Reservation reservation;
	SeatAssignation seatAssignation = null;
	 private EntityManager entityManager;
	
	 @cucumber.api.java.Before
	  public void beforeScenario() throws ParseException {
	    Date reservationDate = DATE_FORMAT_COURT.parse(RESERVATION_DATE);
	    Date flightDate = DATE_FORMAT_LONG.parse(FLIGHT_DATE);

	    reservationDto.reservation_number = RESERVATION_NUMBER;
	    reservationDto.reservation_date = reservationDate;
	    reservationDto.reservation_confirmation = RESERVATION_CONFIRMATION;
	    reservationDto.flight_date = flightDate;
	    reservationDto.payment_location = PAYEMENT_LOCATION;

	   // passengerDto.first_name = FIRST_NAME;
	    passengerDto.age = AGE;
	    passengerDto.passport_number = PASSPORT_NUMBER;
	    passengerDto.seat_class = SEAT_CLASS;
	    
	    entityManager = new EntityManagerProvider().getEntityManager();
	  }

	public SeatAssignationSteps() {
		Given("^a passenger \"([^\"]*)\" with a reservation on a flight$", (String passengerName) -> {
		      passenger = createPassengerWithReservation(passengerName);
		    });

		    Given("^seats are available on this flight$", () -> {
		      PlaneModelService planeModelService = new PlaneModelService();
		      AmsMapEncoded amsMapConnector = new AmsMapEncoded();
		      String planeModel = amsMapConnector.getPlaneModelByFlightNumber(passenger.getFlightNumber());
		      List<Seat> seats = planeModelService.getSeatsAccordingPlaneModel(planeModel);
		      seats = filterSeatsBySeatClass(seats, passenger.getSeatClass());
		    });

		    Given("^he already done his check-in$", () -> {
		      CheckinInMemory checkinInMemory = new CheckinInMemory();
		      checkinInMemory.doPassengerCheckin(passenger.getPassengerHash());
		    });

		    When("^he chooses his seat wiht the \"([^\"]*)\" mode$", (String mode) -> {
		      SeatAssignationService seatAssignationService = new SeatAssignationService(new SeatAssignation(), new SeatAssignationRepository() {
				
				@Override
				public void persistSeatAssignation(SeatAssignation seatAssignation) {
					// TODO Auto-generated method stub
					
				}
			},
		          new SeatAssignationStrategyFactory());
		      mode = mode.toUpperCase();
		      seatAssignation = seatAssignationService.assignSeatToPassenger(passenger, mode);
		    });

		    Then("^a seat is assigned to him on that flight$", () -> {
		      assertEquals(seatAssignation.getAssignationNumber(), 1);
		      assertNotNull(seatAssignation.getSeatNumber());
		      assertEquals(passenger.getPassengerHash(), seatAssignation.getPassengerHash());
		    });
		  }

		  public Passenger createPassengerWithReservation(String passengerName) {
		    reservationDto.passengers = new ArrayList<PassengerDto>();
		    passengerDto.first_name=passengerName;
		    reservationDto.passengers.add(passengerDto);
		    reservation = new Reservation(reservationDto);
		    saveReservation(reservation);
		    return createBoardingPassenger(reservation);
		  }

		  private Passenger createBoardingPassenger(Reservation reservation) {
		    PassengerFactory passengerFactory = new PassengerFactory();
		    String passengerHash = reservation.getPassengers().get(0).getPassengerHash();
		    String seatClass = reservation.getPassengers().get(0).getSeatClass();
		    return passengerFactory.createPassenger(reservation.getFlightNumber(), reservation.getFlightDate(), passengerHash, seatClass, false,
		        false);
		  }

		  private List<Seat> filterSeatsBySeatClass(List<Seat> seats, String seatClass) {
		    List<Seat> filterSeats = new ArrayList<Seat>();
		    for (Seat seat : seats) {
		      if (seat.hasClass(seatClass))
		        filterSeats.add(seat);
		    }
		    if (filterSeats.isEmpty()) {
		      throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
		    }
		    return filterSeats;
		  }
		  
		  private void saveReservation(Reservation reservation){
			  EntityTransaction transaction = entityManager.getTransaction();
			    transaction.begin();
			    try {
			      if (entityManager.find(Reservation.class, reservation.getReservationNumber()) != null)
			        throw new IllegalArgumentReservationException(DOUBLE_RESERVATION_ERROR);
			      else
			        entityManager.persist(reservation);
			    } finally {    	
			      transaction.commit();
			    }  
		  }
}
