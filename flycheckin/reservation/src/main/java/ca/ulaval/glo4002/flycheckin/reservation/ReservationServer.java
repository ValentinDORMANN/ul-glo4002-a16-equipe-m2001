package ca.ulaval.glo4002.flycheckin.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.flycheckin.reservation.persistence.EntityManagerProvider;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.ReservationDto;
import ca.ulaval.glo4002.flycheckin.reservation.rest.filters.EntityManagerContextFilter;

public class ReservationServer implements Runnable {

  private Server server;

  public static void main(String[] args) {
    new ReservationServer().run();
  }

  @Override
  public void run() {
    int httpPort = Integer.valueOf(System.getProperty("reservation.port"));
    start(httpPort);
  }

  public void start(int httpPort) {
    server = new Server(httpPort);
    ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
    servletContextHandler.addFilter(EntityManagerContextFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
    configurerJersey(servletContextHandler);
    fillDataBase();
    try {
      server.start();
      server.join();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      server.destroy();
    }
  }

  private void configurerJersey(ServletContextHandler servletContextHandler) {
    ServletContainer container = new ServletContainer(
        new ResourceConfig().packages("ca.ulaval.glo4002.flycheckin.reservation"));
    ServletHolder jerseyServletHolder = new ServletHolder(container);
    servletContextHandler.addServlet(jerseyServletHolder, "/*");
  }

  private void fillDataBase() {
    EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityManagerProvider.setEntityManager(entityManager);

    PassengerDto passengerDto = new PassengerDto();
    passengerDto.first_name = "Virgil";
    passengerDto.last_name = "Voillot";
    passengerDto.age = 23;
    passengerDto.passport_number = "7HTDEFRE";
    passengerDto.seat_class = "economy";
    List<PassengerDto> passengers = new ArrayList<PassengerDto>();
    passengers.add(passengerDto);
    ReservationDto reservationDto = new ReservationDto();
    reservationDto.passengers = passengers;
    reservationDto.reservation_number = 74563;
    reservationDto.reservation_date = new Date();
    reservationDto.reservation_confirmation = "A3833";
    reservationDto.flight_number = "AC1765";
    reservationDto.flight_date = new Date();
    reservationDto.payment_location = "/payments/da39a3ee5e6b4b0d3255bfef95601890afd80709";
    Reservation reservation = new Reservation(reservationDto);

    EntityManagerProvider.clearEntityManager();
    entityManager.close();
  }

  public void stop() {
    try {
      server.stop();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}