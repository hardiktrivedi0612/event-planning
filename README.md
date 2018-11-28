# Event Planning

RESTful API for an Event Planning company to provide quote estimates to its clients

## Implementation Details:
- RESTFul APIs created using Spring Boot
- IDE used Spring Tool Suite
- Yahoo Weather API used to get weather forecast
- Swagger used for API documentation
- JUnit used for unit testing
- Used Memory (Map) as a datastore to save the request details to be retrieved later. So the data will be lost if the application is restarted.

## How to run:
- Import the project to an IDE such as Eclipse, Spring Tool Suite that can build and run Maven projects
- Run the web service. We can run the web services in 2 ways:
    1. Run the application directly on the IDE.
    2. Build the project on the IDE to create the jar file and then run the JAR file.
- Navigate to a browser and navigate to "http://localhost:10001/swagger-ui.html". This will give the documentation for the API
- Open Postman and test the endpoints using the Swagger documentation. Or use the link: [Postman Import] (https://www.getpostman.com/collections/4e7ba20b4bb9af5eb785) to import the collection directly into your Postman


## Assumptions made by me for this project:
- A minimum of 1 head count will be be required to give a quote i.e. we cannot estimate a price for 0 or less people.
- An event can be scheduled only after today.
- Ranges provided for head count quote estimate calculation are start exclusive and end inclusive. And there are no gaps in the ranges.
- Yahoo weather API failures would lead to us not adding the charge to the quote
- Yahoo weather API only forecasts for 10 days, so for event dates later than that would not have that charge added to the quote.


## This project has 3 endpoints:
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

### /v1/quotes
This is a GET request that the user can use to get all the quotes that have been created by the users. Only certain authorized users will have access to this endpoint.
The response for this API can be given as follows:
```
[
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
    },
    {
        "quoteId": "EQG03ttC",
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
                "href": "http://localhost:10001/v1/EQG03ttC"
            }
        ]
    },
    {
        "quoteId": "EQnSveh1",
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
                "href": "http://localhost:10001/v1/EQnSveh1"
            }
        ]
    }
]
```


## The quote is determined based on the following rules:
- Head count: 0-100 the price per person 2000. 100-500 the price will be 1500, 500 and up price is 1000.
- Weather condition if the weather forecast shows that it will be cold or rainy in the requested date, 5000 flat lee will be added. 
- If the event will be in January, November, or December 3000 flat fee will be added to the total 
- For musical events, 1000 discount should be applied.