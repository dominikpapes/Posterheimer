package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Konferencija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_konferencija")
    private Integer idKonferencija;
    @Column(name="ime_konferencija")
    private String imeKonferencija;
    @Column(name="mjesto")
    private String mjesto;
    private String adresa;
    private String zipCode;
    @Column(name="datum_vrijeme_pocetka")
    private LocalDateTime datumVrijemePocetka;
    @Column(name="datum_vrijeme_zavrsetka")
    private LocalDateTime datumVrijemeZavrsetka;
    private String videoUrl;
    @Column(name="generic_username")
    private String genericUsername;
    @Column(name="generic_password")
    private String genericPassword;
    private boolean votingReminderSent;

    //Ovdje pisemo relacije između klasa
    @OneToMany(mappedBy = "konferencija", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Korisnik> users;
    @OneToMany(mappedBy = "konferencija", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Poster> posters;
    @ManyToMany
    @JoinTable(
            name = "pokrovitelj_konferencije",
            joinColumns = @JoinColumn(name = "id_konferencija"),
            inverseJoinColumns = @JoinColumn(name = "id_pokrovitelj")
    )
    @JsonIgnore
    private List<Pokrovitelj> pokrovitelji = new ArrayList<>();

    @OneToMany(mappedBy = "konferencija", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Fotografija> fotografije;

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
        this.votingReminderSent=false;
    }

    public void setDatumVrijemePocetka(LocalDateTime datumVrijemePocetka) {
        this.datumVrijemePocetka = datumVrijemePocetka;
    }

    public void setDatumVrijemeZavrsetka(LocalDateTime datumVrijemeZavrsetka) {
        this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
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

    public Set<Poster> getPosters() { return posters; }
    public List<Pokrovitelj> getPokrovitelji() { return pokrovitelji; }

    public Set<Fotografija> getFotografije() { return fotografije; }

    public void setUser(Korisnik user) {
        this.users.add(user);
    }

    public void setPoster(Poster poster) {
        this.posters.add(poster);
    }

    public void setPokrovitelj(Pokrovitelj pokrovitelj) { this.pokrovitelji.add(pokrovitelj); }

    public void setFotografije(Fotografija fotografija) {
        this.fotografije.add(fotografija);
    }

    public void setVotingReminderSent(boolean votingReminderSent) {
        this.votingReminderSent = votingReminderSent;
    }

    public boolean isVotingReminderSent() {
        return votingReminderSent;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) { this.adresa = adresa; }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
