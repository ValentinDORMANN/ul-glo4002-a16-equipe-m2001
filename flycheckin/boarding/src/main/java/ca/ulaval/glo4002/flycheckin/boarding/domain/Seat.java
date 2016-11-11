package ca.ulaval.glo4002.flycheckin.boarding.domain;

public class Seat {

  private String seatClass;
  private String seatNumber;
  private int legroom;
  private boolean isNearWindow;
  private boolean isClearView;
  private double price;

  public Seat() {
  }

  public Seat(String seatClass, String seatNumber, int legroom, boolean isNearWindow, boolean isClearView,
      double price) {
    this.seatClass = seatClass;
    this.seatNumber = seatNumber;
    this.legroom = legroom;
    this.isNearWindow = isNearWindow;
    this.isClearView = isClearView;
    this.price = price;
  }

  public boolean isCheaperThan(Seat otherSeat) {
    return this.price < otherSeat.price;
  }

  public boolean isLegroomGreaterThan(Seat otherSeat) {
    return this.legroom > otherSeat.legroom;
  }

  public boolean hasSameLegroomWith(Seat otherSeat) {
    return this.legroom == otherSeat.legroom;
  }

  public boolean hasClass(String seatClass) {
    return this.seatClass.equals(seatClass);
  }

  public String getSeatClass() {
    return seatClass;
  }

  public void setSeatClass(String seatClass) {
    this.seatClass = seatClass;
  }

  public String getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(String seatNumber) {
    this.seatNumber = seatNumber;
  }

  public int getLegroom() {
    return legroom;
  }

  public void setLegroom(int legroom) {
    this.legroom = legroom;
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

  public void setPrice(double price) {
    this.price = price;
  }
}
