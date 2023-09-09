Feature: Get data values

@data
Scenario: Get value by using key
Given User is on the home
When User enters the username "jaci" and password 123
Then Validate the result

@dataM
Scenario: Get value by using key
Given User is on the home
When User gets the values for data
	|Username 	|Test   	|
   	|Password 	|success 	|
    |Search 	|testing 	|
Then Validate the result

@dataTable
Scenario: Get value by using key
Given User is on the home
When User get the values for datatable
	|Username 	|Test1   	|
   	|Password 	|success1 	|
    |Search 	|testing1 	|
Then Validate the result

@dataTableMap
Scenario: Get value by using key
Given User is on the home
When User get the values for datatable with map
|title		|amount	|probability|commission	|
|test deal  | 1000	| 70		|10			|
|test deal1 | 1001	| 71		|11			|
|test deal2 | 1002	| 72		|12			|
|test deal3 | 1003	| 73		|13			|

Then Validate the result

@dataTableMapS
Scenario: Get value by using key
Given User is on the home
When User get the values for datatable with map separate
|title		|amount	|probability|commission	|
|test deal  | 1000	| 70		|10			|
|test deal1 | 1001	| 71		|11			|
|test deal2 | 1002	| 72		|12			|
|test deal3 | 1003	| 73		|13			|

Then Validate the result

@dataTableMapS
Scenario: Get value by using key
Given User is on the home
When User get the values for datatable with map separate
|title		|amount	|probability|commission	|
|test deal  | 1000	| 70		|10			|
|test deal1 | 1001	| 71		|11			|
|test deal2 | 1002	| 72		|12			|
|test deal3 | 1003	| 73		|13			|

Then Validate the result


