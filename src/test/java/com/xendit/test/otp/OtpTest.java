package com.xendit.test.otp;

import com.xendit.helper.PropertiesHelper;
import com.xendit.helper.Utils;
import com.xendit.page.otp.OtpPage;
import com.xendit.test.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OtpTest extends BaseTest {

    private String baseUrl = "/qr_codes/:";
    private OtpPage otpPage;

    @Test
    public void updateQrCodeSuccess() {
        otpPage = new OtpPage(request);

        // Make sure that we will have method for get valid QRCode & token
        String url = Utils.getUrl(baseUrl, "validQRCode");
        String token = otpPage.getToken("user","pass");
        String body = otpPage.getOtpBody("description", "url", String.valueOf(2000));

        // Setup token
        RequestSpecification req = otpPage.setToken(token);
        Response response = otpPage.patch(req, body, url);

        // Verify response
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(otpPage.getResponseMessage(response), "Successful");
    }

    @Test
    public void wrongQrCodeBlank(){
        otpPage = new OtpPage(request);

        // Make sure that we will have method for get valid QRCode & token
        String url = Utils.getUrl(baseUrl, "");
        String token = otpPage.getToken("user","pass");
        String body = otpPage.getOtpBody("description", "url", String.valueOf(2000));

        // Setup token
        RequestSpecification req = otpPage.setToken(token);
        Response response = otpPage.patch(req, body, url);

        // Verify response
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(otpPage.getResponseMessage(response), PropertiesHelper.getConfigValue(ERROR_DATA, "API_VALIDATION_ERROR"));
    }

    @Test
    public void wrongAuthentication(){
        otpPage = new OtpPage(request);

        // Make sure that we will have method for get valid QRCode & token
        String url = Utils.getUrl(baseUrl, "validQrCode");
        String body = otpPage.getOtpBody("description", "url", String.valueOf(2000));

        // Setup token
        RequestSpecification req = otpPage.setToken("abc");
        Response response = otpPage.patch(req, body, url);

        // Verify response
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void wrongBodyAmount(){
        otpPage = new OtpPage(request);

        // Make sure that we will have method for get valid QRCode & token
        String url = Utils.getUrl(baseUrl, "validQrCode");
        String token = otpPage.getToken("user","pass");
        String body = otpPage.getOtpBody("description", "url", String.valueOf(500));

        // Setup token
        RequestSpecification req = otpPage.setToken(token);
        Response response = otpPage.patch(req, body, url);

        // Verify response
        Assert.assertEquals(response.getStatusCode(), 402);
        Assert.assertEquals(otpPage.getResponseMessage(response), PropertiesHelper.getConfigValue(ERROR_DATA, "INVALID_JSON_FORMAT"));
    }

    @Test
    public void wrongQrCodeNotFound(){
        otpPage = new OtpPage(request);

        // Make sure that we will have method for get token
        String url = Utils.getUrl(baseUrl, "abc");
        String token = otpPage.getToken("user","pass");
        String body = otpPage.getOtpBody("description", "url", String.valueOf(2000));

        // Setup token
        RequestSpecification req = otpPage.setToken(token);
        Response response = otpPage.patch(req, body, url);

        // Verify response
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(otpPage.getResponseMessage(response), PropertiesHelper.getConfigValue(ERROR_DATA, "QR_CODE_NOT_FOUND_ERROR"));
    }

    @Test
    public void wrongQrCodeInUse(){
        otpPage = new OtpPage(request);

        // Make sure that we will have method for get valid token
        String url = Utils.getUrl(baseUrl, "usedQrCode");
        String token = otpPage.getToken("user","pass");
        String body = otpPage.getOtpBody("description", "url", String.valueOf(2000));

        // Setup token
        RequestSpecification req = otpPage.setToken(token);
        Response response = otpPage.patch(req, body, url);

        // Verify response
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(otpPage.getResponseMessage(response), PropertiesHelper.getConfigValue(ERROR_DATA, "QR_CODE_CODE_IN_USE"));
    }
}
