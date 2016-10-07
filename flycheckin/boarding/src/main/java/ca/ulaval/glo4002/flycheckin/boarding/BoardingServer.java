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

	public void run() {
<<<<<<< HEAD
		int httpPort = 8090; // 8080 already reserved
=======
		// int httpPort = 8090; // 8080 already reserved
		int httpPort = Integer.valueOf(System.getProperty("boarding.port"));
>>>>>>> ee79c0bf1682cf4b14b1711e09f4a21951b067b9

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
		ServletContainer container = new ServletContainer(
				new ResourceConfig().packages("ca.ulaval.glo4002.flycheckin.boarding"));
		ServletHolder jerseyServletHolder = new ServletHolder(container);
		servletContextHandler.addServlet(jerseyServletHolder, "/*");
	}
}