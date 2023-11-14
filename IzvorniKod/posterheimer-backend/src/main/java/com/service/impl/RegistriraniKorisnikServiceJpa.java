package com.service.impl;

import com.dao.RegistriraniKorisnikRepository;
import com.domain.RegistriraniKorisnik;
import com.service.EntityMissingException;
import com.service.RegistriraniKorisnikService;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class RegistriraniKorisnikServiceJpa implements RegistriraniKorisnikService {
    private static final String EMAIL_FORMAT = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Autowired
    private RegistriraniKorisnikRepository registriraniKorisnikRepository;

    @Override
    public List<RegistriraniKorisnik> listAll() {
        return registriraniKorisnikRepository.findAll();
    }

    @Override
    public Optional<RegistriraniKorisnik> findByEmail(String registriraniKorisnikEmail) {
        return registriraniKorisnikRepository.findByEmail(registriraniKorisnikEmail);
    }

    @Override
    public RegistriraniKorisnik fetch(String registriraniKorisnikEmail) {
        return findByEmail(registriraniKorisnikEmail).orElseThrow(
                () -> new EntityMissingException(RegistriraniKorisnik.class, registriraniKorisnikEmail)
        );
    }

    @Override
    public RegistriraniKorisnik createRegistriraniKorisnik(RegistriraniKorisnik registriraniKorisnik) {
        validate(registriraniKorisnik);
        Assert.notNull(registriraniKorisnik.getEmail(),
                "Email must be not null: " + registriraniKorisnik.getEmail());
        if (registriraniKorisnikRepository.countByEmail(registriraniKorisnik.getEmail()) > 0)
            throw new RequestDeniedException(
                    "Korisnik with email " + registriraniKorisnik.getEmail() + " already exists"
            );
        return registriraniKorisnikRepository.save(registriraniKorisnik);
    }

    @Override
    public RegistriraniKorisnik updateRegistriraniKorisnik(RegistriraniKorisnik registriraniKorisnik, String newEmail) {
        validate(registriraniKorisnik);
        String registriraniKorisnikEmail = registriraniKorisnik.getEmail();
        if (!registriraniKorisnikRepository.existsByEmail(registriraniKorisnikEmail))
            throw new EntityMissingException(RegistriraniKorisnik.class, registriraniKorisnikEmail);
        if (registriraniKorisnikRepository.existsByEmailNot(newEmail))
            throw new RequestDeniedException(
                    "Registrirani korisnik with email " + registriraniKorisnik.getEmail() + " already exists"
            );
        return registriraniKorisnikRepository.save(registriraniKorisnik);
    }

    @Override
        public RegistriraniKorisnik deleteRegistriraniKorisnik(String registriraniKorisnikEmail) {
        RegistriraniKorisnik registriraniKorisnik = fetch(registriraniKorisnikEmail);
        registriraniKorisnikRepository.delete(registriraniKorisnik);
        return registriraniKorisnik;
    }

    private void validate(RegistriraniKorisnik registriraniKorisnik) {
        Assert.notNull(registriraniKorisnik, "Registrirani korisnik object must be given");
        String email = registriraniKorisnik.getEmail();
        Assert.hasText(email, "Email must be given");
        Assert.isTrue(email.matches(EMAIL_FORMAT), "Invalid email format: '" + email + "'");
    }
}
