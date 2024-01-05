package com.dto.KorisnikDTOs;

import com.domain.Konferencija;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class KorisnikPostDTO {
    private String email;
    private String ime;
    private String prezime;
    private boolean admin;
    private boolean visitor;
    private Integer idKonferencije;
    private String lozinka;
    public KorisnikPostDTO(){}
    public KorisnikPostDTO(String email,
     String ime,
     String prezime,
     boolean admin,
     boolean visitor,
     Integer idKonferencije,
     String lozinka){
        this.ime=ime;
        this.email=email;
        this.prezime=prezime;
        this.lozinka=lozinka;
        this.admin=admin;
        this.visitor = visitor;
        this.idKonferencije=idKonferencije;
    }

    public void setVisitor(boolean visitor) {
        this.visitor = visitor;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdKonferencije(Integer idKonferencije) {
        this.idKonferencije = idKonferencije;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public boolean isVisitor() {
        return visitor;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdKonferencije() {
        return idKonferencije;
    }

    public String getLozinka() {
        return lozinka;
    }
}
