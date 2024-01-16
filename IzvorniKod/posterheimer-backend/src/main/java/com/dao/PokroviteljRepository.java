package com.dao;

import com.domain.Pokrovitelj;
import com.domain.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PokroviteljRepository
        extends JpaRepository<Pokrovitelj, String>

{

    @Query("SELECT p FROM Pokrovitelj p WHERE p.imePokrovitelja = :imePokrovitelja")
    Optional<Pokrovitelj> findByImePokrovitelj(@Param("imePokrovitelja") String imePokrovitelja);

    @Query("SELECT p FROM Pokrovitelj p WHERE p.idPokrovitelj = :idPokrovitelj")
    Optional<Pokrovitelj> findByIdPokrovitelj(@Param("idPokrovitelj") Integer idPokrovitelj);

    @Query("SELECT COUNT(p) FROM Pokrovitelj p WHERE p.imePokrovitelja = :imePokrovitelja")
    int countByImePokrovitelja(@Param("imePokrovitelja") String imePokrovitelja);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pokrovitelj p WHERE p.imePokrovitelja = :imePokrovitelja")
    boolean existsByImePokrovitelja(@Param("imePokrovitelja") String imePokrovitelja);

}