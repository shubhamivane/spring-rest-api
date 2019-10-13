# spring-rest-api
Rest api is developed for app like TrueCaller to manage the contact numbers. Link for app is [https://truecaller-spring.herokuapp.com](https://truecaller-spring.herokuapp.com).
## Endpoints
 * POST - Create user [https://truecaller-spring.herokuapp.com/user](https://truecaller-spring.herokuapp.com)
 ```
 	JSON Payload
	{ "name": "shubham", "phoneNo": "123456789", "password": "123456" }
	
  	Response Body
	{ "message": "User account created.", "status": "OK" }
 ```
 * POST - Get access token [https://truecaller-spring.herokuapp.com/accesstoken](https://truecaller-spring.herokuapp.com/accesstoken)
 ```
 	JSON Payload
	{ "phoneNo": "123456789", "password": "123456" }
 
 	Response Body
	{ "id": "226fb86aad4e4982bc04924a7eeb857e", "name": "shubham", "phoneNo": "123456789", 
	  "accessToken": "b49910db-4053-41f2-9a66-ff102d33d840" }
 ```
 * GET - Get user data [https://truecaller-spring.herokuapp.com/user/{id}](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285)
 ```
 	Response Body
	{ "name": "shubham", "phoneNo": "123456789", "id": "cc82f526ab5644d7b0843f03801d6285" }
 ```
 * POST - Update user data [https://truecaller-spring.herokuapp.com/user/{id}](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285)
 ```
 	JSON Payload
	{ "name": "ankit", "new_name": "ankit1", "accessToken": "f96e1e18-753f-485a-8af6-bc42063f053d" }
	
	Response Body
	{ "message": "Name is updated successfully.", "status": "OK" }
 ```
 * DELETE - Delete user [https://truecaller-spring.herokuapp.com/user/{id}](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285)
 ```
 	JSON Payload
	{ "accessToken": "f96e1e18-753f-485a-8af6-bc42063f053d" }
	
	Response Body
 	{ "message": "User with df25a70eae74453a8323d40644978ea7 deleted successfully.", "status": "OK" }
 ```
 * POST - Create contact [https://truecaller-spring.herokuapp.com/user/{id}/contact/](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285/contact/)
 ```
 	JSON Payload
	{ "accessToken": "66de002b-25c4-45b2-8530-fd5ecb1b4583", "name": "ankit", "phoneNo": "9144212526" }
	
	Response Body
	{ "id": 1, "name": "ankit", "phone_no": "9144212526" }
 ```
 * GET - Get contact list of user [https://truecaller-spring.herokuapp.com/user/{id}/contact/](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285/contact/)
 ```
 	JSON Payload
	{ "accessToken": "66de002b-25c4-45b2-8530-fd5ecb1b4583"}
	 
	Response Body
	[
    		{ "id": 1, "name": "ankit", "phone_no": "9144212526" },
    		{ "id": 2, "name": "virat", "phone_no": "12345" }
	]
 ```
 * GET - Get contact from user's contact list [https://truecaller-spring.herokuapp.com/user/{user_id}/contact/{contact_id}](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285/contact/1)
 ```
 	JSON Payload
	{ "accessToken": "66de002b-25c4-45b2-8530-fd5ecb1b4583" }
	
	Response Body
	{ "id": 1, "name": "ankit", "phone_no": "9144212526" }
 ```
 * DELETE - Delete contact from user's contact list [https://truecaller-spring.herokuapp.com/user/{id}/contact/{id}](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285/contact/1)
 ```
 	JSON Payload
	{ "accessToken": "66de002b-25c4-45b2-8530-fd5ecb1b4583" }
	
	Response Body
	{ "message": "Contact with Phone number 12345 is deleted successfully.", "status": "OK" }
 ```
 * POST - Search contact in Database [https://truecaller-spring.herokuapp.com/search](https://truecaller-spring.herokuapp.com/search)
 ```
 	JSON Payload
	{ "phoneNo": "9144212526" }
	
	Response Body
	{ "id": 1, "name": "ankit", "phone_no": "9144212526" }
 ```
 * POST - Search contact in user's contact list [https://truecaller-spring.herokuapp.com/user/{id}/search](https://truecaller-spring.herokuapp.com/user/cc82f526ab5644d7b0843f03801d6285/search)
 ```
 	JSON Payload
	{ "accessToken": "66de002b-25c4-45b2-8530-fd5ecb1b4583", "phoneNo": "9144212526" }
	
	Response Body
	{ "id": 1, "name": "ankit", "phone_no": "9144212526" }
 ```

 
 	
