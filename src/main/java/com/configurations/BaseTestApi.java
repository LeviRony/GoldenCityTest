package com.configurations;

import com.microsoft.playwright.*;
import org.junit.*;

import static com.configurations.BaseUri.*;

public class BaseTestApi {

    protected static Playwright playwright;
    protected static APIRequestContext request;

    @BeforeClass
    public static void setupRequest() {
        playwright = Playwright.create();
        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL(goldenCityUrl()) // sample API
        );
    }

    @AfterClass
    public static void teardown() {
        if (request != null) request.dispose();
        if (playwright != null) playwright.close();
    }
}