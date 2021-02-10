Feature: Validate Library Apis

Scenario Outline: Validate Add Place Api
Given Add place api is available with "<name>" "<language>" "<address>"
When User performs "AddPlaceAPI" with "POST" Request
Then Check the status code is "<code>"
And "status" in response body is "OK"
And verify place_id created is mapped to "<name>" using "GetPlaceAPI"

Examples:
|name    |language    |address        |code|
| Ramesh |Telugu      |   Hyderabad   |200 |
# |Nagesh  |English     |   Khammam     |200 | 

Scenario Outline: validate update place APi
Given update place api is available
When User performs "UpdatePlaceAPI" with "PUT" Request
Then Check the status code is "<code>"
And "msg" in response body is "Address successfully updated"

Examples:
|code|
|200|

 Scenario Outline: validate delete place APi
Given delete place api is available
When User performs "DeletePlaceAPI" with "POST" Request
Then Check the status code is "<code>"

Examples:
|code|
|200 |
|404 |


 #Scenario: validate delete place APi
#Given delete place api is available
#When User performs "DeletePlaceAPI" with "POST" Request
#Then Check the status code is 404




