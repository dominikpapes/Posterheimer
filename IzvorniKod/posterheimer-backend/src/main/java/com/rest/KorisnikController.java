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
import com.service.RequestDeniedException;
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

    @GetMapping("/idKonferencija/{idKonferencija}/{email}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public KorisnikGetByEmailDTO getKorisnik(@PathVariable("email") String korisnikEmail,
                                             @PathVariable("idKonferencija") Integer idKonferencija) {
        Korisnik k = korisnikService.fetchByEmailAndIdKonferenecija(korisnikEmail, idKonferencija);
        return KorisnikGetByEmailMapper.toDTO(k);
    }

    @PostMapping("")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_VISITOR"})
    public ResponseEntity<Korisnik> createKorisnik(@RequestBody KorisnikPostDTO korisnikDTO) {
        korisnikDTO.setLozinka(encoder.encode(korisnikDTO.getLozinka()));
        Optional<Korisnik> existingKorisnik = korisnikService.findByEmailAndIdKonferencija(korisnikDTO.getEmail(), korisnikDTO.getIdKonferencije());
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(korisnikDTO.getIdKonferencije());
        if(existingKorisnik.isPresent()){
            if (existingKorisnik.get().getKonferencijaId().equals(korisnikDTO.getIdKonferencije())) {
                throw new RequestDeniedException("Korisnik with email: " + korisnikDTO.getEmail() + " already exists "
                        + "in conference " + korisnikDTO.getIdKonferencije() + "!");
            }
        }
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

    @PutMapping("/idKonferencija/{idKonferencija}/{email}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Object> changeKorisnikEmail(@PathVariable("idKonferencija") Integer idKonferencija,
                                                      @PathVariable("email") String newEmail) {
        Optional<Korisnik> optKorisnik=korisnikService.findByEmailAndIdKonferencija(newEmail, idKonferencija);
        if(optKorisnik.isPresent()){
            optKorisnik.get().setAdmin(true);
            korisnikService.deleteKorisnik(newEmail, idKonferencija);
            korisnikService.createKorisnik(optKorisnik.get());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/idKonferencija/{idKonferencija}/{email}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_USER"})
    public Korisnik deleteKorisnik(@PathVariable("idKonferencija") Integer idKonferencija, @PathVariable("email") String email) {
        Korisnik k = korisnikService.fetchByEmailAndIdKonferenecija(email, idKonferencija);
        return korisnikService.deleteKorisnik(k.getEmail(), k.getKonferencijaId());
    }

    @DeleteMapping("/{email}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<Object> deleteKorisnikOnAllConferences(@PathVariable("email") String email) {
        List<Korisnik> sviRacuni = korisnikService.listAll().stream()
                .filter(korisnik -> korisnik.getEmail().equals(email)).toList();
        if (!sviRacuni.isEmpty()) {
            for (Korisnik k : sviRacuni) {
                korisnikService.deleteKorisnik(k.getEmail(), k.getKonferencijaId());
            }
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
