package com.dao;

import com.domain.Korisnik;
import com.domain.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KorisnikRepository
        extends JpaRepository<Korisnik, String> {
        //ovdje vidimo da su atributi od jednog imena email pa querye ne treba rucno pisat vec se samo generiraju
        Optional<Korisnik> findByEmail(String email);

        int countByEmail(String email);

        boolean existsByEmail(String email);

        boolean existsByEmailNot(String email);

        @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
                "FROM Korisnik p JOIN p.konferencija k " +
                "WHERE p.email = :email AND k.idKonferencija = :idKonferencija")
        boolean existsByEmailAndIdKonferencija(@Param("email") String email,
                                                   @Param("idKonferencija") Integer idKonferencija);

        @Query("SELECT p FROM Korisnik p JOIN p.konferencija k " +
                "WHERE p.email = :email AND k.idKonferencija = :idKonferencija")
        Optional<Korisnik> findByEmailAndIdKonferencija(@Param("email") String email,
                                                        @Param("idKonferencija") Integer idKonferencija);
}