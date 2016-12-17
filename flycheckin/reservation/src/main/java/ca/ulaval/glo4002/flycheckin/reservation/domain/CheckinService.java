package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateCheckin;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateReservation;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinService {

	private static final String MESSAGE_ERROR = "Passenger Information incorrect";
	private CheckinInMemory checkinInMemory;
	private HibernateReservation hibernateReservation;
	private HibernateCheckin hibernateCheckin;

	public CheckinService(CheckinInMemory checkinInMemory, HibernateReservation hibernateReservation) {
		this.checkinInMemory = checkinInMemory;
		this.hibernateReservation = hibernateReservation;
	}

	public int saveCheckin(CheckinDto checkinDto) throws ReservationModuleException {
		String hash = checkinDto.passenger_hash;
		String by = checkinDto.by;
		boolean isVip = checkinDto.vip;
		Reservation reservation = hibernateReservation.findReservationByPassengerHash(hash);
		reservation.validateCheckinPeriod(by);
		if (reservation.isPassengerInfosValid(hash)) {
			int checkinId = checkinInMemory.doPassengerCheckin(hash);
			return checkinId;
		}
		throw new ReservationModuleException(MESSAGE_ERROR);
	}
}