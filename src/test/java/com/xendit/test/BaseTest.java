package com.xendit.test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public abstract class BaseTest {
    public static final String ERROR_DATA = "error.properties";
    protected RequestSpecification request;

    @BeforeClass
    @Parameters({"url"})
    public void setup(String url) {
        RestAssured.baseURI = url;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    @AfterClass
    public void tearDown(){
        request = null;
    }
}
