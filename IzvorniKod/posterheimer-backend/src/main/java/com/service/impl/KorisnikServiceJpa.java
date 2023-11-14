package com.service.impl;

import com.dao.KorisnikRepository;
import com.domain.Korisnik;
import com.service.EntityMissingException;
import com.service.KorisnikService;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikServiceJpa implements KorisnikService {
    private static final String EMAIL_FORMAT = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Override
    public List<Korisnik> listAll() {
        return korisnikRepository.findAll();
    }

    @Override
    public Optional<Korisnik> findByEmail(String korisnikEmail) {
        return korisnikRepository.findByEmail(korisnikEmail);
    }

    @Override
    public Korisnik fetch(String korisnikEmail) {
        return findByEmail(korisnikEmail).orElseThrow(
                () -> new EntityMissingException(Korisnik.class, korisnikEmail)
        );
    }

    @Override
    public Korisnik createKorisnik(Korisnik korisnik) {
        validate(korisnik);
        Assert.notNull(korisnik.getEmail(),
                "Email must be not null: " + korisnik.getEmail());
        if (korisnikRepository.countByEmail(korisnik.getEmail()) > 0)
            throw new RequestDeniedException(
                    "Korisnik with email " + korisnik.getEmail() + " already exists"
            );
        return korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik updateKorisnik(Korisnik korisnik, String newEmail) {
        validate(korisnik);
        String korisnikEmail = korisnik.getEmail();
        if (!korisnikRepository.existsByEmail(korisnikEmail))
            throw new EntityMissingException(Korisnik.class, korisnikEmail);
        if (korisnikRepository.existsByEmailNot(newEmail))
            throw new RequestDeniedException(
                    "Registrirani korisnik with email " + korisnik.getEmail() + " already exists"
            );
        return korisnikRepository.save(korisnik);
    }

    @Override
        public Korisnik deleteKorisnik(String korisnikEmail) {
        Korisnik korisnik = fetch(korisnikEmail);
        korisnikRepository.delete(korisnik);
        return korisnik;
    }

    private void validate(Korisnik registriraniKorisnik) {
        Assert.notNull(registriraniKorisnik, "Registrirani korisnik object must be given");
        String email = registriraniKorisnik.getEmail();
        Assert.hasText(email, "Email must be given");
        Assert.isTrue(email.matches(EMAIL_FORMAT), "Invalid email format: '" + email + "'");
    }
}
