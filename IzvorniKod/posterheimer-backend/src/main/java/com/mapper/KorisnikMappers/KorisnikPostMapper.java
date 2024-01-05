package com.mapper.KorisnikMappers;

import com.domain.Konferencija;
import com.domain.Korisnik;
import com.dto.KonferencijaDTOs.KonferencijaPostDTO;
import com.dto.KorisnikDTOs.KorisnikPostDTO;

public class KorisnikPostMapper {
    public static Korisnik toEntity(KorisnikPostDTO dto) {

        Korisnik korisnik=new Korisnik();
        korisnik.setLozinka(dto.getLozinka());
        korisnik.setAdmin(dto.isAdmin());
        korisnik.setEmail(dto.getEmail());
        korisnik.setIme(dto.getIme());
        korisnik.setPrezime(dto.getPrezime());
        korisnik.setVisitor(dto.isVisitor());
        return korisnik;
    }
}
