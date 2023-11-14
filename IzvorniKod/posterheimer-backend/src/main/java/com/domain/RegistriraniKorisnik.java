package com.domain;

import com.dao.KonferencijaRepository;
import com.rest.KonferencijaController;
import com.service.KonferencijeService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class RegistriraniKorisnik {
    @Id
    private String email;
    private String ime;
    private String prezime;

    @ManyToOne
    @JoinColumn(name = "konferencijaId")
    private Konferencija konferencija;

    @NotNull
    private String lozinka;

    public RegistriraniKorisnik() {
    }

    public RegistriraniKorisnik(String email,String lozinka, String ime, String prezime, Integer konferencijaId){
        this.ime=ime;
        this.email=email;
        this.prezime=prezime;
        this.lozinka=lozinka;
    }

    public Integer getKonferencijaId() {
        return konferencija.getIdKonferencija();
    }

    public String getIme() {
        return ime;
    }

    public String getEmail() {
        return email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }

    @Override
    public String toString() {
        return "RegistriraniKorisnik{" +
                ", email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                '}';
    }
}
