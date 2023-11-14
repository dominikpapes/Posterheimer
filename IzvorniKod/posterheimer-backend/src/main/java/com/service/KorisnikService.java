package com.service;

import com.domain.Korisnik;

import java.util.List;
import java.util.Optional;


public interface KorisnikService {

    //lista svig  korisnika
    List<Korisnik> listAll();

    //dobavi korisnika prema emailu
    Korisnik fetch(String email);

    //stvori novog korisnika u sustavu
    Korisnik createKorisnik(Korisnik registriraniKorisnik);

    //izbrisi korisnika iz sustava
    Korisnik deleteKorisnik(String korisnickoIme);

    //Azuriraj korisnika
    Korisnik updateKorisnik(Korisnik registriraniKorisnik, String newEmail);

    //pronadi kroisnika prema emailu
    Optional<Korisnik> findByEmail(String email);
}