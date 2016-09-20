package ca.ulaval.glo6002.flycheckin.app;

import ca.ulaval.glo6002.flycheckin.reservation.ReservationServer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		Thread resevationThread = new Thread(new ReservationServer());

		resevationThread.start();
		resevationThread.join();
	}
}