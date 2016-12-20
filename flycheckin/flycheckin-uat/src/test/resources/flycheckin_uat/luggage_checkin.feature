Feature: Luggage Check-in
    In order to complete my check-in
    As a flight passenger
    I want to be able to add a luggage 
    
Scenario: Adding a luggage adjust luggages fees
  Given a passenger Bob with an economy class reservation on flight AC-1234
  And already has a checked luggage meeting the standards
  When He registers another checked luggage weighing 10kg and size 100cm
  Then the amount to pay is 50$