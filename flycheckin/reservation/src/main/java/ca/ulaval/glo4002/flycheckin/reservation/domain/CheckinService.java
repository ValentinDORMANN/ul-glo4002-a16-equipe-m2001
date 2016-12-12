package ca.ulaval.glo4002.flycheckin.reservation.domain;


import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.CheckinInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.EntityManagerProvider;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.HibernateCheckin;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.ReservationInMemory;
import ca.ulaval.glo4002.flycheckin.reservation.rest.application.ServiceLocator;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckinService {

  private static final String MESSAGE_ERROR = "Passenger Information incorrect";
  //private HibernateCheckin hibernateCheckin;
  private ReservationRegistry reservationRegistry;
  private CheckinRepository checkinRepository ;
  private Reservation reservation;
 
  
  public CheckinService(){
	  this.checkinRepository = ServiceLocator.resolve(CheckinRepository.class);
   
	  
  }
  
  public CheckinService(CheckinRepository checkinRepository, ReservationRegistry reservationRegistry,Reservation reservation) {
    this.checkinRepository = checkinRepository;
    this.reservationRegistry = reservationRegistry;
    //this.checkinRepository = checkinRepository;
  }

  public int saveCheckin(CheckinDto checkinDto) throws NotCheckedinException{
    String by = checkinDto.by;
    String hash = checkinDto.passenger_hash;
    boolean isVip = checkinDto.vip;
    validateCheckin(by, hash, isVip);
    CheckIn checkin =new CheckIn(checkinDto);
    checkinRepository.persistCheckIn(checkin);
    return checkin.getId();
  }

  private void validateCheckin(String by, String hash, boolean isVip) {
    Reservation reservation = reservationRegistry.getReservationByPassengerHash(hash);
    reservation.validateCheckinPeriod(by);
    reservation.changePassengerVipStatus(hash, isVip);
    if (!reservation.isPassengerInfosValid(hash))
      throw new ReservationModuleException(MESSAGE_ERROR);
  }

  public void isCheckInPassengerDone(String passengerHash) throws NotCheckedinException {
	  checkinRepository.findCheckinByPassengerHash(passengerHash);
  }
}
