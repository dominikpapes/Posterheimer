package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Pokrovitelj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPokrovitelj;
    private String imePokrovitelja;
    @Lob
    private byte[] promotivniMaterijal;
    private String urlPromo;

    @ManyToMany
    @JoinColumn(name="id_konferencija")
    //@JsonIgnore
    private List<Konferencija> konferencije = new ArrayList<Konferencija>();

    public Pokrovitelj() {

    }

    public Pokrovitelj(Integer idPokrovitelj, String imePokrovitelja, byte[] promotivniMaterijal, String urlPromo) {
        this.idPokrovitelj = idPokrovitelj;
        this.imePokrovitelja = imePokrovitelja;
        this.promotivniMaterijal = promotivniMaterijal;
        this.urlPromo = urlPromo;
    }

    public void setImePokrovitelja(String imePokrovitelja) {
        this.imePokrovitelja = imePokrovitelja;
    }

    public void setPromotivniMaterijal(byte[] promotivniMaterijal) {
        this.promotivniMaterijal = promotivniMaterijal;
    }

    public String getImePokrovitelja() {
        return imePokrovitelja;
    }

    public byte[] getPromotivniMaterijal() {
        return promotivniMaterijal;
    }

    public String getUrlPromo() {
        return urlPromo;
    }

    public void setUrlPromo(String urlPromo) {
        this.urlPromo = urlPromo;
    }

    public List<Konferencija> getKonferencije() {
        return konferencije;
    }

    public void setKonferencije(Konferencija konferencija) {
        this.konferencije.add(konferencija);
    }

    public Integer getIdPokrovitelj() { return idPokrovitelj; }

    @Override
    public String toString() {
        return "Pokrovitelj{" +
                "idPokrovitelj=" + idPokrovitelj +
                ", imePokrovitelja='" + imePokrovitelja + '\'' +
                ", urlPromo='" + urlPromo + '\'' +
                ", konferencije=" + konferencije +
                '}';
    }
}
