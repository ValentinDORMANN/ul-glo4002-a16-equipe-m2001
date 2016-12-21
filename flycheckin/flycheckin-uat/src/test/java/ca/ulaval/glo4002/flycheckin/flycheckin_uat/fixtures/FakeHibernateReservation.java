package ca.ulaval.glo4002.flycheckin.flycheckin_uat.fixtures;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;

public class FakeHibernateReservation extends HibernateReservation{
	
	public Map<Integer, Reservation> store = new HashMap<>();
	 private static final int RESERVATION_ID_BEGIN = 100;
	  private static int reservationId = RESERVATION_ID_BEGIN;
	@Override
	public void persisteReservation(Reservation reservation){
		store.put(reservationId, reservation);
	}
}
