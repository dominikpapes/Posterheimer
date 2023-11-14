package com.dao;

import com.domain.Konferencija;
import com.domain.RegistriraniKorisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KonferencijaRepository
    extends JpaRepository<Konferencija, Integer>

    {

        @Query("SELECT COUNT(k) FROM Konferencija k WHERE k.idKonferencija = :id")
        long countByIdKonferencije(@Param("id") Integer id);


        @Query("SELECT CASE WHEN COUNT(k) > 0 THEN true ELSE false END FROM Konferencija k WHERE k.idKonferencija = :id")
        boolean existsByIdKonferencija(@Param("id") Integer id);


        @Query("SELECT COUNT(k) FROM Konferencija k WHERE k.imeKonferencija = :imeKonferencija")
        long countByImeKonferencija(@Param("imeKonferencija") String imeKonferencija);


        @Query("SELECT COUNT(k) > 0 FROM Konferencija k WHERE k.imeKonferencija <> :imeKonferencija")
        boolean existsByImeKonferencijaNot(@Param("imeKonferencija") String konferencijaIme);
    }
