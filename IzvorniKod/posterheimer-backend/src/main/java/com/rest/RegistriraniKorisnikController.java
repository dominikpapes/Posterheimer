package com.rest;

import com.domain.Konferencija;
import com.domain.RegistriraniKorisnik;
import com.service.KonferencijeService;
import com.service.RegistriraniKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registriraniKorisnici")
public class RegistriraniKorisnikController {
    @Autowired
    private RegistriraniKorisnikService registriraniKorisnikService;
    @Autowired
    private KonferencijeService konferencijeService;

    @GetMapping("")
    public List<RegistriraniKorisnik> registriraniKorisnikList() {
        return registriraniKorisnikService.listAll();
    }

    @GetMapping("/{email}")
    public RegistriraniKorisnik getRegistriraniKorisnik(@PathVariable("email") String registriraniKorisnikEmail) {
        return registriraniKorisnikService.fetch(registriraniKorisnikEmail);
    }

    @PostMapping("")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<RegistriraniKorisnik> createRegistriraniKorisnik(@RequestBody RegistriraniKorisnik registriraniKorisnik) {
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(registriraniKorisnik.getKonferencijaId());
        if (existingKonferencija.isPresent()) {
            registriraniKorisnik.setKonferencija(existingKonferencija.get());
            RegistriraniKorisnik saved = registriraniKorisnikService.createRegistriraniKorisnik(registriraniKorisnik);
            existingKonferencija.get().setUser(saved);
            return ResponseEntity.created(URI.create("/registriraniKorisnici/" + saved.getEmail())).body(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{email}")
    //@Secured("ROLE_USER")
    public RegistriraniKorisnik registriraniKorisnik(@PathVariable("email") String newEmail, @RequestBody RegistriraniKorisnik registriraniKorisnik) {
        if (!registriraniKorisnik.getEmail().equals(newEmail))
            throw new IllegalArgumentException("");
        return registriraniKorisnikService.updateRegistriraniKorisnik(registriraniKorisnik,newEmail);
    }

    @DeleteMapping("/{email}")
    //@Secured("ROLE_ADMIN")
    public RegistriraniKorisnik deleteRegistriraniKorisnik(@PathVariable("email") String email) {
        return registriraniKorisnikService.deleteRegistriraniKorisnik(email);
    }
}
