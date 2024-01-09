package com.mapper.KorisnikMappers;

import com.domain.Korisnik;
import com.dto.KorisnikDTOs.KorisnikGetDTO;

public class KorisnikGetMapper {
    public static KorisnikGetDTO toDTO(Korisnik korisnik) {
        return new KorisnikGetDTO(
                korisnik.getIdKorisnik(),
                korisnik.getEmail(),
                korisnik.getIme(),
                korisnik.getPrezime());
    }
}
