package com.mapper.KorisnikMappers;

import com.domain.Korisnik;
import com.dto.KorisnikDTOs.KorisnikGetByEmailDTO;
import com.dto.KorisnikDTOs.KorisnikGetDTO;

public class KorisnikGetByEmailMapper {
    public static KorisnikGetByEmailDTO toDTO(Korisnik korisnik) {
        return new KorisnikGetByEmailDTO(
                korisnik.getEmail(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.isAdmin(),
                korisnik.isVisitor()
        );
    }
}
