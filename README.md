# event-planning
RESTful API for an Event Planning company to provide quote estimates to its clients

## This project has 2 endpoints:


### /v1/quote: 
This is a POST request that the user can use to get the quote estimate for a given input. The input to the API will be a JSON as follows:
```
{
	"headCount": 12,
	"eventType": "MUSICAL",
	"eventDate": "2018-11-28",
	"phoneNumber": "1234567890",
	"city": "Dallas",
	"email": "testEmail@gmail.com",
	"name": "Test user"
}
```
Where the fields can be described as follows:
- headCount: Number of people attending the event
- eventType: Type of event (Enum). Can have the following values: CONFERENCE, MEETING, MUSICAL, FAMILY, OTHER
- eventDate: Date on which the event is to be scheduled
- phoneNumber: Contact phone number
- city: City in which the event is to be taken place
- email: Contact email ID
- name: Contact name

The response of the API will be a JSON as follows:
```
{
    "quoteId": "EQ1F0vJ1",
    "quotePrice": 26000,
    "_links": {
        "self": {
            "href": "http://localhost:10001/v1/quote/EQ1F0vJ1"
        }
    }
}
```
Where the fields can be described as follows:
- quoteId: Reference Id that can be used to later access the quote details
- quotePrice: Quote estimate for the request
- _links.self.href: Link that can be used to access the quote details


### /v1/quote/{quoteId}
This is a GET request that the user can use to get the details of the quote that he might have created in the past. {quoteId} represents the quote reference id that the user received when they got the initial quote
The response of the API will be a JSON as follows:
```
{
    "quoteId": "EQZ4CF7w",
    "headCount": 12,
    "eventType": "MUSICAL",
    "eventDate": "2018-11-28",
    "quotePrice": 26000,
    "name": "Test user",
    "phoneNumber": "1234567890",
    "email": "testEmail@gmail.com",
    "city": "Dallas",
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:10001/v1/EQZ4CF7w"
        }
    ]
}
```