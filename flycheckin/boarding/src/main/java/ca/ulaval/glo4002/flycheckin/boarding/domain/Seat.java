package ca.ulaval.glo4002.flycheckin.boarding.domain;

import javax.persistence.Entity;

@Entity
public class Seat {

  String seatClass;  // economy, premium-economy, big-front, business
  int row;
  String column;
  int legroom;
  boolean isNearWindow;
  boolean isClearView;
  double price;
  
  public Seat(){
  }

  public Seat(String seatClass, int row, String column, int legroom, boolean isNearWindow, boolean isClearView,
              double price) {
    this.seatClass = seatClass;
    this.row = row;
    this.column = column.toUpperCase();
    this.legroom = legroom;
    this.isNearWindow = isNearWindow;
    this.isClearView = isClearView;
  }
  
  public String getSeatClass() {
    return seatClass;
  }
  
  public String getSeatNumber() {
    return Integer.toString(row) + "-" + column;
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
