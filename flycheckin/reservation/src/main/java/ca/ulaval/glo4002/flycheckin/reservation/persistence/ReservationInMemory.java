package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class ReservationInMemory {

  private static Map<Integer, Reservation> reservationList = new HashMap<Integer, Reservation>();

  public void saveNewReservation(Reservation newReservation) throws IllegalArgumentReservationException {
    int reservationNumber = newReservation.getReservationNumber();
    if (reservationList.containsKey(reservationNumber))
      throw new IllegalArgumentReservationException("Reservation " + reservationNumber + " already exists.");
    else
      reservationList.put(reservationNumber, newReservation);
  }

  public Reservation getReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    if (reservationList.isEmpty() || !reservationList.containsKey(reservationNumber))
      throw new NotFoundReservationException("Reservation " + reservationNumber + " not found.");
    else
      return reservationList.get(reservationNumber);
  }
  
  private Passenger getPassengerByPassengerHashInReservation(int reservationNumber, String passengerHash){
    List<Passenger> passengers = reservationList.get(reservationNumber).getPassengers();
    Passenger passengerFound = null;
    for(int i = 0; i < passengers.size(); i++){
      if(passengers.get(i).getPassengerHash() == passengerHash){
        passengerFound = passengers.get(i);
      }
    }
    if(passengerFound == null){
      throw new NotFoundPassengerException("Passenger with " + passengerHash + " not found");
    }
    return passengerFound;
  }
  
  public Passenger getPassengerByPassengerHash(String passengerHash){
    Passenger passengerFound = null;
    Set<Integer> reservationNumbers = reservationList.keySet();
    Iterator<Integer> iterator = reservationNumbers.iterator();
    while(iterator.hasNext()){
      int reservationNumber = iterator.next();
      passengerFound = getPassengerByPassengerHashInReservation(reservationNumber, passengerHash);
    }
    return passengerFound;
  }
}
