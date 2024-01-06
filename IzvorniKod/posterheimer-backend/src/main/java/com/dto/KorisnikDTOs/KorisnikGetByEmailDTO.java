package com.dto.KorisnikDTOs;

public class KorisnikGetByEmailDTO {
    private String email;
    private String ime;
    private String prezime;
    private boolean admin;
    private boolean visitor;

    public KorisnikGetByEmailDTO(){}
    public KorisnikGetByEmailDTO(String email, String ime, String prezime, boolean admin, boolean visitor){
        this.ime=ime;
        this.email=email;
        this.prezime=prezime;
        this.admin=admin;
        this.visitor = visitor;
    }

    public String getEmail() {
        return email;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setVisitor(boolean visitor) {
        this.visitor = visitor;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isVisitor() {
        return visitor;
    }
}
