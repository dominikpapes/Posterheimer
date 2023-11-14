package com.rest;
import com.domain.Konferencija;
import com.domain.Korisnik;
import com.service.EntityMissingException;
import com.service.KonferencijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/konferencije")
public class KonferencijaController {
    @Autowired
    private KonferencijeService konferencijeService;

    @GetMapping("")
    public List<Map<String, Object>> konferencije() {
        return konferencijeService.listAll().stream()
                .map(konferencija -> {
                    Map<String, Object> konferencijaMap = new HashMap<>();
                    konferencijaMap.put("idKonferencija", konferencija.getIdKonferencija());
                    konferencijaMap.put("imeKonferencija", konferencija.getImeKonferencija());
                    konferencijaMap.put("mjesto", konferencija.getMjesto());
                    konferencijaMap.put("datumVrijemePocetka", konferencija.getDatumVrijemePocetka());
                    konferencijaMap.put("datumVrijemeZavrsetka", konferencija.getDatumVrijemeZavrsetka());
                    return konferencijaMap;
                }).collect(Collectors.toList());
    }

    @GetMapping("/{idKonferencija}")
    public Konferencija getKonferencijaById(@PathVariable("idKonferencija") Integer idKonferencija) {
        return konferencijeService.fetch(idKonferencija);
    }
    @GetMapping("/{idKonferencija}/users")
    public Set<Korisnik> getUsers(@PathVariable("idKonferencija") Integer idKonferencija) {
        return konferencijeService.fetch(idKonferencija).getUsers();
    }
    @GetMapping("/{idKonferencija}/{genericUsername}/{genericPassword}")
    public boolean loginGeneric(@PathVariable("id") Integer idKonferencija,@PathVariable("genericUsername") String genericUsername,
                      @PathVariable("genericPassword") String genericPassword){
        try{
        Konferencija saved=konferencijeService.fetch(idKonferencija);
        return saved.getGenericUsername().equals(genericUsername)&&saved.getGenericPassword().equals(genericPassword);}
        catch (EntityMissingException exception){
            throw new EntityMissingException(Konferencija.class,idKonferencija);
        }
    }

    @PostMapping("")
    @Secured("ROLE_SUPERUSER")
    public ResponseEntity<Konferencija> createKonferencija(@RequestBody Konferencija konferencija) {
        Konferencija saved = konferencijeService.createKonferencija(konferencija);
        return ResponseEntity.created(URI.create("/konferencije/" + saved.getIdKonferencija())).body(saved);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_SUPERUSER")
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
