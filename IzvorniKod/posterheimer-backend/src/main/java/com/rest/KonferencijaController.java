package com.rest;
import com.domain.Konferencija;
import com.domain.Korisnik;
import com.service.KonferencijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/konferencije")
public class KonferencijaController {
    @Autowired
    private KonferencijeService konferencijeService;

    @GetMapping("")
    public List<Konferencija> konferencije() {
        return konferencijeService.listAll();
    }

    @GetMapping("/{idKonferencija}")
    public Konferencija getKonferencijaById(@PathVariable("id") Integer idKonferencija) {
        return konferencijeService.fetch(idKonferencija);
    }
    @GetMapping("/{idKonferencija}/users")
    public Set<Korisnik> getUsers(@PathVariable("id") Integer idKonferencija) {
        return konferencijeService.fetch(idKonferencija).getUsers();
    }

    @PostMapping("")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<Konferencija> createKonferencija(@RequestBody Konferencija konferencija) {
        Konferencija saved = konferencijeService.createKonferencija(konferencija);
        return ResponseEntity.created(URI.create("/konferencije/" + saved.getIdKonferencija())).body(saved);
    }

    @PutMapping("/{id}")
    //@Secured("ROLE_ADMIN")
    public Konferencija konferencija(@PathVariable("id") Integer id, @RequestBody Konferencija konferencija) {
        if (!(konferencija.getIdKonferencija().equals(id)))
            throw new IllegalArgumentException("");
        return konferencijeService.updateKonferencija(konferencija,id);
    }


    @DeleteMapping("/{id}")
    //@Secured("ROLE_ADMIN")
    public Konferencija deleteKonferencija(@PathVariable("id") Integer idKonferencija) {
        return konferencijeService.deleteKonferencija(idKonferencija);
    }
}
