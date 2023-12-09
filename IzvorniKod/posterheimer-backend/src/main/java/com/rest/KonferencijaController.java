package com.rest;
import com.domain.Konferencija;
import com.domain.Korisnik;
import com.service.EntityMissingException;
import com.service.KonferencijeService;
import com.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    //dostavi mi listu svih konferencija, treba implementirati DTOove kako bi bilo pojednostavnjeno bez mapiranja
    @GetMapping("")
    public List<Map<String, Object>> konferencije() {
        return konferencijeService.listAll().stream()
                .map(konferencija -> {
                    Map<String, Object> konferencijaMap = new HashMap<>();
                    konferencijaMap.put("idKonferencija", konferencija.getIdKonferencija());
                    konferencijaMap.put("imeKonferencija", konferencija.getImeKonferencija());
                    //konferencijaMap.put("mjesto", konferencija.getMjesto());
                    //konferencijaMap.put("datumVrijemePocetka", konferencija.getDatumVrijemePocetka());
                    //konferencijaMap.put("datumVrijemeZavrsetka", konferencija.getDatumVrijemeZavrsetka());
                    return konferencijaMap;
                }).collect(Collectors.toList());
    }

    //dostavljamo konferenciju prema njenom IDu
    @GetMapping("/{idKonferencija}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER", "ROLE_VISITOR"})
    public Konferencija getKonferencijaById(@PathVariable("idKonferencija") Integer idKonferencija) {
        return konferencijeService.fetch(idKonferencija);
    }
    //dobavljamo listu svih usera od konferencije s poslanim IDom npr .../3/users poslat ce se sve od one s IDom 3,
    // secured govori tko moze pristupit tome
    @GetMapping("/{idKonferencija}/users")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public Set<Korisnik> getUsers(@PathVariable("idKonferencija") Integer idKonferencija) {
        return konferencijeService.fetch(idKonferencija).getUsers();
    }

    //editanje konferencija cemo omogucit samo superuseru kad rijesimo superusera
    @PostMapping("")
  //@Secured("ROLE_SUPERUSER")
    public ResponseEntity<Konferencija> createKonferencija(@RequestBody Konferencija konferencija) {
        Korisnik tempKorisnik = new Korisnik(konferencija.getGenericUsername(), pwdEncoder.encode(konferencija.getGenericPassword()), konferencija.getIdKonferencija().toString(), konferencija.getImeKonferencija(), false, true);
        konferencija.setGenericPassword(tempKorisnik.getLozinka());
        tempKorisnik.setKonferencija(konferencija);
        Konferencija saved = konferencijeService.createKonferencija(konferencija);
        korisnikService.createKorisnik(tempKorisnik);
        return ResponseEntity.created(URI.create("/konferencije/" + saved.getIdKonferencija())).body(saved);
    }

    @PutMapping("/{id}")
  //@Secured("ROLE_SUPERUSER")
    public Konferencija konferencija(@PathVariable("id") Integer id, @RequestBody Konferencija konferencija) {
        if (!(konferencija.getIdKonferencija().equals(id)))
            throw new IllegalArgumentException("");
        return konferencijeService.updateKonferencija(konferencija,id);
    }


    @DeleteMapping("/{id}")
  //@Secured("ROLE_SUPERUSER")
    public Konferencija deleteKonferencija(@PathVariable("id") Integer idKonferencija) {
        return konferencijeService.deleteKonferencija(idKonferencija);
    }
}
