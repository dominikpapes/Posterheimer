package com.dao;

import com.domain.Konferencija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KonferencijaRepository
    extends JpaRepository<Konferencija, Integer>

    {
        @Query("SELECT idKonferencija, datumVrijemePocetka, datumVrijemeZavrsetka, genericPassword, genericUsername, imeKonferencija, mjesto FROM Konferencija")
        List<Konferencija> findAllKonferencije();

        @Query("SELECT COUNT(*) FROM Konferencija WHERE idKonferencija = :id")
        long countByIdKonferencije(@Param("id") Integer id);

        @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Konferencija WHERE idKonferencija = :id")
        boolean existsByIdKonferencija(@Param("id") Integer id);

        @Query("SELECT COUNT(*) FROM Konferencija WHERE imeKonferencija = :imeKonferencija")
        long countByImeKonferencija(@Param("imeKonferencija") String imeKonferencija);

        @Query("SELECT COUNT(*) > 0 FROM Konferencija WHERE imeKonferencija <> :imeKonferencija")
        boolean existsByImeKonferencijaNot(@Param("imeKonferencija") String konferencijaIme);


    }
