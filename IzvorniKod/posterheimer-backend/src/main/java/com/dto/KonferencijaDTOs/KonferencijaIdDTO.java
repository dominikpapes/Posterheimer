package com.dto.KonferencijaDTOs;

public class KonferencijaIdDTO {
    private Integer idKonferencija;
    public KonferencijaIdDTO(){}
    public KonferencijaIdDTO(Integer idKonferencija){
        this.idKonferencija=idKonferencija;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(Integer id) {
        this.idKonferencija = id;
    }
}
