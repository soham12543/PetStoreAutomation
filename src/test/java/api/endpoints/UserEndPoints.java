package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*; //for static packages we need to use static keyword
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created to perform CRUD operations to the user API
public class UserEndPoints
{
	//method created for getting URLs from properties file
	
	//We can also use ConfigManager.getProperty("create_user")
	static ResourceBundle getUrl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("Routes"); //This will automatically search the properties in the project directory
		return routes;
	}
	
	
	@Test
	public static Response createUser(User payload)
	{
		String create_user=getUrl().getString("create_user");
		Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)  
		.when()
		    .post(create_user);
		
		return response;
	}
	
	@Test
	public static Response readUser(String username)
	{
		String get_user=getUrl().getString("get_user");
		Response response=given()
		    .pathParam("username",username)
		.when()
		  .get(get_user);
		return response;
	}
	
	@Test
	public static Response updateUser(String username, User payload)
	{
		String update_user=getUrl().getString("update_user");
		Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)  
		   .pathParam("username", username)
		.when()
		    .put(update_user);
		return response;
	}
	
	@Test
	public static Response deleteUser(String username)
	{
		String delete_user=getUrl().getString("delete_user");
		Response response=given()
		    .pathParam("username",username)
		.when()
		  .delete(delete_user);
		return response;
	}
	

}
