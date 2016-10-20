package ca.ulaval.glo4002.flycheckin.app;

import ca.ulaval.glo4002.flycheckin.reservation.ReservationServer;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Thread reservationThread = new Thread(new ReservationServer());

    reservationThread.start();
    reservationThread.join();
  }
}