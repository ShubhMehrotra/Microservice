

# ONE to MANY example with Spring Boot JPA & Hibernate 

This Application is simply demonstrating the one to many realtionship between the User & Address entity . A user can have many addresses at a time (For example a user can have different billing address and a shipping address).




## Author

- [@ShubhMehrotra](https://www.github.com/ShubhMehrotra)


## Pre Requisite /Installation
* Any IDE Intellije or Eclipse or STS
* JDK 17 or above 
* Postman or Swagger 
* You can simply download the code as a zip and  or you can simply download the code by using ``` gh repo clone ShubhMehrotra/Microservice```

## How to run the code 
* Import the project as maven project .
* Update/re-load the project to get all the dependencies downloaded.
* Simply run the ```MicroserviceApplication.java```
* Start using the MicroserviceApplication through Postman or Swagger



## This is the ER diagram 
![image](https://github.com/ShubhMehrotra/Microservice/assets/60496852/9d424a11-af62-4b9d-92ad-ea2bc710dd61)

## Payload

You can refer the following payload 

```{

  "user_Name":"Shubh Mehrotra",
  "user_Email":"User@gmail.com",
  "addresses":[
    {
      "door_No":"345",
      "street_Name":"New Blackwood",
      "city":"LiverPool",
      "pin_Code":"34567"
    },
    {
      "door_No":"456",
      "street_Name":"Lombok Street",
      "city":"Manchester",
      "pin_Code":"88888"
    }



  ]


}
