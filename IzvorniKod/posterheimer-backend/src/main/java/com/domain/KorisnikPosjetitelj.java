package com.domain;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class KorisnikPosjetitelj {
    @Id
    @OneToOne
    private Integer idKonferencija;
    private String username;
    private String password;

}
