package ca.ulaval.glo4002.flycheckin.reservation;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;

public class ReservationServer implements Runnable {

	public static void main(String[] args) {
		new ReservationServer().run();
	}

	public void run() {
		setData();
		int httpPort = Integer.valueOf(System.getProperty("reservation.port"));

		Server server = new Server(httpPort);
		ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
		configurerJersey(servletContextHandler);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.destroy();
		}
	}

	private void configurerJersey(ServletContextHandler servletContextHandler) {
		ServletContainer container = new ServletContainer(new ResourceConfig()
				.packages("ca.ulaval.glo4002.flycheckin.reservation").register(JacksonFeature.class));
		ServletHolder jerseyServletHolder = new ServletHolder(container);
		servletContextHandler.addServlet(jerseyServletHolder, "/*");
	}

	private void setData() {
		String stringJSON = "{'reservation_number': 74563,'reservation_date': '2016-01-31','reservation_confirmation': 'A3833','flight_number': 'AC1765','flight_date': '2016-10-30T00:00:00Z','payment_location': '/payments/da39a3ee5e6b4b0d3255bfef95601890afd80709','passengers': [{ 'first_name': 'Virgil','last_name': 'VOILLOT','age': 18,'passport_number': '7HTDEFRE','seat_class': 'economy'}]}";
		JSONObject json = new JSONObject(stringJSON);
		Services service = new Services();
		service.createReservation(json);
		stringJSON = "{'reservation_number': 65329,'reservation_date': '2016-01-31','reservation_confirmation': 'A3933','flight_number': 'AC1765','flight_date': '2016-10-30T00:00:00Z','payment_location': '/payments/da39a3ee5e6b4b0d3255bfef95601890afd80709','passengers': [{'first_name': 'Autre','last_name': 'FRANCE','age': 18,'passport_number': '5KDTERWSA','seat_class': 'economy'}]}";
		json = new JSONObject(stringJSON);
		service.createReservation(json);
	}
}
