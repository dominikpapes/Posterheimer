package com.service;

import com.domain.Korisnik;
import com.domain.Poster;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface KorisnikService {

    //lista svig  korisnika
    List<Korisnik> listAll();

    //dobavi korisnika prema emailu
    Korisnik fetch(String email);

    //stvori novog korisnika u sustavu
    Korisnik createKorisnik(Korisnik korisnik);

    //izbrisi korisnika iz sustava
    Korisnik deleteKorisnik(String korisnickoIme, Integer idKonferencija);

    //Azuriraj korisnika
    Korisnik updateKorisnik(Korisnik korisnik, String newEmail);

    //pronadi kroisnika prema emailu
    Optional<Korisnik> findByEmail(String email);

    Optional<Korisnik> findByEmailAndIdKonferencija(String email, Integer idKonferencija);

    Korisnik fetchByEmailAndIdKonferenecija(String email, Integer idKonferencija);

}
