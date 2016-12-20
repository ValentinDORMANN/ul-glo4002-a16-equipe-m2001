Feature: Luggage Check-in
    In order to complete my check-in
    As a flight passenger
    I want to be able to add a luggage 
    
Scenario Outline: Adding a luggage adjust luggages fees
  Given a passenger "<name>" with an "<seatClass>" class reservation on flight "<flightNumber>"
  And already has a "<type>" luggage meeting the standards
  When He registers another "<type>" luggage weighing 10kg and size 100cm
  Then the amount to pay is 50$
  
  Examples:
  |name|seatClass|flightNumber|type|
  |Bob |economy  |AC-1234     |checked|