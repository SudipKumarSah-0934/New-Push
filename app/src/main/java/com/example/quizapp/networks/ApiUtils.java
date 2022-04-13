package com.example.quizapp.networks;
public class ApiUtils {
    public static APIService getApiService() {
        return RetroClient.getClient().create(APIService.class);
    }
}