package com.service.impl;

import com.dao.PosterRepository;
import com.domain.Korisnik;
import com.domain.Poster;
import com.service.EntityMissingException;
import com.service.PosterService;
import com.service.RequestDeniedException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
@Service
public class PosterServiceJpa implements PosterService {
    @Autowired
    PosterRepository posterRepository;

    @Override
    public List<Poster> listAll(){
        return posterRepository.findAll();
    }
    @Override
    public Poster fetch(String imePoster){
        return posterRepository.findByImePoster(imePoster).orElseThrow((()->new EntityMissingException(Poster.class,imePoster)));
    }
    @Override
    public Poster createPoster(Poster poster){
        validate(poster);
        Assert.notNull(poster,"Poster must not be null!");
        if (posterRepository.countByImePoster(poster.getImePoster()) > 0)
            throw new RequestDeniedException(
                    "Poster with name " + poster.getImePoster() + " already exists!"
            );return posterRepository.save(poster);
    }
    @Override
    public Poster deletePoster(String imePoster){
        Poster poster=fetch(imePoster);
        posterRepository.delete(poster);
        return poster;
    }
    @Override
    public Poster updatePosterIme(Poster poster, String newImePoster){
        String posterIme = poster.getImePoster();
        Assert.hasText(newImePoster, "Poster name must be given!");
        if (!posterRepository.existsByImePoster(poster.getImePoster()))
            throw new EntityMissingException(Poster.class, poster.getImePoster());
        if (posterRepository.existsByImePoster(newImePoster))
            throw new RequestDeniedException(
                    "Poster with name " + newImePoster + " already exists!"
            );
        posterRepository.delete(poster);
        poster.setImePoster(newImePoster);
        return posterRepository.save(poster);
    }
    @Override
    public Poster updatePosterMail(Poster poster, String newEmail){
            String posterMail = poster.getPosterEmail();
            Assert.hasText(newEmail, "Poster email must be given!");
            if (!posterRepository.existsByPosterEmail(poster.getPosterEmail()))
                throw new EntityMissingException(Poster.class, poster.getImePoster());
            if (posterRepository.existsByPosterEmail(newEmail))
                throw new RequestDeniedException(
                        "Poster with email " + newEmail + " already exists!"
                );
            posterRepository.delete(poster);
            poster.setImePoster(newEmail);
            return posterRepository.save(poster);
    }
    @Override
    public Poster updatePosterAuthor(Poster poster, String newAuthorIme,String newAuthorPrezime){
        Assert.hasText(newAuthorIme, "Poster Author name must be given!");
        Assert.hasText(newAuthorPrezime, "Poster Author surname must be given!");
        if (!(posterRepository.existsByImeAutor(poster.getImeAutor())&&posterRepository.existsByPrezimeAutor(poster.getPrezimeAutor())))
            throw new EntityMissingException(Poster.class, poster.getImeAutor()+ " " +poster.getPrezimeAutor());
        posterRepository.delete(poster);
        poster.setImeAutor(newAuthorIme);
        poster.setPrezimeAutor(newAuthorPrezime);
        return posterRepository.save(poster);
    }

    @Override
    public Optional<Poster> findByImePoster(String imePoster){
        return posterRepository.findByImePoster(imePoster);
    }



    private void validate(Poster poster){
        Assert.hasText(poster.getImePoster(),
                "Poster name must be given!");
        Assert.hasText(poster.getFilePath(),
                "Poster file path must be given!");
        Assert.hasText(poster.getImeAutor(),
                "Poster author name must be given!");
        Assert.hasText(poster.getPrezimeAutor(),
                "Poster author surname must be given!");
    }
}