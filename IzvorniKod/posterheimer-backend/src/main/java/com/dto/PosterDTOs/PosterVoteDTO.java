package com.dto.PosterDTOs;

public class PosterVoteDTO {
    String imePoster;
    Integer idKonferencija;
    String email;
    public PosterVoteDTO(){}
    public PosterVoteDTO(String imePoster,String email,Integer idKonferencija){
        this.email=email;
        this.idKonferencija=idKonferencija;
        this.imePoster=imePoster;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public String getImePostera() {
        return imePoster;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public void setImePoster(String imePoster) {
        this.imePoster = imePoster;
    }
}
