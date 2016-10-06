package ca.ulaval.glo4002.flycheckin.reservation;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ReservationServer implements Runnable {

	public static void main(String[] args) {
		new ReservationServer().run();
	}

	public void run() {
		// int httpPort = 8080;
		int httpPort = Integer.valueOf(System.getProperty("reservation.port"));

		Server server = new Server(httpPort);
		ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
		configurerJersey(servletContextHandler);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace(); // Une des rare fois qu'on peut!
		} finally {
			server.destroy();
		}
	}

	private void configurerJersey(ServletContextHandler servletContextHandler) {
		ServletContainer container = new ServletContainer(new ResourceConfig()
				.packages("ca.ulaval.glo6002.flycheckin.reservation").register(JacksonFeature.class));
		ServletHolder jerseyServletHolder = new ServletHolder(container);
		servletContextHandler.addServlet(jerseyServletHolder, "/*");
	}
}
