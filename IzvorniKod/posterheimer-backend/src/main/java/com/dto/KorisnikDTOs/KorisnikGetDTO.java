package com.dto.KorisnikDTOs;

import com.dto.KonferencijaDTOs.KonferencijaGetDTO;

public class KorisnikGetDTO {
    private String email;
    public KorisnikGetDTO(){}
    public KorisnikGetDTO(String email){
        this.email=email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
