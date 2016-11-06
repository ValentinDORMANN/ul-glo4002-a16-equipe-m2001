package ca.ulaval.glo4002.flycheckin.boarding.services.externe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.client.PlaneModelHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ClassPassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatDto;

public class ServicePlaneModel {

  private PlaneModelHttpClient planeModelHttpClient;

  public ServicePlaneModel() {
    planeModelHttpClient = new PlaneModelHttpClient();
  }

  public ServicePlaneModel(PlaneModelHttpClient serviceHttp) {
    this.planeModelHttpClient = serviceHttp;
  }

  public List<Seat> getSeatsAccordingPlaneModel(String planeModel) {
    PlaneModelDto planeModelDto = planeModelHttpClient.getPlaneModelDtoAccordingPlaneModel(planeModel);
    List<Seat> seats = transformPlaneModelDtoToSeats(planeModelDto);
    return seats;
  }

  private List<Seat> transformPlaneModelDtoToSeats(PlaneModelDto planeModelDto) {
    List<Seat> seats = new ArrayList<Seat>();
    for (SeatDto seatDto : planeModelDto.seats) {
      seats.add(createSeat(seatDto, planeModelDto.classes));
    }
    return seats;
  }

  private Seat createSeat(SeatDto seatDto, ClassPassengerDto[] classes) {
    for (ClassPassengerDto classPassengerDto : classes) {
      if (contains(classPassengerDto.rows, seatDto.row))
        return new Seat(seatDto, classPassengerDto.name);
    }
    Seat seat = new Seat();
    return seat;
  }

  private boolean contains(final int[] array, final int key) {
    return Arrays.asList(array).contains(key);
  }
}
