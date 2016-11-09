package ca.ulaval.glo4002.flycheckin.boarding;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class BoardingServer implements Runnable {

  public static void main(String[] args) {
    new BoardingServer().run();
  }

  @Override
  public void run() {
    int httpPort = Integer.valueOf(System.getProperty("boarding.port"));

    Server server = new Server(httpPort);
    ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
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

  private void configurerJersey(ServletContextHandler servletContextHandler) {
    ServletContainer container = new ServletContainer(
        new ResourceConfig().packages("ca.ulaval.glo4002.flycheckin.boarding"));
    ServletHolder jerseyServletHolder = new ServletHolder(container);
    servletContextHandler.addServlet(jerseyServletHolder, "/*");
  }
}
