package ca.ulaval.glo4002.flycheckin.boarding.domain;

import javax.persistence.Entity;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.SeatDto;

@Entity
public class Seat {

  private String seatClass; // economy, premium-economy, big-front, business
  private String seatNumber;
  private int legroom;
  private boolean isNearWindow;
  private boolean isClearView;
  private double price;

  public Seat() {
  }

  public Seat(SeatDto seatDto, String seatClass) {
    this.seatClass = seatClass;
    setSeatNumber(seatDto.row, seatDto.seat);
    this.legroom = seatDto.legroom;
    this.isNearWindow = seatDto.window;
    this.isClearView = seatDto.clear_view;
    this.price = seatDto.price;
  }

  public Seat(String seatClass, int row, String column, int legroom, boolean isNearWindow, boolean isClearView,
      double price) {
    this.seatClass = seatClass;
    setSeatNumber(row, column);
    this.legroom = legroom;
    this.isNearWindow = isNearWindow;
    this.isClearView = isClearView;
    this.price = price;
  }

  public String getSeatClass() {
    return seatClass;
  }

  public void setSeatClass(String seatClass) {
    this.seatClass = seatClass;
  }

  public void setSeatNumber(int row, String column) {
    this.seatNumber = Integer.toString(row) + "-" + column.toUpperCase();
  }

  public String getSeatNumber() {
    return this.seatNumber;
  }

  public int getLegroom() {
    return legroom;
  }

  public boolean isNearWindow() {
    return isNearWindow;
  }

  public boolean isClearView() {
    return isClearView;
  }

  public double getPrice() {
    return price;
  }

}
