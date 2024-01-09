package com.dto.PosterDTOs;

public class PosterGetWithVotesDTO {
    private String imePoster;
    private String imeAutor;
    private String prezimeAutor;
    private Integer brGlasova;

    public PosterGetWithVotesDTO() {}

    public PosterGetWithVotesDTO(String imePoster, String imeAutor, String prezimeAutor, Integer brGlasova) {
        this.imePoster = imePoster;
        this.imeAutor = imeAutor;
        this.prezimeAutor = prezimeAutor;
        this.brGlasova = brGlasova;
    }

    public String getImePoster() {
        return imePoster;
    }

    public void setImePoster(String imePoster) {
        this.imePoster = imePoster;
    }

    public String getImeAutor() {
        return imeAutor;
    }

    public void setImeAutor(String imeAutor) {
        this.imeAutor = imeAutor;
    }

    public String getPrezimeAutor() {
        return prezimeAutor;
    }

    public void setPrezimeAutor(String prezimeAutor) {
        this.prezimeAutor = prezimeAutor;
    }

    public Integer getBrGlasova() {
        return brGlasova;
    }

    public void setBrGlasova(Integer brGlasova) {
        this.brGlasova = brGlasova;
    }
}
