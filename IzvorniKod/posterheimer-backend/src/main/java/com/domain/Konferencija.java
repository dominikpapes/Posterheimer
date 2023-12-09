package com.domain;

import jakarta.persistence.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Konferencija {
    @Id
    @Column(name="id_konferencija")
    private Integer idKonferencija;
    @Column(name="ime_konferencija")
    private String imeKonferencija;
    @Column(name="mjesto")
    private String mjesto;
    @Column(name="datum_vrijeme_pocetka")
    private LocalDateTime datumVrijemePocetka;
    @Column(name="datum_vrijeme_zavrsetka")
    private LocalDateTime datumVrijemeZavrsetka;
    @Column(name="generic_username")
    private String genericUsername;
    @Column(name="generic_password")
    private String genericPassword;

    //Ovdje pisemo relacije između klasa
    @OneToMany(mappedBy = "konferencija")
    private Set<Korisnik> users;
    @OneToMany(mappedBy = "konferencija")
    private Set<Poster> posters;

    //konstruktori
    public Konferencija() {
    }

    public Konferencija(Integer idKonferencija, String imeKonferencija, String mjesto,
                        LocalDateTime datumVrijemePocetka, LocalDateTime datumVrijemeZavrsetka,
                        String genericPassword,String genericUsername){
        Assert.hasText(imeKonferencija,"Ime konferencije must be set.");
        Assert.isTrue(idKonferencija instanceof Integer,"Id konferencije must be integer");
        this.idKonferencija=idKonferencija;
        this.imeKonferencija=imeKonferencija;
        this.mjesto=mjesto;
        this.datumVrijemePocetka=datumVrijemePocetka;
        this.datumVrijemeZavrsetka=datumVrijemeZavrsetka;
        this.genericPassword=genericPassword;
        this.genericUsername=genericUsername;
    }

    public void setDatumVrijemePocetka(LocalDateTime datumVrijemePocetka) {
        this.datumVrijemePocetka = datumVrijemePocetka;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public void setDatumVrijemeZavrsetka(LocalDateTime datumVrijemeZavrsetka) {
        this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
    }

    public void setGenericUsername(String genericUsername) {
        this.genericUsername = genericUsername;
    }

    public String getGenericUsername() {
        return genericUsername;
    }

    public void setGenericPassword(String genericPassword) {
        this.genericPassword = genericPassword;
    }

    public String getGenericPassword() {
        return genericPassword;
    }

    public void setImeKonferencija(String imeKonferencija) {
        this.imeKonferencija = imeKonferencija;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public LocalDateTime getDatumVrijemeZavrsetka() {
        return datumVrijemeZavrsetka;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public String getImeKonferencija() {
        return imeKonferencija;
    }

    public LocalDateTime getDatumVrijemePocetka() {
        return datumVrijemePocetka;
    }

    public String getMjesto() {
        return mjesto;
    }

    public Set<Korisnik> getUsers() {
        return users;
    }

    public void setUser(Korisnik user) {
        this.users.add(user);
    }

    public void setPoster(Poster poster) {
        this.posters.add(poster);
    }

    public Set<Poster> getPosters() {
        return posters;
    }

    @Override
    public String toString() {
        return "Konferencija{" +
                "imeKonferencija='" + imeKonferencija + '\'' +
                ", mjesto='" + mjesto + '\'' +
                ", datumVrijemePocetka=" + datumVrijemePocetka +
                ", datumVrijemeZavrsetka=" + datumVrijemeZavrsetka +
                '}';
    }
}
