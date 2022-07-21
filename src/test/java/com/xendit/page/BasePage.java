package com.xendit.page;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BasePage {

    protected RequestSpecification req;

    public BasePage(RequestSpecification request) {
        this.req = request;
    }

    public String getToken(String userName, String passWord){
        // Implement method for getToken
        String token = "validToken";
        return token;
    }

    public RequestSpecification setToken(String token){
        return req.header("Authorization", "Bearer " + token);
    }

    public Response patch(RequestSpecification request, String body, String url){
        return request.body(body).patch(url);
    }

    public Response post(RequestSpecification request, String body, String url){
        return request.body(body).post(url);
    }

    public Response get(RequestSpecification request, String body, String url) {
        return request.body(body).get(url);
    }

    public String getResponseMessage(Response response){
        String jsonString = response.asString();
        return JsonPath.from(jsonString).get("message");
    }
}
