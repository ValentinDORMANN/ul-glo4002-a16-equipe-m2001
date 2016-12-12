package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.CheckIn;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;

public class HibernateCheckinTest {

	

		  private static final int CHECKIN_LIST_LIMIT = 100;
		  private static final String PASSENGER_HASH = "acb15 de26f 4mf99z";
		  private static final String PASSENGER_HASH2 = "jfd45 d4g5f 48dfz";
		  private int checkinNumber;
		  private HibernateCheckin hibernateCheckin;
		  private CheckIn checkinMock;

		  @Before
		  public void initiateTest() {
			  checkinMock = mock(CheckIn.class);
			  hibernateCheckin = new HibernateCheckin();
		  }


		

}
