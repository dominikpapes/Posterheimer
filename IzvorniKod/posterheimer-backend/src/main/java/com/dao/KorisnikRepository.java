package com.dao;

import com.domain.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository
        extends JpaRepository<Korisnik, String> {
        //ovdje vidimo da su atributi od jednog imena email pa querye ne treba rucno pisat vec se samo generiraju
        Optional<Korisnik> findByEmail(String email);

        int countByEmail(String email);

        boolean existsByEmail(String email);

        boolean existsByEmailNot(String email);
}