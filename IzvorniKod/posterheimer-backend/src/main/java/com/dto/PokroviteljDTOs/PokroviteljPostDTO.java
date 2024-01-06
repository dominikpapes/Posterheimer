package com.dto.PokroviteljDTOs;

import java.util.Base64;

public class PokroviteljPostDTO {
    private String imePokrovitelja;
    private String promotivniMaterijal;
    private String urlPromo;
    private Integer idKonferencija;

    public PokroviteljPostDTO() {
    }
    public PokroviteljPostDTO(String imePokrovitelja, String promotivniMaterijal, String urlPromo, Integer idKonferencija) {
        this.imePokrovitelja = imePokrovitelja;
        this.promotivniMaterijal = promotivniMaterijal;
        this.urlPromo = urlPromo;
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

    public String getUrlPromo() {
        return urlPromo;
    }

    public void setUrlPromo(String urlPromo) {
        this.urlPromo = urlPromo;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public byte[] decodeBase64String(String promotivniMaterijal) {
        return Base64.getDecoder().decode(promotivniMaterijal);
    }
}

