package com.dao;

import com.domain.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PosterRepository extends JpaRepository<Poster,String> {
    @Query("SELECT p FROM Poster p WHERE p.imePoster = :imePoster")
    Optional<Poster> findByImePoster(@Param("imePoster") String imePoster);

    @Query("SELECT p FROM Poster p WHERE p.idPoster = :idPoster")
    Optional<Poster> findByIdPoster(@Param("idPoster") Integer idPoster);

    @Query("SELECT COUNT(p) FROM Poster p WHERE p.imePoster = :imePoster")
    int countByImePoster(@Param("imePoster") String imePoster);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Poster p WHERE p.imePoster = :imePoster")
    boolean existsByImePoster(@Param("imePoster") String imePoster);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Poster p WHERE p.posterEmail = :email")
    boolean existsByPosterEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(k) > 0 THEN true ELSE false END FROM Poster k WHERE k.imeAutor = :imeAutor")
    boolean existsByImeAutor(String imeAutor);

    @Query("SELECT CASE WHEN COUNT(k) > 0 THEN true ELSE false END FROM Poster k WHERE k.prezimeAutor = :prezimeAutor")
    boolean existsByPrezimeAutor(String prezimeAutor);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Poster p JOIN p.konferencija k " +
            "WHERE p.imePoster = :imePoster AND k.idKonferencija = :idKonferencija")
    boolean existsByImePosterAndIdKonferencija(@Param("imePoster") String imePoster,
                                               @Param("idKonferencija") Integer idKonferencija);

    @Query("SELECT p FROM Poster p JOIN p.konferencija k " +
            "WHERE p.imePoster = :imePoster AND k.idKonferencija = :idKonferencija")
    Optional<Poster> findByImePosterAndIdKonferencija(@Param("imePoster") String imePoster,
                                               @Param("idKonferencija") Integer idKonferencija);

    //boolean existsByImeNot(String imePoster);
}
