Feature: Seat assignation 
	In order to complete my reservation
    As a flight passenger
    I want to be able to retreive my seat number
    
Scenario Outline: 
	Given a passenger "<name>" with a reservation on a flight 
	And seats are available on this flight 
	And he already done his check-in 
	When he chooses his seat wiht the "<mode>" mode 
	Then a seat is assigned to him on that flight 
	
	Examples: 
		|name ||mode|
		|Alice||random|
