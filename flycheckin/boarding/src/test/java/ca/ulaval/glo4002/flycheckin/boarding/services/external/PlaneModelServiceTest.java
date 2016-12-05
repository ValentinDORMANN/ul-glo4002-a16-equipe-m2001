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

  private static final int[] EXIT_ROWS = { 1 };
  private static final int ROW = 1;
  private static final int LEGROOM = 56;
  private static final String SEAT = "A";
  private static final boolean WINDOW = true;
  private static final boolean CLEAR_VIEW = true;
  private static final double PRICE = 100;
  private static final String CLASS_NAME = "economic";
  private static final int[] ROWS = { ROW };
  private static final String PLANE_MODEL = "a320";
  private PlaneModelHttpClient mockPlaneModelHttpClient;
  private PlaneModelDto planeModelDto;
  private SeatDto mockSeatDto;
  private ClassPassengerDto mockClassPassengerDto;
  private PlaneModelService planeModelService;

  @Before
  public void initiateTest() {
    mockPlaneModelHttpClient = mock(PlaneModelHttpClient.class);
    planeModelDto = new PlaneModelDto();
    mockSeatDto = mock(SeatDto.class);
    mockClassPassengerDto = mock(ClassPassengerDto.class);
    planeModelService = new PlaneModelService(mockPlaneModelHttpClient);
  }

  @Test
  public void givenPlaneModelWhenGetPlaneSeatsThenReturnNotEmptySeatList() {
    mockSeatDto.row = ROW;
    mockSeatDto.seat = SEAT;
    mockSeatDto.legroom = LEGROOM;
    mockSeatDto.window = WINDOW;
    mockSeatDto.clear_view = CLEAR_VIEW;
    mockSeatDto.price = PRICE;
    mockClassPassengerDto.rows = ROWS;
    mockClassPassengerDto.name = CLASS_NAME;
    SeatDto[] seatsDto = { mockSeatDto };
    planeModelDto.seats = seatsDto;
    ClassPassengerDto[] classesDto = { mockClassPassengerDto };
    planeModelDto.classes = classesDto;
    planeModelDto.exit_rows = EXIT_ROWS;
    willReturn(planeModelDto).given(mockPlaneModelHttpClient).getPlaneModelDtoAccordingPlaneModel(PLANE_MODEL);

    List<Seat> seats = planeModelService.getSeatsAccordingPlaneModel(PLANE_MODEL);

    assertFalse(seats.isEmpty());
  }
}
