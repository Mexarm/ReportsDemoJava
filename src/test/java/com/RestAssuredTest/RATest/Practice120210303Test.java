package com.RestAssuredTest.RATest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

import org.json.JSONObject;

public class Practice120210303Test {

	static ExtentTest test;
	static ExtentReports report;
	
	@Rule public TestName name = new TestName();

	@BeforeClass
	public static void classSetup() {
		try {
			RestAssured.baseURI = "https://reqres.in";
			RestAssured.basePath = "/api";

			report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
			test = report.startTest("ExtentDemo");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Test
	public void postUserThenCreatedStatusCodeIsReceived() {
		JSONObject payload = new JSONObject();
		payload.put("name", "Armando H");
		payload.put("job", "Software Engineer");
		Response resp = RestAssured.given().header("Content-Type", "application/json").header("Accept", "*/*")
				.body(payload.toString()).post("/users");

		try {
			assertEquals(resp.statusCode(), 201);
			test.log(LogStatus.PASS, name.getMethodName());
		} catch (Exception ex) {
			test.log(LogStatus.FAIL, name.getMethodName());
		}
	}

	@Test
	public void patchUserThenOKStatusCodeIsReceived() {
		JSONObject payload = new JSONObject();
		payload.put("name", "R2D2");
		payload.put("job", "Android");
		Response resp = RestAssured.given().header("Content-Type", "application/json").header("Accept", "*/*")
				.body(payload.toString()).patch("/users/1");
		try {
			assertEquals(resp.statusCode(), 200);
			test.log(LogStatus.PASS, name.getMethodName());
		} catch (Exception ex) {
			test.log(LogStatus.FAIL, name.getMethodName());
		}
	}

	@Test
	public void deleteUserThenNoContentStatusCodeIsReceived() {

		Response resp = RestAssured.given().header("Content-Type", "application/json").header("Accept", "*/*")
				.delete("/users/1");
		try {
			assertEquals(resp.statusCode(), 204);
			test.log(LogStatus.PASS, name.getMethodName());
		} catch (Exception ex) {
			test.log(LogStatus.FAIL, name.getMethodName());
		}
	}

	@AfterClass
	public static void endTest() {
		report.endTest(test);
		report.flush();
	}
}
