package com.rest;

import com.domain.Konferencija;
import com.domain.Korisnik;
import com.service.KonferencijeService;
import com.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registriraniKorisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KonferencijeService konferencijeService;

    @GetMapping("")
    public List<Korisnik> korisnikList() {
        return korisnikService.listAll();
    }

    @GetMapping("/{email}")
    public Korisnik getKorisnik(@PathVariable("email") String korisnikEmail) {
        return korisnikService.fetch(korisnikEmail);
    }

    @PostMapping("")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<Korisnik> createKorisnik(@RequestBody Korisnik korisnik) {
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(korisnik.getKonferencijaId());
        if (existingKonferencija.isPresent()) {
            korisnik.setKonferencija(existingKonferencija.get());
            Korisnik saved = korisnikService.createKorisnik(korisnik);
            existingKonferencija.get().setUser(saved);
            return ResponseEntity.created(URI.create("/korisnici/" + saved.getEmail())).body(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{email}")
    //@Secured("ROLE_USER")
    public Korisnik korisnik(@PathVariable("email") String newEmail, @RequestBody Korisnik korisnik) {
        if (!korisnik.getEmail().equals(newEmail))
            throw new IllegalArgumentException("");
        return korisnikService.updateKorisnik(korisnik,newEmail);
    }

    @DeleteMapping("/{email}")
    //@Secured("ROLE_ADMIN")
    public Korisnik deleteKorisnik(@PathVariable("email") String email) {
        return korisnikService.deleteKorisnik(email);
    }
}
