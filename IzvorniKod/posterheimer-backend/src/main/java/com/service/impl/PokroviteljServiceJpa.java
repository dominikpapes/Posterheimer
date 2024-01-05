package com.service.impl;

import com.dao.PokroviteljRepository;
import com.domain.Konferencija;
import com.domain.Korisnik;
import com.domain.Pokrovitelj;
import com.domain.Poster;
import com.service.EntityMissingException;
import com.service.PokroviteljService;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PokroviteljServiceJpa implements PokroviteljService {

    @Autowired
    private PokroviteljRepository pokroviteljRepository;

    @Override
    public List<Pokrovitelj> listAll() {
        return pokroviteljRepository.findAll();
    }

    @Override
    public Pokrovitelj fetch(String imePokrovitelja) {
        return pokroviteljRepository.findByImePokrovitelj(imePokrovitelja).orElseThrow(
                () -> new EntityMissingException(Pokrovitelj.class, imePokrovitelja));
    }

    @Override
    public Pokrovitelj createPokrovitelj(Pokrovitelj pokrovitelj) {
        validate(pokrovitelj);
        Assert.notNull(pokrovitelj, "Pokrovitelj must not be null!");
        /*
        if (pokroviteljRepository.countByImePokrovitelja(pokrovitelj.getImePokrovitelja()) > 0)
            throw new RequestDeniedException(
                    "Pokrovitelj with name " + pokrovitelj.getImePokrovitelja() + " already exists!"
            );
         */
        return pokroviteljRepository.save(pokrovitelj);
    }

    @Override
    public Pokrovitelj updatePokroviteljIme(Pokrovitelj pokrovitelj, String newImePokrovitelja) {
        validate(pokrovitelj);
        Assert.hasText(newImePokrovitelja, "Pokrovitelj name must be given!");
        if (!pokroviteljRepository.existsByImePokrovitelja(pokrovitelj.getImePokrovitelja()))
            throw new EntityMissingException(Pokrovitelj.class, pokrovitelj.getImePokrovitelja());
        if (pokroviteljRepository.existsByImePokrovitelja(newImePokrovitelja))
            throw new RequestDeniedException(
                    "Pokrovitelj with name " + newImePokrovitelja + " already exists!"
            );
        pokroviteljRepository.delete(pokrovitelj);
        pokrovitelj.setImePokrovitelja(newImePokrovitelja);
        return pokroviteljRepository.save(pokrovitelj);
    }

    @Override
    public Pokrovitelj deletePokrovitelj(String imePokrovitelja) {
        Pokrovitelj pokrovitelj = fetch(imePokrovitelja);
        pokroviteljRepository.delete(pokrovitelj);
        return pokrovitelj;
    }

    @Override
    public Optional<Pokrovitelj> findByImePokrovitelj(String imePokrovitelja) {
        return pokroviteljRepository.findByImePokrovitelj(imePokrovitelja);
    }

    private void validate(Pokrovitelj pokrovitelj) {
        Assert.hasText(pokrovitelj.getImePokrovitelja(),
                "Pokrovitelj name must be given!");
        Assert.isTrue(pokrovitelj.getPromotivniMaterijal() != null,
                "Promotivni materijal must be given!");
    }

}
