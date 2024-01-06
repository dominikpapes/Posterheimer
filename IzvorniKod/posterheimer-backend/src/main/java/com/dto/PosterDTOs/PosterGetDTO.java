package com.dto.PosterDTOs;

public class PosterGetDTO {
    private String imePoster;
    private String imeAutor;
    private String prezimeAutor;
    private byte[] filePath;//to be blob i dodaj br glasova
    public PosterGetDTO(){}
    public PosterGetDTO(String imePoster,String imeAutor,String prezimeAutor,byte[] filePath){
        this.filePath=filePath;
        this.imePoster=imePoster;
        this.imeAutor=imeAutor;
        this.prezimeAutor=prezimeAutor;
    }

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
