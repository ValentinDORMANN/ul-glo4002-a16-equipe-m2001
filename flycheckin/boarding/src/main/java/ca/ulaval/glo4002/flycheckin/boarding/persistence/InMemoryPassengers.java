package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;
import javassist.compiler.ast.Pair;

public class InMemoryPassengers implements SeatAssignationRepository {

  private static final String NO_SEAT_AVAILABLE = "No more seat available for this seat class";
  private static Map<Integer, Pair> seatAssignationMap = new HashMap<Integer, Pair>();
  private static Map<String, List<String>> availableSeatMap = new HashMap<String, List<String>>();

  @Override
  public int assignSeatToPassenger(String passengerHash) {
    return 0;
  }

  @Override
  public String getPassengerSeat(String passengerHash) {
    return null;
  }
}
