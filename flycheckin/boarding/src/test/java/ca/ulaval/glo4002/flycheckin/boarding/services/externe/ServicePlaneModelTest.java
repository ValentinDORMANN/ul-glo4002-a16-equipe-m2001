package ca.ulaval.glo4002.flycheckin.boarding.services.externe;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.client.PlaneModelHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ClassPassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatDto;

public class ServicePlaneModelTest {

  private static final String CLASS_NAME = "economic";
  private static final int[] ROWS = { 1 };
  private static final String PLANE_MODEL = "a320";
  private PlaneModelHttpClient mockPlaneModelHttpClient;
  private PlaneModelDto mockPlaneModelDto;
  private SeatDto mockSeatDto;
  private ClassPassengerDto mockClassPassengerDto;
  private ServicePlaneModel servicePlaneModel;

  @Before
  public void initiateTest() {
    mockPlaneModelHttpClient = mock(PlaneModelHttpClient.class);
    mockPlaneModelDto = mock(PlaneModelDto.class);
    mockSeatDto = mock(SeatDto.class);
    mockClassPassengerDto = mock(ClassPassengerDto.class);
    servicePlaneModel = new ServicePlaneModel(mockPlaneModelHttpClient);
  }

  @Test
  public void givenPlaneModelWhenSeatsNotExistThenVerifyListNotEmpty() {
    mockClassPassengerDto.rows = ROWS;
    mockClassPassengerDto.name = CLASS_NAME;
    SeatDto[] seatsDto = { mockSeatDto };
    mockPlaneModelDto.seats = seatsDto;
    ClassPassengerDto[] classesDto = { mockClassPassengerDto };
    mockPlaneModelDto.classes = classesDto;
    willReturn(mockPlaneModelDto).given(mockPlaneModelHttpClient).getPlaneModelDtoAccordingPlaneModel(PLANE_MODEL);

    List<Seat> seats = servicePlaneModel.getSeatsAccordingPlaneModel(PLANE_MODEL);
    assertFalse(seats.isEmpty());
  }

}
