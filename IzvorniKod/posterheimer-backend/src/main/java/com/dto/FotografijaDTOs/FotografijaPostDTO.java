package com.dto.FotografijaDTOs;

import java.util.Base64;

public class FotografijaPostDTO {
    private String filePath;
    private Integer idKonferencija;

    public FotografijaPostDTO() {
    }
    public FotografijaPostDTO(String filePath, Integer idKonferencija) {
        this.filePath = filePath;
        this.idKonferencija = idKonferencija;
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }
    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public byte[] decodeBase64String(String filePath) {
        return Base64.getDecoder().decode(filePath);
    }

}
