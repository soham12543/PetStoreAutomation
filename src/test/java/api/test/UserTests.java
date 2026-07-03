package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User payload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		payload = new User();
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().cellPhone());
		payload.setUserStatus(0);
		
		//logs ( For debug level you have to change the config )
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void postUser()
	{
		logger.info("Creating User-------->");
		Response response=UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		logger.info("User Created-------->");
	}
	
	@Test(priority=2)
	public void getUser()
	{
		Response response=UserEndPoints.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		logger.info("Getting User Created-------->");
	}
	
	@Test(priority=3)
	public void updateUserByName()
	
	{
		//Update user
		logger.info("Updating User-------->");
		Response response=UserEndPoints.updateUser(this.payload.getUsername(), payload);
		response.then().log().all();
		response.then().statusCode(200);
		//Assert.assertEquals(response.getStatusCode(), 200);	
		
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints.readUser(this.payload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);	
		logger.info("User Updated-------->");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("Deleting User-------->");
		Response response=UserEndPoints.deleteUser(this.payload.getUsername());
		response.then().log().all();
		response.then().statusCode(200);
		logger.info("User Deleted-------->");
	}


}
