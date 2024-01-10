package com.dto.KonferencijaDTOs;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class KonferencijaPostDTO {
    private static final String EMAIL_FORMAT = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PASSWORD_FORMAT = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
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

    public KonferencijaPostDTO(String imeKonferencija, String mjesto, String adresa,
                               String zipCode, String datumVrijemePocetka, String datumVrijemeZavrsetka,
                               String videoUrl, String genericPassword, String genericUsername,String adminUsername, String adminPassword) {
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

    public String getDatumVrijemePocetka() {
        return datumVrijemePocetka;
    }

    public String getDatumVrijemeZavrsetka() {
        return datumVrijemeZavrsetka;
    }

    public String getGenericPassword() {
        Assert.isTrue(genericPassword.matches(PASSWORD_FORMAT), "Password must be at least 8 characters long, " +
                "must contain at least one digit and " +
                "at least one uppercase letter.");
        return genericPassword;
    }

    public String getGenericUsername() {
        Assert.isTrue(genericUsername.matches(EMAIL_FORMAT), "Invalid email format: '" + genericUsername + "'");
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
        Assert.isTrue(adminPassword.matches(PASSWORD_FORMAT), "Password must be at least 8 characters long, " +
                "must contain at least one digit and " +
                "at least one uppercase letter.");
        return adminPassword;
    }

    public String getAdminUsername() {
        Assert.isTrue(adminUsername.matches(EMAIL_FORMAT), "Invalid email format: '" + adminUsername + "'");
        return adminUsername;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
}
