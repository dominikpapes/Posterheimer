package com.dto.PokroviteljDTOs;

public class PokroviteljPostDTO {
    private String imePokrovitelja;
    private String promotivniMaterijal;
    private Integer idKonferencija;

    public PokroviteljPostDTO() {
    }
    public PokroviteljPostDTO(String imePokrovitelja, String promotivniMaterijal, Integer idKonferencija) {
        this.imePokrovitelja = imePokrovitelja;
        this.promotivniMaterijal = promotivniMaterijal;
        this.idKonferencija = idKonferencija;
    }

    public String getImePokrovitelja() {
        return imePokrovitelja;
    }

    public void setImePokrovitelja(String imePokrovitelja) {
        this.imePokrovitelja = imePokrovitelja;
    }

    public String getPromotivniMaterijal() {
        return promotivniMaterijal;
    }

    public void setPromotivniMaterijal(String promotivniMaterijal) {
        this.promotivniMaterijal = promotivniMaterijal;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }
}

