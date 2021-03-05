package com.RestAssuredTest.RATest;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.RestAssuredTest.RATest.reports.EReport;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

import org.json.JSONObject;

public class FwReportingTest {

	@Rule
	public EReport name = new EReport(System.getProperty("user.dir") + "\\target\\ExtentReportResults.html");

	@BeforeClass
	public static void classSetup() {
		try {
			RestAssured.baseURI = "https://reqres.in";
			RestAssured.basePath = "/api";

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Test
	public void whenPostUser_ThenCreatedStatusCodeIsReceived() {
		JSONObject payload = new JSONObject();
		payload.put("name", "Armando H");
		payload.put("job", "Software Engineer");
		Response resp = RestAssured.given().header("Content-Type", "application/json").header("Accept", "*/*")
				.body(payload.toString()).post("/users");
    	assertEquals(resp.statusCode(), 2011);
}

	@Test
	public void whenPatchUser_ThenOKStatusCodeIsReceived() {
		JSONObject payload = new JSONObject();
		payload.put("name", "R2D2");
		payload.put("job", "Android");
		Response resp = RestAssured.given().header("Content-Type", "application/json").header("Accept", "*/*")
				.body(payload.toString()).patch("/users/1");
		assertEquals(resp.statusCode(), 200);
	}

	@Test
	public void whenDeleteUser_ThenNoContentStatusCodeIsReceived() {
		Response resp = RestAssured.given().header("Content-Type", "application/json").header("Accept", "*/*")
				.delete("/users/1");
		assertEquals(resp.statusCode(), 204);
	}
}
