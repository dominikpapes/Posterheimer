package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Poster {
    @Id
    private String imePoster;

    private String imeAutor;
    private String prezimeAutor;
    @Lob
    private byte[] filePath;
    private String posterEmail;
    private Integer brGlasova;

    @OneToOne
    @JoinColumn(name="id_konferencija")
    //@JsonIgnore
    private Konferencija konferencija;
    public Poster(){
    }
    public Poster(String imePoster,String imeAutor,String prezimeAutor,byte[] filePath,String mail, Integer brGlasova){
        this.filePath=filePath;
        this.imePoster=imePoster;
        this.imeAutor=imeAutor;
        this.prezimeAutor=prezimeAutor;
        posterEmail=mail;
        this.brGlasova=brGlasova;
    }
    public void vote(){
        this.brGlasova++;
    }
    public Konferencija getKonferencija() {
        return konferencija;
    }

    public byte[] getFilePath() {
        return filePath;
    }

    public String getPosterEmail() {
        return posterEmail;
    }

    public String getImeAutor() {
        return imeAutor;
    }

    public String getImePoster() {
        return imePoster;
    }

    public String getPrezimeAutor() {
        return prezimeAutor;
    }

    public void setFilePath(byte[] filePath) {
        this.filePath = filePath;
    }

    public void setImeAutor(String imeAutor) {
        this.imeAutor = imeAutor;
    }

    public void setImePoster(String imePoster) {
        this.imePoster = imePoster;
    }

    public void setPosterEmail(String posterMail) {
        this.posterEmail = posterMail;
    }

    public void setPrezimeAutor(String prezimeAutor) {
        this.prezimeAutor = prezimeAutor;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }

    public Integer getBrGlasova() {
        return brGlasova;
    }
    public void setBrGlasova(Integer brGlasova) {
        this.brGlasova = brGlasova;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "imePoster='" + imePoster + '\'' +
                ", imeAutor='" + imeAutor + '\'' +
                ", prezimeAutor='" + prezimeAutor + '\'' +
                ", posterEmail='" + posterEmail + '\'' +
                ", brGlasova=" + brGlasova +
                ", konferencija=" + konferencija +
                '}';
    }
}
