package com.dto.KonferencijaDTOs;

import java.time.LocalDateTime;

public class KonferencijaPostDTO {
    private Integer idKonferencija;
    private String imeKonferencija;
    private String mjesto;
    private String adresa;
    private String zipCode;
    private String datumVrijemePocetka;
    private String datumVrijemeZavrsetka;
    private String videoUrl;
    private String genericPassword;
    private String genericUsername;
    private String adminUsername;
    private String adminPassword;

    public KonferencijaPostDTO() {}

    public KonferencijaPostDTO(Integer idKonferencija, String imeKonferencija, String mjesto, String adresa,
                               String zipCode, String datumVrijemePocetka, String datumVrijemeZavrsetka,
                               String videoUrl, String genericPassword, String genericUsername,String adminUsername, String adminPassword) {
        this.idKonferencija = idKonferencija;
        this.imeKonferencija = imeKonferencija;
        this.mjesto = mjesto;
        this.adresa = adresa;
        this.zipCode = zipCode;
        this.datumVrijemePocetka = datumVrijemePocetka;
        this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
        this.videoUrl = videoUrl;
        this.genericPassword = genericPassword;
        this.genericUsername = genericUsername;
        this.adminPassword=adminPassword;
        this.adminUsername=adminUsername;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public String getDatumVrijemePocetka() {
        return datumVrijemePocetka;
    }

    public String getDatumVrijemeZavrsetka() {
        return datumVrijemeZavrsetka;
    }

    public String getGenericPassword() {
        return genericPassword;
    }

    public String getGenericUsername() {
        return genericUsername;
    }

    public String getImeKonferencija() {
        return imeKonferencija;
    }

    public String getMjesto() {
        return mjesto;
    }

    public String getAdresa() { return adresa; }

    public String getZipCode() { return zipCode; }

    public String getVideoUrl() { return videoUrl; }

    public void setGenericPassword(String genericPassword) {
        this.genericPassword = genericPassword;
    }

    public void setDatumVrijemePocetka(String datumVrijemePocetka) {
        this.datumVrijemePocetka = datumVrijemePocetka;
    }

    public void setDatumVrijemeZavrsetka(String datumVrijemeZavrsetka) {
        this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public void setGenericUsername(String genericUsername) {
        this.genericUsername = genericUsername;
    }

    public void setImeKonferencija(String imeKonferencija) {
        this.imeKonferencija = imeKonferencija;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public void setAdresa(String adresa) { this.adresa = adresa; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getAdminUsername() { return adminUsername; }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
}
