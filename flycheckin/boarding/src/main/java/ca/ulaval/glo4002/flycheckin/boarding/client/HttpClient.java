package ca.ulaval.glo4002.flycheckin.boarding.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class HttpClient {

  public HttpClient() {
  }

  protected Response callUrlWithGetMethod(String url) {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url);
    return target.request(MediaType.APPLICATION_JSON_TYPE).get();
  }
}
