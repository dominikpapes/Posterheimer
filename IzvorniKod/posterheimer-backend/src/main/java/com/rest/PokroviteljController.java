package com.rest;

import com.domain.Konferencija;
import com.domain.Pokrovitelj;
import com.domain.Poster;
import com.dto.PokroviteljDTOs.PokroviteljGetDTO;
import com.dto.PokroviteljDTOs.PokroviteljPostDTO;
import com.mapper.KonferencijaMappers.KonferencijaGetMapper;
import com.mapper.PokroviteljMappers.PokroviteljGetMapper;
import com.mapper.PosterMapper.PosterGetMapper;
import com.service.KonferencijeService;
import com.service.PokroviteljService;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokrovitelji")
public class PokroviteljController {
    @Autowired
    private PokroviteljService pokroviteljService;
    @Autowired
    private KonferencijeService konferencijeService;

    @GetMapping("/idKonferencija/{idKonferencija}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER"})
    public List<PokroviteljGetDTO> pokrovitelji(@PathVariable("idKonferencija") Integer idKonferencija) {
        return pokroviteljService.listAll().stream()
                .filter(pokrovitelj -> pokrovitelj.getKonferencije().stream()
                        .anyMatch(konferencija -> konferencija.getIdKonferencija().equals(idKonferencija))).map(PokroviteljGetMapper::toDTO)
                .collect(Collectors.toList());
    }


    /*
    @PostMapping("")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Pokrovitelj> createPokrovitelj(@RequestBody PokroviteljPostDTO pokroviteljDTO) {
        Optional<Pokrovitelj> existingPokrovitelj = pokroviteljService.findByImePokrovitelj(pokroviteljDTO.getImePokrovitelja());
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(pokroviteljDTO.getIdKonferencija());
        if (existingPokrovitelj.isPresent()) {
            if (existingPokrovitelj.get().getKonferencije().stream()
                    .anyMatch(konferencija -> konferencija.getIdKonferencija().equals(pokroviteljDTO.getIdKonferencija()))) {
                throw new RequestDeniedException("Pokrovitelj with name: " + pokroviteljDTO.getImePokrovitelja() + " already exists!");
            }
        }
        if(existingKonferencija.isPresent()){
            if(existingPokrovitelj.isPresent()){
                existingPokrovitelj.get().setKonferencije(existingKonferencija.get());
                existingKonferencija.get().setPokrovitelj(existingPokrovitelj.get());
                return ResponseEntity.created(URI.create("/pokrovitelji/" + existingPokrovitelj.get().getImePokrovitelja())).body(existingPokrovitelj.get());
            }
            Pokrovitelj pokrovitelj = new Pokrovitelj();
            pokrovitelj.setKonferencije(existingKonferencija.get());
            pokrovitelj.setImePokrovitelja(pokroviteljDTO.getImePokrovitelja());
            pokrovitelj.setPromotivniMaterijal(pokroviteljDTO.decodeBase64String(pokroviteljDTO.getPromotivniMaterijal()));
            pokrovitelj.setUrlPromo(pokroviteljDTO.getUrlPromo());
            Pokrovitelj saved = pokroviteljService.createPokrovitelj(pokrovitelj);
            existingKonferencija.get().setPokrovitelj(saved);
            return ResponseEntity.created(URI.create("/pokrovitelji/" + saved.getImePokrovitelja())).body(saved);
        }
        else
            return ResponseEntity.notFound().build();
    }
     */

    @PostMapping("")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Pokrovitelj> createPokrovitelj(@RequestBody PokroviteljPostDTO pokroviteljDTO) {
        Optional<Pokrovitelj> existingPokrovitelj = pokroviteljService.findByImePokrovitelj(pokroviteljDTO.getImePokrovitelja());
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(pokroviteljDTO.getIdKonferencija());

        if (existingPokrovitelj.isPresent() && existingKonferencija.isPresent()) {
            // Check if the relationship already exists
            if (existingPokrovitelj.get().getKonferencije().contains(existingKonferencija.get())) {
                throw new RequestDeniedException("Pokrovitelj with name: " + pokroviteljDTO.getImePokrovitelja() + " already registered for this conference!");
            }

            // Add the relationship
            existingPokrovitelj.get().getKonferencije().add(existingKonferencija.get());
            existingKonferencija.get().getPokrovitelji().add(existingPokrovitelj.get());

            pokroviteljService.save(existingPokrovitelj.get());
            konferencijeService.save(existingKonferencija.get());

            return ResponseEntity.created(URI.create("/pokrovitelji/" + existingPokrovitelj.get().getImePokrovitelja())).body(existingPokrovitelj.get());
        }
        else if (!existingPokrovitelj.isPresent() && existingKonferencija.isPresent()) {
            // Create new Pokrovitelj
            Pokrovitelj newPokrovitelj = new Pokrovitelj();
            newPokrovitelj.setImePokrovitelja(pokroviteljDTO.getImePokrovitelja());
            newPokrovitelj.setPromotivniMaterijal(pokroviteljDTO.decodeBase64String(pokroviteljDTO.getPromotivniMaterijal()));
            newPokrovitelj.setUrlPromo(pokroviteljDTO.getUrlPromo());
            newPokrovitelj.getKonferencije().add(existingKonferencija.get());

            // Add the relationship from the conference side as well
            existingKonferencija.get().getPokrovitelji().add(newPokrovitelj);

            Pokrovitelj saved = pokroviteljService.createPokrovitelj(newPokrovitelj);
            konferencijeService.save(existingKonferencija.get());

            return ResponseEntity.created(URI.create("/pokrovitelji/" + saved.getImePokrovitelja())).body(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
    @DeleteMapping("/id/{idPokrovitelj}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Object> deletePokroviteljById(@PathVariable("idPokrovitelj") Integer idPokrovitelj){
        Optional<Pokrovitelj> existingPokrovitelj = pokroviteljService.findByIdPokrovitelj(idPokrovitelj);
        if (existingPokrovitelj.isPresent()) {
            for (Konferencija konferencija : existingPokrovitelj.get().getKonferencije()) {
                konferencija.getPokrovitelji().remove(existingPokrovitelj.get());
            }
            existingPokrovitelj.get().getKonferencije().clear();

            pokroviteljService.save(existingPokrovitelj.get());
            pokroviteljService.deletePokrovitelj(idPokrovitelj);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @DeleteMapping("/idKonferencija/{idKonferencija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Object> deletePokrovitelj(@PathVariable("idKonferencija") Integer idKonferencija){
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(idKonferencija);
        if(existingKonferencija.isPresent()) {
            List<Pokrovitelj> list = pokroviteljService.listAll().stream()
                    .filter(pokrovitelj -> pokrovitelj.getKonferencije().stream()
                            .anyMatch(konferencija -> konferencija.getIdKonferencija().equals(idKonferencija))).toList();

            for (Pokrovitelj p : list) {
                pokroviteljService.deletePokrovitelj(p.getIdPokrovitelj());
            }
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
     */

    @DeleteMapping("/idKonferencija/{idKonferencija}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Object> deletePokrovitelj(@PathVariable("idKonferencija") Integer idKonferencija){
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(idKonferencija);
        if(existingKonferencija.isPresent()) {
            existingKonferencija.get().getPokrovitelji().clear();
            konferencijeService.save(existingKonferencija.get());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
