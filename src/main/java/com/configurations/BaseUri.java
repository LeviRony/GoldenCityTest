package com.configurations;
public class BaseUri extends AutomationEnvProperties{


    static IllegalArgumentException URL_Error_Msg = new IllegalArgumentException("No valid URL found");

    public static String goldenCityUrl() {
        if ("STG".equalsIgnoreCase(ENV_TYPE)) {
            return "http://localhost:3000/";
        } else if ("PROD".equalsIgnoreCase(ENV_TYPE)) {
            return "https://goldencity.com";
        } else {
            throw URL_Error_Msg;
        }
    }
}
