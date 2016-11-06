package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.client.AmsMapClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.ServicePlaneModel;

public class ServiceSeatAssignation {

  private static final String MESSAGE_SEAT_UNASSIGNED = "Error: This passenger seat is already assigned";
  private static Map<String, List<Seat>> availableSeatMap = new HashMap<String, List<Seat>>();
  private SeatAssignation seatAssignation;
  private SeatAssignationRepository seatAssignationRepository;

  public ServiceSeatAssignation(SeatAssignation seatAssignation, SeatAssignationRepository seatAssignationRepository) {
    this.seatAssignation = seatAssignation;
    this.seatAssignationRepository = seatAssignationRepository;
  }

  public SeatAssignation assignSeatToPassenger(Passenger passenger, String mode) {
    List<Seat> availableSeats = getAvalaibleSeatsForFlight(passenger.getFlightNumber(), passenger.getFlightDate());
    // attribuer un numéro d'assignation si un siège est retourné
    seatAssignation.assignSeatNumberToPassenger(passenger.getPassengerHash(), mode);
    // persister l'assignation du siège en mémoire
    // retirer le siège des sièges disponibles pur ce vol
    return null;
  }

  private List<Seat> getAvalaibleSeatsForFlight(String flightNumber, Date flightDate) {
    ServicePlaneModel servicePlaneModel = new ServicePlaneModel();
    AmsMapClient amsMapConnector = new AmsMapClient();
    String flightInfos = flightNumber + flightDate.toString();
    if (availableSeatMap.containsKey(flightInfos))
      return availableSeatMap.get(flightInfos);
    else {
      String planeModel = amsMapConnector.getPlaneModelByFlightNumber(flightNumber);
      return servicePlaneModel.getSeatsAccordingPlaneModel(planeModel);
    }
  }

}
