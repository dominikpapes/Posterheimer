package com.dto.PosterDTOs;

public class PosterVoteDTO {
    String imePostera;
    Integer idKonferencija;
    String email;
    public PosterVoteDTO(){}
    public PosterVoteDTO(String imePostera,String email,Integer idKonferencija){
        this.email=email;
        this.idKonferencija=idKonferencija;
        this.imePostera=imePostera;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public String getImePostera() {
        return imePostera;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdKonferencija(Integer idKonferencija) {
        this.idKonferencija = idKonferencija;
    }

    public void setImePostera(String imePostera) {
        this.imePostera = imePostera;
    }
}
