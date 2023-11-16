package com.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Poster {
    @Id
    private String imePoster;

    private String imeAutor;
    private String prezimeAutor;
    private String filePath;
    private String posterEmail;

    @OneToOne
    private Konferencija konferencija;
    public Poster(){
    }
    public Poster(String imePoster,String imeAutor,String prezimeAutor,String filePath,String mail){
        this.filePath=filePath;
        this.imePoster=imePoster;
        this.imeAutor=imeAutor;
        this.prezimeAutor=prezimeAutor;
        posterEmail=mail;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public String getFilePath() {
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

    public void setFilePath(String filePath) {
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
}
