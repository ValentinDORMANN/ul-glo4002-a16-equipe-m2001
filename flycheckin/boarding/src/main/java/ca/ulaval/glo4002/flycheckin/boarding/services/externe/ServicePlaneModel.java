package ca.ulaval.glo4002.flycheckin.boarding.services.externe;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.client.PlaneModelHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PlaneModelDto;

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
    List<Seat> seats = transformPlaneModelDtoToSeats();
    return seats;
  }

  private List<Seat> transformPlaneModelDtoToSeats() {
    List<Seat> seats = new ArrayList<Seat>();
    return seats;
  }
}
