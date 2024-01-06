package com.dto.KorisnikDTOs;

import com.dto.KonferencijaDTOs.KonferencijaGetDTO;

public class KorisnikGetDTO {
    private String email;
    private String ime;
    private String prezime;
    public KorisnikGetDTO(){}

    public KorisnikGetDTO(String email, String ime, String prezime) {
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
