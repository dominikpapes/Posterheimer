package com.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pokrovitelj {
    //todo
    @Id
    private String imePokrovitelja;
    private String promotivniMaterijal;

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
    public String   toString() {
        return "Pokrovitelj{" +
                "imePokrovitelja='" + imePokrovitelja + '\'' +
                ", promotivniMaterijal='" + promotivniMaterijal + '\'' +
                '}';
    }
}
