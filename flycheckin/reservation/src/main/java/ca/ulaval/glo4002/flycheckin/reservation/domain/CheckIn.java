package ca.ulaval.glo4002.flycheckin.reservation.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.CheckinDto;

public class CheckIn {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column(name = "passengerHash")
  private String passengerHash;
  @Column(name = "by")
  private String by;
  @Column(name = "isvip")
  private boolean isVip;

  public CheckIn(){
  }

  public CheckIn(CheckinDto checkinDto){
    this.passengerHash = checkinDto.passenger_hash;
    this.by = checkinDto.by;
    this.isVip = checkinDto.vip;
  }

  public int getId(){
    return id;
  }

  public String getPassengerHash(){
    return passengerHash;
  }

  public String getCheckedBy(){
    return by;
  }

  public boolean getStatus(){
    return isVip;
  }
}
