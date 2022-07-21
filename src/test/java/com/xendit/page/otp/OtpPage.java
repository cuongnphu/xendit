package com.xendit.page.otp;

import com.xendit.page.BasePage;
import io.restassured.specification.RequestSpecification;

public class OtpPage extends BasePage {

    public OtpPage(RequestSpecification request) {
        super(request);
    }

    public String getOtpBody(String description, String callBackUrl, String amount){
        return "{ \"description\":\"" + description + "\", \"callback_url\":\"" + callBackUrl + "\", \"amount\":" + amount + "}";
    }
}
