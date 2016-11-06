package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;
import javassist.compiler.ast.Pair;

public class InMemorySeatAssignation implements SeatAssignationRepository {

  private static final String NO_SEAT_AVAILABLE = "No more seat available for this seat class";
  private static Map<Integer, Pair> seatAssignationMap = new HashMap<Integer, Pair>();

  @Override
  public int persistSeatAssignation(SeatAssignation seatAssignation) {
    return 0;
  }

  @Override
  public String getSeatAssignationByPassenger(String passengerHash) {

    return null;
  }
}
