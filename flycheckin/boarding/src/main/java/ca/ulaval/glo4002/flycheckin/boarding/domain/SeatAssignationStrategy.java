package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.List;

public interface SeatAssignationStrategy {

  public String chooseSeatNumber(List<Seat> availableSeats);
}
