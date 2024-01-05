package com.dto.FotografijaDTOs;

import jakarta.persistence.Lob;

public class FotografijaGetDTO {
    private Integer idFotografija;
    @Lob
    private byte[] filePath;

    public FotografijaGetDTO() {
    }
    public FotografijaGetDTO(Integer idFotografija, byte[] filePath) {
        this.idFotografija = idFotografija;
        this.filePath = filePath;
    }

    public Integer getIdFotografija() {
        return idFotografija;
    }
    public void setIdFotografija(Integer idFotografija) {
        this.idFotografija = idFotografija;
    }

    public byte[] getFilePath() {
        return filePath;
    }
    public void setFilePath(byte[] filePath) {
        this.filePath = filePath;
    }
}
