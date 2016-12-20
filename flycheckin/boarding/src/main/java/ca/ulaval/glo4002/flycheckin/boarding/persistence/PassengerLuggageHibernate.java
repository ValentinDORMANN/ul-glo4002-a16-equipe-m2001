package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageRegistry;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.PassengerLuggage;

public class PassengerLuggageHibernate implements LuggageRegistry {

  private EntityManager entityManager;

  public PassengerLuggageHibernate() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  public void savePassengerLuggage(PassengerLuggage passengerLugage) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    try {
      PassengerLuggage buffer = entityManager.find(PassengerLuggage.class, passengerLugage.getPassengerHash());
      if (buffer == null)
        entityManager.persist(passengerLugage);
      else {
        buffer.setLuggage(passengerLugage.getLuggage());
        entityManager.persist(buffer);
      }
    } finally {
      transaction.commit();
    }
  }

  public PassengerLuggage getPassengerLuggage(String passengerHash) {
    PassengerLuggage passengerLuggage;
    passengerLuggage = entityManager.find(PassengerLuggage.class, passengerHash);
    if (passengerLuggage == null)
      passengerLuggage = new PassengerLuggage();
    return passengerLuggage;
  }

}
