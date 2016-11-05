package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.boarding.domain.RepositorySeatAssignation;
import javassist.compiler.ast.Pair;

public class InMemorySeatAssignation implements RepositorySeatAssignation {

  private static Map<Integer, Pair> seatAssignationMap = new HashMap<Integer, Pair>();
  private static Map<String, List<String>> availableSeatMap = new HashMap<String, List<String>>();

  @Override
  public int assignSeatToPassenger() {
    return 0;
  }

  @Override
  public String getPassengerSeat() {
    return null;
  }
}
