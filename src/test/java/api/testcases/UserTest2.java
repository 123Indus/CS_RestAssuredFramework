package api.testcases;

import java.io.IOException;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UserTest2 {


	Faker faker;
	User userpayload;
	public static Logger logger;

	@BeforeClass
	public void generateTestData()
	{
		faker=new Faker();
		userpayload=new User();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());

		//obtain logger
		logger=LogManager.getLogger("CodeStudio_Rest_Framework");
	}

	//For create user
	@Test(priority=0)
	public void testCreateUser() throws IOException
	{
		Response response=UserEndPoints2.createUser(userpayload);

		//log response
		response.then().log().all();

		//validation
		Assert.assertEquals(200,response.getStatusCode());

		//log
		logger.info("Create User Executed");
	}

	//For get User
	@Test(priority=1)
	public void testGetUser() throws IOException
	{
		Response response=UserEndPoints2.getUser(this.userpayload.getUsername());

		//log response
		response.then().log().all();

		//validation
		Assert.assertEquals(200,response.getStatusCode());

		//log
		logger.info("Get User Executed");
	}

	//For Update User
	@Test(priority=2)
	public void testUpdateUser() throws IOException
	{
		userpayload.setLastName(faker.name().lastName());
		Response response=UserEndPoints2.UpdateUser(this.userpayload.getUsername(),userpayload);

		//log response
		response.then().log().all();

		//validation
		Assert.assertEquals(200,response.getStatusCode());

		//Read user data to check if last name is updated or not
		Response responsePostUpdate=UserEndPoints2.getUser(this.userpayload.getUsername());

		//log response
		responsePostUpdate.then().log().all();

		//log
		logger.info("Update User Executed");
	}

	//For Delete User

	@Test(priority=3)
	public void testDeleteUser() throws IOException
	{
		Response response=UserEndPoints2.DeleteUser(this.userpayload.getUsername());

		//log response
		response.then().log().all();

		//validation
		Assert.assertEquals(200,response.getStatusCode());

		//log
		logger.info("Delete user executed");
	}
}
