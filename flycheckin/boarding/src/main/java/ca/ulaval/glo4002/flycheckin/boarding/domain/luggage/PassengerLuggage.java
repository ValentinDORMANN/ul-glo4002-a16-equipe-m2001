package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class PassengerLuggage {

  @Id
  @Column(name = "passengerHash", unique = true, nullable = false)
  private String passengerHash;
  @OneToMany
  @Cascade(value = { CascadeType.ALL })
  @ElementCollection(targetClass = Luggage.class)
  private List<Luggage> luggage;

  public PassengerLuggage(String passengerHash, List<Luggage> luggage) {
    this.passengerHash = passengerHash;
    this.luggage = luggage;
  }

  public String getPassengerHash() {
    return passengerHash;
  }

  public List<Luggage> getLuggage() {
    return luggage;
  }

  public void setPassengerHash(String passengerHash) {
    this.passengerHash = passengerHash;
  }

  public void setLuggage(List<Luggage> luggage) {
    this.luggage = luggage;
  }
}
