package com.rest.Security;

import java.util.List;

public class AuthenticationResponse {
    private String jwtToken;
    private String role;
    private String ime;
    private String prezime;

    public AuthenticationResponse(String jwtToken, String role,String ime,String prezime) {
        this.jwtToken = jwtToken;
        this.role = role;
        this.ime=ime;
        this.prezime=prezime;
    }

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

    public String getPrezime() {
        return prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
