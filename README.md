## spring-rest-api
Rest api is developed for app like TrueCaller to manage the contact numbers. Link for app is [https://truecaller-spring.herokuapp.com](https://truecaller-spring.herokuapp.com).
# Endpoints
 * POST - [https://truecaller-spring.herokuapp.com/user](https://truecaller-spring.herokuapp.com)
 ```
 	JSON Payload
	{
		"name": "shubham",
		"phoneNo": "123456789",
		"password": "123456"
	}
	
  	Response Body
	{
 		"message": "User account created.",
    		"status": "OK"
	}
 ```
 * POST - [https://truecaller-spring.herokuapp.com/accesstoken](https://truecaller-spring.herokuapp.com/accesstoken)
 ```
 	JSON Payload
	{
		"phoneNo": "123456789",
		"password": "123456"
	}
 
 	Response Body
	{
    		"id": "226fb86aad4e4982bc04924a7eeb857e",
    		"name": "shubham",
    		"phoneNo": "123456789",
    		"accessToken": "b49910db-4053-41f2-9a66-ff102d33d840"
	}
 ```
 * GET - [https://truecaller-spring.herokuapp.com/user/{id}](https://truecaller-spring.herokuapp.com/user/226fb86aad4e4982bc04924a7eeb857e)
 ```
 	Response Body
	{
		"name": "shubham",
		"phoneNo": "123456789",
		"id": "cc82f526ab5644d7b0843f03801d6285"
	}
 ```
 
 	
