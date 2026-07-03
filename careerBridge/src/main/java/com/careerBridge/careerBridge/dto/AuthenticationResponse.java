package com.careerBridge.careerBridge.dto;

public class AuthenticationResponse {

    private String token;
    private String type;

    public AuthenticationResponse(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
