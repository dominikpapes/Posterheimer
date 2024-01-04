package com.dto.PokroviteljDTOs;

public class PokroviteljGetDTO {
    private String imePokrovitelja;
    private String promotivniMaterijal;

    public PokroviteljGetDTO() {}

    public PokroviteljGetDTO(String imePokrovitelja, String promotivniMaterijal) {
        this.imePokrovitelja = imePokrovitelja;
        this.promotivniMaterijal = promotivniMaterijal;
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
}
