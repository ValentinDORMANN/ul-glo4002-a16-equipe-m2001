package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;

public interface CheckinRepository {
	
	public void persistCheckIn(CheckIn checKin);
	
	public CheckIn findCheckinByPassengerHash(String passengerHash) throws NotCheckedinException;

}
