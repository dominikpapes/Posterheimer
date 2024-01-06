package com.rest;
import com.domain.Konferencija;
import com.domain.Korisnik;
import com.dto.KonferencijaDTOs.KonferencijaIdDTO;
import com.dto.KonferencijaDTOs.KonferencijaGetDTO;
import com.dto.KonferencijaDTOs.KonferencijaPostDTO;
import com.dto.KorisnikDTOs.KorisnikGetDTO;
import com.mapper.KonferencijaMappers.KonferencijaGetMapper;
import com.mapper.KonferencijaMappers.KonferencijaPostMapper;
import com.mapper.KorisnikMappers.KorisnikGetMapper;
import com.service.KonferencijeService;
import com.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//Controller je prvi sloj, s njega se salje u service sloj
@RestController
@RequestMapping("/konferencije")
public class KonferencijaController {
    //definiramo service od drugih objekata ukoliko ih trebamo pozivati
    @Autowired
    private KonferencijeService konferencijeService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private  PasswordEncoder pwdEncoder;

    //dohvacanje svih konferencija
    @GetMapping("")
    public List<KonferencijaGetDTO> konferencije() {
        List<Konferencija> konferencije = konferencijeService.listAll();
        return konferencije.stream()
                .map(KonferencijaGetMapper::toDTO)
                .collect(Collectors.toList());
    }

    //dohvati konferenciju prema idu
    @GetMapping("/{idKonferencija}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER", "ROLE_VISITOR"})
    public KonferencijaGetDTO getKonferencijaById(@PathVariable("idKonferencija") Integer idKonferencija) {
        Konferencija konferencija = konferencijeService.fetch(idKonferencija);
        return KonferencijaGetMapper.toDTO(konferencija);
    }
    //dobavljamo listu svih usera od konferencije s poslanim IDom npr .../3/users poslat ce se sve od one s IDom 3,
    // secured govori tko moze pristupit tome
    @GetMapping("/{idKonferencija}/users")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public List<KorisnikGetDTO> getUsers(@PathVariable("idKonferencija") Integer idKonferencija) {
        return konferencijeService.fetch(idKonferencija).getUsers().stream().map(KorisnikGetMapper::toDTO)
                .collect(Collectors.toList());
    }

    //dto
    @PostMapping("")
  //@Secured("ROLE_SUPERUSER")
    public ResponseEntity<Konferencija> createKonferencija(@RequestBody KonferencijaPostDTO konferencijaPostDTO) {
        Konferencija konferencija = KonferencijaPostMapper.toEntity(konferencijaPostDTO);
        konferencija.setIdKonferencija(-1);
        Korisnik tempKorisnik = new Korisnik(konferencija.getGenericUsername(), pwdEncoder.encode(konferencija.getGenericPassword()), konferencija.getIdKonferencija().toString(), konferencija.getImeKonferencija(), false, true);
        konferencija.setGenericPassword(tempKorisnik.getLozinka());
        tempKorisnik.setKonferencija(konferencija);
        Konferencija saved = konferencijeService.createKonferencija(konferencija);
        korisnikService.createKorisnik(tempKorisnik);
        tempKorisnik=new Korisnik(konferencijaPostDTO.getAdminUsername(), konferencijaPostDTO.getAdminPassword(), null,null,true,false);
        korisnikService.createKorisnik(tempKorisnik);
        return ResponseEntity.created(URI.create("/konferencije/" + saved.getIdKonferencija())).body(saved);
    }


    /*@PutMapping("/{id}")
  //@Secured("ROLE_SUPERUSER")
    public Konferencija konferencija(@PathVariable("id") Integer id, @RequestBody Konferencija konferencija) {
        if (!(konferencija.getIdKonferencija().equals(id)))
            throw new IllegalArgumentException("");
        return konferencijeService.updateKonferencija(konferencija,id);
    }*/

    //dto
    @DeleteMapping("")
  //@Secured("ROLE_SUPERUSER")
    public ResponseEntity<?> deleteKonferencija(@RequestBody KonferencijaIdDTO konferencijaIdDTO) {
        Integer idKonferencija = konferencijaIdDTO.getIdKonferencija();
        if (idKonferencija == null) {
            return ResponseEntity.badRequest().body("ID must not be null");
        }
        konferencijeService.deleteKonferencija(idKonferencija);
        return ResponseEntity.noContent().build();
    }
}
