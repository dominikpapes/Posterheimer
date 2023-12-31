package com.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKorisnik;
    private String email;
    private String ime;
    private String prezime;
    private boolean admin;
    private boolean visitor;
    private boolean voted;

    //ovdje pisemo relacije
    @ManyToOne
    @JoinColumn(name="id_konferencija")
    private Konferencija konferencija;

    @NotNull
    private String lozinka;

    //konstruktori
    public Korisnik() {
    }

    public Korisnik(String email, String lozinka, String ime, String prezime, boolean admin, boolean visitor){
        this.ime=ime;
        this.email=email;
        this.prezime=prezime;
        this.lozinka=lozinka;
        this.admin=admin;
        this.visitor = visitor;
        this.voted=false;
    }

    public Integer getKonferencijaId() {
        return konferencija.getIdKonferencija();
    }

    public String getIme() {
        return ime;
    }

    public String getEmail() {
        return email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isVisitor() {
        return visitor;
    }

    public void setVisitor(boolean visitor) {
        this.visitor = visitor;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public boolean isVoted() {
        return voted;
    }

    public Integer getIdKorisnik() { return idKorisnik; }

    public void setIdKorisnik(Integer idKorisnik) { this.idKorisnik = idKorisnik; }

    @Override
    public String toString() {
        return "Korisnik{" +
                ", email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                '}';
    }
}
