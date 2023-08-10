package com.qa.gorest.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class CircuitTest extends BaseTest{
	
	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void getCircuitTest() {
		restClient.get(CIRCUIT_ENDPOINT+"/2017/circuits.json", false, false)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
						
	}
			

}

