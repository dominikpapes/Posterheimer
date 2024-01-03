package com.dto.KonferencijaDTOs;

public class KonferencijaGetDTO {
    private Integer idKonferencija;
    private String imeKonferencija;

    // Constructors
    public KonferencijaGetDTO() {}

    public KonferencijaGetDTO(Integer idKonferencija, String imeKonferencija) {
        this.idKonferencija = idKonferencija;
        this.imeKonferencija = imeKonferencija;
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
}