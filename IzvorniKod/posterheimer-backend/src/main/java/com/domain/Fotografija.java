package com.domain;

import jakarta.persistence.*;

@Entity
public class Fotografija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFotografija;
    @Lob
    private byte[] filePath;

    @ManyToOne
    @JoinColumn(name="id_konferencija")
    private Konferencija konferencija;

    public Fotografija() {
    }
    public Fotografija(Integer idFotografija, byte[] filePath) {
        this.idFotografija = idFotografija;
        this.filePath = filePath;
    }

    public Integer getIdFotografija() {
        return idFotografija;
    }

    public void setIdFotografija(Integer idFotografija) {
        this.idFotografija = idFotografija;
    }

    public byte[] getFilePath() {
        return filePath;
    }

    public void setFilePath(byte[] filePath) {
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
                ", konferencija=" + konferencija +
                '}';
    }
}
