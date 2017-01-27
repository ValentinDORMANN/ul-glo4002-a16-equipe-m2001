package ca.ulaval.glo4002.flycheckin.reservation.rest;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestTestBase {

  protected RequestSpecification givenBaseRequest() {
    return given().accept(ContentType.JSON).port(RestTestSuite.TEST_SERVER_PORT).contentType(ContentType.JSON);
  }

  protected Map<String, Object> buildPathParams(String key, Object value) {
    Map<String, Object> params = new HashMap<>();
    params.put(key, value);
    return params;
  }
}
