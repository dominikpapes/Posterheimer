package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Pokrovitelj {
    @Id
    private String imePokrovitelja;
    private String promotivniMaterijal;

    @ManyToMany(mappedBy = "pokrovitelji")
    @JsonIgnore
    private Set<Konferencija> konferencije;

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

    @Override
    public String toString() {
        return "Pokrovitelj{" +
                "imePokrovitelja='" + imePokrovitelja + '\'' +
                ", promotivniMaterijal='" + promotivniMaterijal + '\'' +
                '}';
    }
}
