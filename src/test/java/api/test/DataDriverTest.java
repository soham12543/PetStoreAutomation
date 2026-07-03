package api.test;

import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDriverTest {
	
	@Test(dataProvider="data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userid, String username, String fname, String lname, String password, String phone)
	{
		
		User payload=new User();
		payload.setId((int) Double.parseDouble(userid));
		payload.setUsername(username);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(lname);
		payload.setPassword(password);
		payload.setPhone(phone);
		payload.setUserStatus(0);
		
		Response response=UserEndPoints.createUser(payload);
		response.then().log().all();
		response.then().statusCode(200);
	}
	
	@Test(dataProvider="usernames",dataProviderClass=DataProviders.class)
	public void deleteUsers(String username)
	{
	
		Response response=UserEndPoints.deleteUser(username);
		response.then().log().all();
		response.then().statusCode(200);
		
	}

}
