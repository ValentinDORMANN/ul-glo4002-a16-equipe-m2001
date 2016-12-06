package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

public class SeatAssignationStrategyFactory {

  public SeatAssignationStrategy createSeatAssignationStrategy(String mode) {
    switch (mode) {
      case "CHEAPEST":
        return new SeatAssignationCheapestStrategy();
      case "LEGS":
        return new SeatAssignationLegroomStrategy();
      case "LANDSCAPE":
        return new SeatAssignationLandScapeStrategy();
      case "RANDOM":
        return new SeatAssignationRandomStrategy();
      default:
        throw new UndefinedSeatAssignationStrategyException();
    }
  }
}
