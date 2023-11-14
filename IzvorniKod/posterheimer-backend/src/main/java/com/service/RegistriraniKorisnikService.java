package com.service;

import com.domain.RegistriraniKorisnik;
import java.util.List;
import java.util.Optional;


public interface RegistriraniKorisnikService {

    //lista svig registriranih korisnika
    List<RegistriraniKorisnik> listAll();

    //dobavi registriranog korisnika prema emailu
    RegistriraniKorisnik fetch(String email);

    //stvori novog reg. korisnika u sustavu
    RegistriraniKorisnik createRegistriraniKorisnik(RegistriraniKorisnik registriraniKorisnik);

    //izbrisi registriranog korisnika iz sustava
    RegistriraniKorisnik deleteRegistriraniKorisnik(String korisnickoIme);

    //Azuriraj registriranog korisnika
    RegistriraniKorisnik updateRegistriraniKorisnik(RegistriraniKorisnik registriraniKorisnik, String newEmail);

    //pronadi reg. kroisnika prema emailu
    Optional<RegistriraniKorisnik> findByEmail(String email);
}
