package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

public class UserEndPoints2 {

	//this is alternate way(if we not use properties file)
	static ResourceBundle getURL() throws IOException
	{
		 //FileInputStream fis = new FileInputStream("Routing");
		// ResourceBundle routes=new PropertyResourceBundle(fis);
		ResourceBundle routes=ResourceBundle.getBundle("Routing");  //load Routes.properties
		
		return routes;
	}

	public static Response createUser(User payload) throws IOException
	{

		String post_url=getURL().getString("post_url");
		Response response=given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)

				.when()
				.post(post_url);


		return response;

	}

	public static Response getUser(String userName) throws IOException
	{
		String get_url=getURL().getString("get_url");
		Response response=given()
				.accept(ContentType.JSON)
				.pathParam("username", userName)

				.when()
				.get(get_url);


		return response;

	}

	public static Response UpdateUser(String userName, User payload) throws IOException
	{
		String update_url=getURL().getString("update_url");
		Response response=given()
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)

				.when()
				.put(update_url);


		return response;

	}

	public static Response DeleteUser(String userName) throws IOException
	{
		String delete_url=getURL().getString("delete_url");
		Response response=given()
				.accept(ContentType.JSON)
				.pathParam("username", userName)

				.when()
				.delete(delete_url);


		return response;

	}

}
