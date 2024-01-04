package com.rest.Security;

import java.util.List;

public class AuthenticationResponse {
    private String jwtToken;
    private String role;

    public AuthenticationResponse(String jwtToken, String role) {
        this.jwtToken = jwtToken;
        this.role = role;
    }
    // Getters and possibly setters
    public String getJwtToken() {
        return jwtToken;
    }

    public String getRole() {
        return role;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
