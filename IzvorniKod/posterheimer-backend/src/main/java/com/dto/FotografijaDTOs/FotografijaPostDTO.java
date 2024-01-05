package com.dto.FotografijaDTOs;

import java.util.Base64;

public class FotografijaPostDTO {
    private Integer idFotografija;
    private String filePath;
    private Integer idKonferencija;

    public FotografijaPostDTO() {
    }
    public FotografijaPostDTO(Integer idFotografija, String filePath, Integer idKonferencija) {
        this.idFotografija = idFotografija;
        this.filePath = filePath;
        this.idKonferencija = idKonferencija;
    }

    public Integer getIdFotografija() {
        return idFotografija;
    }
    public void setIdFotografija(Integer idFotografija) {
        this.idFotografija = idFotografija;
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
