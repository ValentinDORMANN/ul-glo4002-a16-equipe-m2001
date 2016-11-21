package ca.ulaval.glo4002.flycheckin.boarding.services.external;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.client.PlaneModelHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ClassPassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatDto;

public class PlaneModelService {

  private PlaneModelHttpClient planeModelHttpClient;

  public PlaneModelService() {
    planeModelHttpClient = new PlaneModelHttpClient();
  }

  public PlaneModelService(PlaneModelHttpClient serviceHttp) {
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
      try {
        seats.add(createSeat(seatDto, planeModelDto.classes));
      } catch (BoardingModuleException ex) {
      }
    }
    return seats;
  }

  private Seat createSeat(SeatDto seatDto, ClassPassengerDto[] classes) throws BoardingModuleException {
    for (ClassPassengerDto classPassengerDto : classes) {
      if (contains(classPassengerDto.rows, seatDto.row))
        return constructSeatFromDto(seatDto, classPassengerDto.name);
    }
    throw new BoardingModuleException();
  }

  private boolean contains(final int[] array, final int key) {
    for (int value : array) {
      if (value == key)
        return true;
    }
    return false;
  }

  private Seat constructSeatFromDto(SeatDto seatDto, String seatClass) {
    String seatNumber = Integer.toString(seatDto.row) + "-" + seatDto.seat.toUpperCase();
    Seat seat = new Seat(seatClass, seatNumber, seatDto.legroom, seatDto.window, seatDto.clear_view, seatDto.price);
    return seat;
  }
}
