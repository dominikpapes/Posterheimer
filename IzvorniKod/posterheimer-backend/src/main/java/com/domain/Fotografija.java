package com.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Fotografija {

    @Id
    private Integer idFotografija;
    private String filePath;

    @ManyToOne
    @JoinColumn(name="id_konferencija")
    private Konferencija konferencija;

    public Fotografija() {
    }
    public Fotografija(Integer idFotografija, String filePath) {
        this.idFotografija = idFotografija;
        this.filePath = filePath;
    }

    public Integer getIdFotografija() {
        return idFotografija;
    }

    public void setIdFotografija(Integer idFotografija) {
        this.idFotografija = idFotografija;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }

    @Override
    public String toString() {
        return "Fotografija{" +
                "idFotografija=" + idFotografija +
                ", filePath='" + filePath + '\'' +
                ", konferencija=" + konferencija +
                '}';
    }
}
