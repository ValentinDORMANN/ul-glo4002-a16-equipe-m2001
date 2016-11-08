package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.client.AmsMapClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRandomStrategy;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationStrategy;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.PlaneModelService;

public class SeatAssignationService {

  private static int assignationNumberProvider = 1;
  private static Map<String, List<Seat>> availableSeatMap = new HashMap<String, List<Seat>>();
  private SeatAssignation seatAssignation;
  private SeatAssignationRepository seatAssignationRepository;
  private SeatAssignationStrategy seatAssignationStrategy;

  public SeatAssignationService(SeatAssignation seatAssignation, SeatAssignationRepository seatAssignationRepository) {
    this.seatAssignation = seatAssignation;
    this.seatAssignationRepository = seatAssignationRepository;
  }

  public SeatAssignationService(SeatAssignation seatAssignation, SeatAssignationRepository seatAssignationRepository,
      SeatAssignationStrategy seatAssignationStrategy) {
    this.seatAssignation = seatAssignation;
    this.seatAssignationRepository = seatAssignationRepository;
    this.seatAssignationStrategy = seatAssignationStrategy;
  }

  public SeatAssignation assignSeatToPassenger(Passenger passenger, String mode) {
    setSeatAssignationStrategy(mode);
    List<Seat> availableSeats = getAvalaibleSeatsForFlight(passenger.getFlightNumber(), passenger.getFlightDate());
    String seatNumber = seatAssignationStrategy.chooseSeatNumber(availableSeats, passenger.getSeatClass());
    seatAssignation.createAssignation(seatNumber, passenger.getPassengerHash(), assignationNumberProvider);
    seatAssignationRepository.persistSeatAssignation(seatAssignation);
    makeSeatNumberUnavailable(passenger.getFlightNumber(), passenger.getFlightDate(), seatNumber);
    assignationNumberProvider++;
    return seatAssignation;
  }

  private List<Seat> getAvalaibleSeatsForFlight(String flightNumber, Date flightDate) {
    String flightInfos = flightNumber + flightDate.toString();
    if (!(availableSeatMap.containsKey(flightInfos))) {
      PlaneModelService planeModelService = new PlaneModelService();
      AmsMapClient amsMapConnector = new AmsMapClient();
      String planeModel = amsMapConnector.getPlaneModelByFlightNumber(flightNumber);
      availableSeatMap.put(flightInfos, planeModelService.getSeatsAccordingPlaneModel(planeModel));
    }
    return availableSeatMap.get(flightInfos);
  }

  private void setSeatAssignationStrategy(String mode) {
    if (this.seatAssignationStrategy == null)
      this.seatAssignationStrategy = new SeatAssignationRandomStrategy();
  }

  private void makeSeatNumberUnavailable(String flightNumber, Date flightDate, String seatNumber) {
    String flightInfos = flightNumber + flightDate.toString();
    List<Seat> availableSeats = availableSeatMap.get(flightInfos);
    for (Seat seat : availableSeats) {
      if (seat.getSeatNumber().equals(seatNumber)) {
        availableSeats.remove(seat);
        break;
      }
    }
    availableSeatMap.replace(flightInfos, availableSeats);
  }
}
