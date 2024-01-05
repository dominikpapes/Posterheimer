package com.rest;

import com.domain.Konferencija;
import com.domain.Korisnik;
import com.dto.KonferencijaDTOs.KonferencijaGetDTO;
import com.dto.KorisnikDTOs.KorisnikGetByEmailDTO;
import com.dto.KorisnikDTOs.KorisnikGetDTO;
import com.dto.KorisnikDTOs.KorisnikPostDTO;
import com.mapper.KonferencijaMappers.KonferencijaGetMapper;
import com.mapper.KorisnikMappers.KorisnikGetByEmailMapper;
import com.mapper.KorisnikMappers.KorisnikGetMapper;
import com.mapper.KorisnikMappers.KorisnikPostMapper;
import com.service.KonferencijeService;
import com.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KonferencijeService konferencijeService;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public List<KorisnikGetDTO> korisnikList() {
        List<Korisnik> korisnici = korisnikService.listAll();
        return korisnici.stream().
                map(KorisnikGetMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{email}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public KorisnikGetByEmailDTO getKorisnik(@PathVariable("email") String korisnikEmail) {
        Korisnik k = korisnikService.fetch(korisnikEmail);
        return KorisnikGetByEmailMapper.toDTO(k);
    }

    @PostMapping("")
    public ResponseEntity<Korisnik> createKorisnik(@RequestBody KorisnikPostDTO korisnikDTO) {
        korisnikDTO.setLozinka(encoder.encode(korisnikDTO.getLozinka()));
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(korisnikDTO.getIdKonferencije());
        if (existingKonferencija.isPresent()) {
            Korisnik korisnik= KorisnikPostMapper.toEntity(korisnikDTO);
            korisnik.setKonferencija(existingKonferencija.get());
            Korisnik saved = korisnikService.createKorisnik(korisnik);
            existingKonferencija.get().setUser(saved);
            return ResponseEntity.created(URI.create("/korisnici/" + saved.getEmail())).body(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
/*
    @PutMapping("/{email}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_USER"})
    public Korisnik changeKorisnikEmail(@PathVariable("email") String newEmail, @RequestBody Korisnik korisnik) {
        if (korisnik.getEmail().equals(newEmail))
            throw new IllegalArgumentException("");
        return korisnikService.updateKorisnik(korisnik,newEmail);
    }*/

    @DeleteMapping("/{email}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_USER"})
    public Korisnik deleteKorisnik(@PathVariable("email") String email) {
        return korisnikService.deleteKorisnik(email);
    }
}
