package com.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Konferencija {
    @Id
    private Integer idKonferencija;
    private String imeKonferencija;
    private String mjesto;
    private LocalDateTime datumVrijemePocetka;
    private LocalDateTime datumVrijemeZavrsetka;

    @OneToMany(mappedBy = "konferencija")
    private Set<RegistriraniKorisnik> users;

    public Konferencija() {
    }

    public Konferencija(Integer idKonferencija, String imeKonferencija, String mjesto, LocalDateTime datumVrijemePocetka, LocalDateTime datumVrijemeZavrsetka){
        Assert.hasText(imeKonferencija,"Ime konferencije must be set.");
        Assert.isTrue(idKonferencija instanceof Integer,"Id konferencije must be integer");
        this.idKonferencija=idKonferencija;
        this.imeKonferencija=imeKonferencija;
        this.mjesto=mjesto;
        this.datumVrijemePocetka=datumVrijemePocetka;
        this.datumVrijemeZavrsetka=datumVrijemeZavrsetka;
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

    public Set<RegistriraniKorisnik> getUsers() {
        return users;
    }

    public void setUser(RegistriraniKorisnik user) {
        this.users.add(user);
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
