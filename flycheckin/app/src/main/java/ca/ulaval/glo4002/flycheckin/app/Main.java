package ca.ulaval.glo4002.flycheckin.app;

import ca.ulaval.glo4002.flycheckin.boarding.BoardingServer;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Thread boardingThread = new Thread(new BoardingServer());

    boardingThread.start();

    boardingThread.join();
  }
}