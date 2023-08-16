package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class GetUserTest extends BaseTest{
	
	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, baseURI);
		
	
	}
	//code smell: sonarQube
	@Test(enabled = true, priority = 1)
	public void getAllUsersTest() {
		restClient.get(GOREST_ENDPOINT, true,  true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
						
	}
	
	///public/v2/users/4328205/?name&staus
	@Test(priority = 2, enabled = false)
	public void getUserTest() {
		restClient.get(GOREST_ENDPOINT+"/"+4328205, true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
							.and().body("id", equalTo(4328205));
						
	}
	
	//url?name&staus
	@Test(priority = 2)
	public void getUserWithQueryParamsTest() {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("name", "vindhya");
		queryParams.put("status", "active");

		restClient.get(GOREST_ENDPOINT, queryParams, null,true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
							
						
	}
	
	
}





