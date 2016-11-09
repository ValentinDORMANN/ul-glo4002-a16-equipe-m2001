package ca.ulaval.glo4002.flycheckin.app;

import ca.ulaval.glo4002.flycheckin.boarding.BoardingServer;
import ca.ulaval.glo4002.flycheckin.reservation.ReservationServer;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Thread reservationThread = new Thread(new ReservationServer());
    Thread boardingThread = new Thread(new BoardingServer());
    reservationThread.start();
    boardingThread.start();
    reservationThread.join();
    boardingThread.join();
  }
}