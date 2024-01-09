package com.service;


import com.domain.Poster;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PosterService {
    List<Poster> listAll();

    Poster fetch(Integer idPoster);

    Poster createPoster(Poster poster);

    Poster deletePoster(Integer idPoster);

    Poster updatePosterIme(Poster poster, String newImePoster);

    Poster updatePosterMail(Poster poster, String newMail);

    Poster updatePosterAuthor(Poster poster, String newImeAuthor,String newPrezimeAuthor);

    Optional<Poster> findByImePoster(String imePoster);
    void save(Poster poster);

    Optional<Poster> findByImePosterAndIdKonferencija(String imePoster, Integer idKonferencija);
}
