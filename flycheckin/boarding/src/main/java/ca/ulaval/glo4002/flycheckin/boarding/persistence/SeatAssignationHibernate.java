package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignationRepository;

public class SeatAssignationHibernate implements SeatAssignationRepository{

  private static final String EMPTY_STRING = "";
  private static final String ERROR_SEAT_UNASSIGNED = "Error: This passenger seat is already assigned.";
  private static final String ERROR_ASSIGNATION_NUMBER_USED = "Error: This assignation number is already used.";
  
  private EntityManager entityManager;
  
  public SeatAssignationHibernate() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }
  
  @Override
  public void persistSeatAssignation(SeatAssignation seatAssignation) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    try {
      if (entityManager.find(SeatAssignation.class, seatAssignation.getAssignationNumber()) != null)
        throw new AssignationNumberUsedException(ERROR_ASSIGNATION_NUMBER_USED);
      else if (!getPassengerHashSeatNumber(seatAssignation.getPassengerHash()).isEmpty())
        throw new SeatAlreadyAssignedException(ERROR_SEAT_UNASSIGNED);
    } finally {
      transaction.commit();
    }
  }

  @Override
  public String getPassengerHashSeatNumber(String passengerHash) {
    String seatNumber = EMPTY_STRING;
    SeatAssignation seatAssignationFound;
    String hql = "select s from SeatAssignation s where s.passengerHash = :passengerHash";
    
    TypedQuery<SeatAssignation> query = entityManager.createQuery(hql, SeatAssignation.class);
    query.setParameter("passengerHash", passengerHash);
    seatAssignationFound = query.getSingleResult();
    seatAssignationFound = entityManager.find(SeatAssignation.class, passengerHash);
    
    if (seatAssignationFound == null)
      return seatNumber;
    return seatAssignationFound.getSeatNumber();
  }
}
