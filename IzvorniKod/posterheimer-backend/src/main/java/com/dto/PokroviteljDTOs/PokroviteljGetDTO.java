package com.dto.PokroviteljDTOs;

public class PokroviteljGetDTO {
    private String imePokrovitelja;
    private byte[] promotivniMaterijal;
    private String urlPromo;

    public PokroviteljGetDTO() {}

    public PokroviteljGetDTO(String imePokrovitelja, byte[] promotivniMaterijal, String urlPromo) {
        this.imePokrovitelja = imePokrovitelja;
        this.promotivniMaterijal = promotivniMaterijal;
        this.urlPromo = urlPromo;
    }

    public String getImePokrovitelja() {
        return imePokrovitelja;
    }

    public void setImePokrovitelja(String imePokrovitelja) {
        this.imePokrovitelja = imePokrovitelja;
    }

    public byte[] getPromotivniMaterijal() {
        return promotivniMaterijal;
    }

    public void setPromotivniMaterijal(byte[] promotivniMaterijal) {
        this.promotivniMaterijal = promotivniMaterijal;
    }

    public String getUrlPromo() { return urlPromo; }

    public void setUrlPromo(String urlPromo) { this.urlPromo = urlPromo; }
}
