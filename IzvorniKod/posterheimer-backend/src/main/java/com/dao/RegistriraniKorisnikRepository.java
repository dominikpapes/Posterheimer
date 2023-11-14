package com.dao;

import com.domain.RegistriraniKorisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RegistriraniKorisnikRepository
        extends JpaRepository<RegistriraniKorisnik, String> {
        Optional<RegistriraniKorisnik> findByEmail(String email);

        int countByEmail(String email);

        boolean existsByEmail(String email);

        boolean existsByEmailNot(String email);
}