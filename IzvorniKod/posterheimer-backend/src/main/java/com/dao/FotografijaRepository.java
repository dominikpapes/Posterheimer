package com.dao;

import com.domain.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.Fotografija;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FotografijaRepository
        extends JpaRepository<Fotografija, Integer>

{
    @Query("SELECT p FROM Fotografija p WHERE p.idFotografija = :idFotografija")
    Optional<Fotografija> findByIdFotografija(@Param("idFotografija") Integer idFotografija);

    @Query("SELECT COUNT(p) FROM Fotografija p WHERE p.idFotografija = :idFotografija")
    int countByIdFotografija(@Param("idFotografija") Integer idFotografija);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Fotografija p WHERE p.idFotografija = :idFotografija")
    boolean existsByIdFotografija(@Param("idFotografija") Integer idFotografija);

}
