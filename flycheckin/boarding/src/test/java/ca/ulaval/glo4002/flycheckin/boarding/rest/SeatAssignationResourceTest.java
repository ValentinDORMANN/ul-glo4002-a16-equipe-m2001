package ca.ulaval.glo4002.flycheckin.boarding.rest;

public class SeatAssignationResourceTest {

  /*
   * TODO
  private static final String PASSENGER_HASH = "HASH";
  private static final String WRONG_PASSENGER_HASH = "FAKE";
  private static final int STATUS_OK = 200;
  private static final int STATUS_NOT_FOUND = 404;
  private HttpClient httpServices;
  private ReservationDto reservationDto;
  private SeatAssignationDto seatAssignationDto;
  private ResourceSeatAssignation seatAssignationResource;
  private Response response;
  
  @Before
  public void initiateTest() throws Exception {
    httpServices = mock(HttpClient.class);
    reservationDto = mock(ReservationDto.class);
    seatAssignationDto = mock(SeatAssignationDto.class);
    seatAssignationResource = new ResourceSeatAssignation(httpServices);
    willReturn(reservationDto).given(httpServices).getReservationDtoFromReservation(PASSENGER_HASH);
    seatAssignationDto.passenger_hash = PASSENGER_HASH;
  }
  
  @Test
  public void givenWrongPassengerHashWhenGetReservationThenReturnStatusNotFound() throws Exception {
    willThrow(NotFoundPassengerException.class).given(httpServices)
        .getReservationDtoFromReservation(WRONG_PASSENGER_HASH);
    seatAssignationDto.passenger_hash = WRONG_PASSENGER_HASH;
    response = seatAssignationResource.assignSeatToPassenger(seatAssignationDto);
    assertEquals(STATUS_NOT_FOUND, response.getStatus());
  }
  
  @Test
  public void givenPassengerHashWhenGetReservationThenReturnStatusOk() throws Exception {
    response = seatAssignationResource.assignSeatToPassenger(seatAssignationDto);
    assertEquals(STATUS_OK, response.getStatus());
  }*/
}
