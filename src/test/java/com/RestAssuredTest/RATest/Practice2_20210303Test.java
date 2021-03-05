package com.RestAssuredTest.RATest;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class Practice2_20210303Test {
	
	@BeforeClass
	public static void classSetup() {
		try {
			RestAssured.baseURI = "https://pokeapi.co";
			
			RestAssured.basePath = "/api/v2/";
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
		
	@Test
	public void getPokemonWithRandomIdThenVerifyReceivedId() {
		Random rand = new Random();
		Integer id = rand.nextInt(100);
		Response resp = RestAssured.given()
				.header("content-type", "application/json")
				.when()
				.get("/pokemon/{id}", id);
		assertEquals(resp.getBody().jsonPath().get("id").toString(),id.toString());
	}
}
