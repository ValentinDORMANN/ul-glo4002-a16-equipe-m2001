package ca.ulaval.glo4002.flycheckin.boarding.domain.seat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SeatAssignation {

  @Id
  @Column(name = "assignationNumber", unique = true, nullable = false)
  private int assignationNumber;
  @Column(name = "passengerHash")
  private String passengerHash;
  @Column(name = "seatNumber")
  private String seatNumber;

  public SeatAssignation() {
  }

  public void createAssignation(String seatNumber, String passengerHash, int assignationNumber) {
    this.passengerHash = passengerHash;
    this.seatNumber = seatNumber;
    this.assignationNumber = assignationNumber;
  }

  public boolean isAssociateToThisHash(String passengerHash) {
    return this.passengerHash.equals(passengerHash);
  }

  public int getAssignationNumber() {
    return this.assignationNumber;
  }

  public String getPassengerHash() {
    return this.passengerHash;
  }

  public String getSeatNumber() {
    return this.seatNumber;
  }
}
