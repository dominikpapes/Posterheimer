package com.service;


import com.domain.Poster;

import java.util.List;
import java.util.Optional;

public interface PosterService {
    List<Poster> listAll();

    Poster fetch(String imePoster);

    Poster createPoster(Poster poster);

    Poster deletePoster(String imePoster);

    Poster updatePosterIme(Poster poster, String newImePoster);

    Poster updatePosterMail(Poster poster, String newMail);

    Poster updatePosterAuthor(Poster poster, String newImeAuthor,String newPrezimeAuthor);

    Optional<Poster> findByImePoster(String imePoster);
}
