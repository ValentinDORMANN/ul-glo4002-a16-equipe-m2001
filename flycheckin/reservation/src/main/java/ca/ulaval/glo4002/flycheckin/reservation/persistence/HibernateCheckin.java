package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckIn;
import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckinRepository;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;

public class HibernateCheckin{
	private EntityManager entityManager;
	
	public HibernateCheckin(){
		
		this.entityManager = new EntityManagerProvider().getEntityManager();
	}

	public void persistCheckIn(CheckIn checkIn) {
		try{
			entityManager.getTransaction().begin();
			
			entityManager.persist(checkIn);
			entityManager.getTransaction().commit();
			
		}catch(EntityNotFoundException ex){
			throw new NotCheckedinException();
			
		}
		
	}

	public CheckIn findCheckinByPassengerHash(String passengerHash) throws NotCheckedinException {
		return entityManager.find(CheckIn.class, passengerHash);
		
	}

}
