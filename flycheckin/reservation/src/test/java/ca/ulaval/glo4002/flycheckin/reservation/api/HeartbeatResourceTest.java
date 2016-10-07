package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;

public class HeartbeatResourceTest extends JerseyTest{
	//private HeartbeatResource heartbeatResource;
	private  Response response;
	@Before
	public Application initialiaze(){
		return new ResourceConfig(HeartbeatResource.class);
		
	}
	public void givenArequestWhenHeartbeatIsInvokeThenReturnStatus(){
		response = target("/heartbeat").queryParam("token", "").request().get();
		
		assertEquals(response.getStatus(),200);
		
	}
	
}
