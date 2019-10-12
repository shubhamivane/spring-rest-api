## spring-rest-api
Rest api is developed for app like TrueCaller to manage the contact numbers. Link for app is [https://truecaller-spring.herokuapp.com](https://truecaller-spring.herokuapp.com).
# Endpoints
 * POST - Create User [https://truecaller-spring.herokuapp.com/user](https://truecaller-spring.herokuapp.com)
 ```
 	JSON Payload
	{ "name": "shubham", "phoneNo": "123456789", "password": "123456" }
	
  	Response Body
	{ "message": "User account created.", "status": "OK" }
 ```
 * POST - Get Access Token [https://truecaller-spring.herokuapp.com/accesstoken](https://truecaller-spring.herokuapp.com/accesstoken)
 ```
 	JSON Payload
	{ "phoneNo": "123456789", "password": "123456" }
 
 	Response Body
	{ "id": "226fb86aad4e4982bc04924a7eeb857e", "name": "shubham", "phoneNo": "123456789", 
	  "accessToken": "b49910db-4053-41f2-9a66-ff102d33d840" }
 ```
 * GET - Get User data [https://truecaller-spring.herokuapp.com/user/{id}](https://truecaller-  spring.herokuapp.com/user/226fb86aad4e4982bc04924a7eeb857e)
 ```
 	Response Body
	{ "name": "shubham", "phoneNo": "123456789", "id": "cc82f526ab5644d7b0843f03801d6285" }
 ```
 * POST - Update User data [https://truecaller-spring.herokuapp.com/user/{id}](https://truecaller-spring.herokuapp.com/user/226fb86aad4e4982bc04924a7eeb857e)
 ```
 	JSON Payload
	{ "name": "ankit", "new_name": "ankit1", "accessToken": "f96e1e18-753f-485a-8af6-bc42063f053d" }
	
	Response Body
	{ "message": "Name is updated successfully.", "status": "OK" }
 ```
 * 
 
 	
