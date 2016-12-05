package ca.ulaval.glo4002.flycheckin.boarding.services.external;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.client.PlaneModelHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ClassPassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatDto;

public class PlaneModelServiceTest {

  private static final int EXIT_ROW = 1;
  private static final int ANOTHER_ROW = 2;
  private static final int LEGROOM = 56;
  private static final String SEAT = "A";
  private static final boolean WINDOW = true;
  private static final boolean CLEAR_VIEW = true;
  private static final double PRICE = 100;
  private static final String CLASS_NAME = "economic";
  private static final int[] EXIT_ROWS = { EXIT_ROW };
  private static final int[] ROWS = { EXIT_ROW, ANOTHER_ROW };
  private static final String PLANE_MODEL = "a320";

  private PlaneModelHttpClient planeModelHttpClientMock;
  private PlaneModelDto planeModelDto;
  private SeatDto seatDto;
  private ClassPassengerDto classPassengerDto;
  private PlaneModelService planeModelService;

  @Before
  public void initiateTest() {
    planeModelHttpClientMock = mock(PlaneModelHttpClient.class);
    planeModelDto = new PlaneModelDto();
    seatDto = new SeatDto();
    classPassengerDto = new ClassPassengerDto();

    planeModelService = new PlaneModelService(planeModelHttpClientMock);
  }

  @Test
  public void givenPlaneModelWhenGetPlaneSeatsThenReturnNotEmptySeatList() {
    givenPlaneModel();

    List<Seat> seats = planeModelService.getSeatsAccordingPlaneModel(PLANE_MODEL);

    assertFalse(seats.isEmpty());
  }

  @Test
  public void givenSeatInExitRowWhenGetPlaneSeatsThenReturnTrue() {
    givenPlaneModel();

    List<Seat> seats = planeModelService.getSeatsAccordingPlaneModel(PLANE_MODEL);

    assertTrue(seats.get(0).isExitRow());
  }

  @Test
  public void givenSeatNotInExitRowWhenGetPlaneSeatsThenReturnFalse() {
    givenPlaneModel();
    seatDto.row = ANOTHER_ROW;

    List<Seat> seats = planeModelService.getSeatsAccordingPlaneModel(PLANE_MODEL);

    assertFalse(seats.get(0).isExitRow());
  }

  private void givenPlaneModel() {
    seatDto.row = EXIT_ROW;
    seatDto.seat = SEAT;
    seatDto.legroom = LEGROOM;
    seatDto.window = WINDOW;
    seatDto.clear_view = CLEAR_VIEW;
    seatDto.price = PRICE;
    classPassengerDto.rows = ROWS;
    classPassengerDto.name = CLASS_NAME;
    SeatDto[] seatsDto = { seatDto };
    planeModelDto.seats = seatsDto;
    ClassPassengerDto[] classesDto = { classPassengerDto };
    planeModelDto.classes = classesDto;
    planeModelDto.exit_rows = EXIT_ROWS;
    willReturn(planeModelDto).given(planeModelHttpClientMock).getPlaneModelDtoAccordingPlaneModel(PLANE_MODEL);
  }
}
