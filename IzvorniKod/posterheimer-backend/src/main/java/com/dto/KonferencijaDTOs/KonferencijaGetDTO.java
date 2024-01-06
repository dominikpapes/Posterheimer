package com.dto.KonferencijaDTOs;

import java.time.LocalDateTime;

public class KonferencijaGetDTO {
    private Integer idKonferencija;
    private String imeKonferencija;
    private String mjesto;
    private String adresa;
    private String zipCode;
    private LocalDateTime datumVrijemePocetka;
    private LocalDateTime datumVrijemeZavrsetka;
    private String videoUrl;


    // Constructors
    public KonferencijaGetDTO() {}

    public KonferencijaGetDTO(Integer idKonferencija, String imeKonferencija, String mjesto,
                              String adresa, String zipCode, LocalDateTime datumVrijemePocetka,
                              LocalDateTime datumVrijemeZavrsetka, String videoUrl) {
        this.idKonferencija = idKonferencija;
        this.imeKonferencija = imeKonferencija;
        this.mjesto = mjesto;
        this.adresa = adresa;
        this.zipCode = zipCode;
        this.datumVrijemePocetka = datumVrijemePocetka;
        this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
        this.videoUrl = videoUrl;
    }

    // Getters and Setters
    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public String getImeKonferencija() {
        return imeKonferencija;
    }

    public void setImeKonferencija(String imeKonferencija) {
        this.imeKonferencija = imeKonferencija;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDateTime getDatumVrijemePocetka() {
        return datumVrijemePocetka;
    }

    public void setDatumVrijemePocetka(LocalDateTime datumVrijemePocetka) {
        this.datumVrijemePocetka = datumVrijemePocetka;
    }

    public LocalDateTime getDatumVrijemeZavrsetka() {
        return datumVrijemeZavrsetka;
    }

    public void setDatumVrijemeZavrsetka(LocalDateTime datumVrijemeZavrsetka) {
        this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}