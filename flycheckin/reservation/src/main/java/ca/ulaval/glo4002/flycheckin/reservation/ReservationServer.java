package ca.ulaval.glo4002.flycheckin.reservation;

import java.util.EnumSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.flycheckin.reservation.api.filters.EntityManagerContextFilter;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.EntityManagerFactoryProvider;

public class ReservationServer implements Runnable {

  public static void main(String[] args) {
    new ReservationServer().run();
  }

  @Override
	public void run() {
		entityManagerCreater();
		startServer();
	}
  
  private void startServer() {
    int httpPort = Integer.valueOf(System.getProperty("reservation.port"));

    Server server = new Server(httpPort);
    ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
    servletContextHandler.addFilter(EntityManagerContextFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
    configurerJersey(servletContextHandler);
    try {
      server.start();
      server.join();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      server.destroy();
    }
  }
  
	private void entityManagerCreater() {
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
	}
	
  private void configurerJersey(ServletContextHandler servletContextHandler) {
    ServletContainer container = new ServletContainer(
        new ResourceConfig().packages("ca.ulaval.glo4002.flycheckin.reservation"));
    ServletHolder jerseyServletHolder = new ServletHolder(container);
    servletContextHandler.addServlet(jerseyServletHolder, "/*");
  }
}