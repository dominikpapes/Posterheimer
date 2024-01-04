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
    private String imePokrovitelja;
    private String promotivniMaterijal;

    @ManyToMany
    @JoinColumn(name="id_konferencija")
    @JsonIgnore
    private List<Konferencija> konferencije = new ArrayList<Konferencija>();

    public Pokrovitelj() {

    }

    public Pokrovitelj(String imePokrovitelja, String promotivniMaterijal) {
        this.imePokrovitelja = imePokrovitelja;
        this.promotivniMaterijal = promotivniMaterijal;
    }

    public void setImePokrovitelja(String imePokrovitelja) {
        this.imePokrovitelja = imePokrovitelja;
    }

    public void setPromotivniMaterijal(String promotivniMaterijal) {
        this.promotivniMaterijal = promotivniMaterijal;
    }

    public String getImePokrovitelja() {
        return imePokrovitelja;
    }

    public String getPromotivniMaterijal() {
        return promotivniMaterijal;
    }

    public List<Konferencija> getKonferencije() {
        return konferencije;
    }

    public void setKonferencije(Konferencija konferencija) {
        this.konferencije.add(konferencija);
    }

    @Override
    public String toString() {
        return "Pokrovitelj{" +
                "imePokrovitelja='" + imePokrovitelja + '\'' +
                ", promotivniMaterijal='" + promotivniMaterijal + '\'' +
                '}';
    }
}
