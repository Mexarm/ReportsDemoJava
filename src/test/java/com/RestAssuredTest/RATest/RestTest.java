package com.RestAssuredTest.RATest;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;


public class RestTest {

	@BeforeClass
	public static void classSetup() {
		try {
			RestAssured.baseURI = "http://reqres.in";
			RestAssured.basePath = "/api";
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Test
	public void getUsers() {
		Response resp = RestAssured.given()
				.header("content-type", "application/json")
				.when()
				.param("page", 2)
				.get("/users");
//		resp.body().prettyPrint();
		assertEquals(resp.getBody().jsonPath().get("page").toString(), "2");
	}
}
