package com.dto.PosterDTOs;

public class PosterGetDTO {
    private Integer idPoster;
    private String imePoster;
    private String imeAutor;
    private String prezimeAutor;
    private byte[] filePath;
    public PosterGetDTO(){}
    public PosterGetDTO(Integer idPoster,String imePoster,String imeAutor,String prezimeAutor,byte[] filePath){
        this.idPoster=idPoster;
        this.imePoster=imePoster;
        this.imeAutor=imeAutor;
        this.prezimeAutor=prezimeAutor;
        this.filePath=filePath;
    }

    public Integer getIdPoster() { return idPoster; }

    public byte[] getFilePath() {
        return filePath;
    }

    public String getImeAutor() {
        return imeAutor;
    }

    public String getImePoster() {
        return imePoster;
    }

    public String getPrezimeAutor() {
        return prezimeAutor;
    }

    public void setFilePath(byte[] filePath) {
        this.filePath = filePath;
    }

    public void setImeAutor(String imeAutor) {
        this.imeAutor = imeAutor;
    }

    public void setImePoster(String imePoster) {
        this.imePoster = imePoster;
    }

    public void setPrezimeAutor(String prezimeAutor) {
        this.prezimeAutor = prezimeAutor;
    }
}
