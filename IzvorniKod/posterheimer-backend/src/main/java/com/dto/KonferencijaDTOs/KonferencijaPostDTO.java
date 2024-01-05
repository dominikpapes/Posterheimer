package com.dto.KonferencijaDTOs;

import java.time.LocalDateTime;

public class KonferencijaPostDTO {
    private Integer idKonferencija;
    private String imeKonferencija;
    private String mjesto; // Fixed: replaced comma with semicolon
    private LocalDateTime datumVrijemePocetka;
    private LocalDateTime datumVrijemeZavrsetka;
    private String genericPassword;
    private String genericUsername;

    public KonferencijaPostDTO() {}

    public KonferencijaPostDTO(Integer idKonferencija, String imeKonferencija, String mjesto,
                               LocalDateTime datumVrijemePocetka, LocalDateTime datumVrijemeZavrsetka,
                               String genericPassword, String genericUsername) {
        this.idKonferencija = idKonferencija;
        this.imeKonferencija = imeKonferencija;
        this.mjesto = mjesto;
        this.datumVrijemePocetka = datumVrijemePocetka;
        this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
        this.genericPassword = genericPassword;
        this.genericUsername = genericUsername;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public LocalDateTime getDatumVrijemePocetka() {
        return datumVrijemePocetka;
    }

    public LocalDateTime getDatumVrijemeZavrsetka() {
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

    public void setGenericPassword(String genericPassword) {
        this.genericPassword = genericPassword;
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

    public void setImeKonferencija(String imeKonferencija) {
        this.imeKonferencija = imeKonferencija;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }
}
