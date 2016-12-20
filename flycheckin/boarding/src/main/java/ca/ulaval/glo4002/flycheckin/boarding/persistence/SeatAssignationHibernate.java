package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignationRepository;

public class SeatAssignationHibernate implements SeatAssignationRepository {

  private static final String ERROR_SEAT_UNASSIGNED = "Error: This passenger seat is already assigned.";

  private EntityManager entityManager;

  public SeatAssignationHibernate() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  public SeatAssignationHibernate(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void persistSeatAssignation(SeatAssignation seatAssignation) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    try {
      if (isThisSeatAlreadyAssigned(seatAssignation.getPassengerHash()))
        throw new SeatAlreadyAssignedException(ERROR_SEAT_UNASSIGNED);
      entityManager.persist(seatAssignation);
    } finally {
      transaction.commit();
    }
  }

  private boolean isThisSeatAlreadyAssigned(String passengerHash) {
    return entityManager.find(SeatAssignation.class, passengerHash) != null;
  }
}
