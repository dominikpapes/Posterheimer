package com.dto.FotografijaDTOs;

public class FotografijaGetDTO {
    private Integer idFotografija;
    private String filePath;

    public FotografijaGetDTO() {
    }
    public FotografijaGetDTO(Integer idFotografija, String filePath) {
        this.idFotografija = idFotografija;
        this.filePath = filePath;
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
}
