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
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER"})
    public List<PokroviteljGetDTO> pokrovitelji(@PathVariable("idKonferencija") Integer idKonferencija) {
        return pokroviteljService.listAll().stream()
                .filter(pokrovitelj -> pokrovitelj.getKonferencije().stream()
                        .anyMatch(konferencija -> konferencija.getIdKonferencija().equals(idKonferencija))).map(PokroviteljGetMapper::toDTO)
                .collect(Collectors.toList());
    }

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
            Pokrovitelj pokrovitelj = new Pokrovitelj();
            pokrovitelj.setKonferencije(existingKonferencija.get());
            pokrovitelj.setImePokrovitelja(pokroviteljDTO.getImePokrovitelja());
            pokrovitelj.setPromotivniMaterijal(pokroviteljDTO.getPromotivniMaterijal());
            Pokrovitelj saved = pokroviteljService.createPokrovitelj(pokrovitelj);
            existingKonferencija.get().setPokrovitelj(saved);
            return ResponseEntity.created(URI.create("/pokrovitelji/" + saved.getImePokrovitelja())).body(saved);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/ime/{imePokrovitelja}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public Pokrovitelj deletePokrovitelj(@PathVariable("imePokrovitelja") String imePokrovitelja){
        return pokroviteljService.deletePokrovitelj(imePokrovitelja);
    }

    @DeleteMapping("/idKonferencija/{idKonferencija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Object> deletePokrovitelj(@PathVariable("idKonferencija") Integer idKonferencija){
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(idKonferencija);
        if(existingKonferencija.isPresent()) {
            List<Pokrovitelj> list = pokroviteljService.listAll().stream()
                    .filter(pokrovitelj -> pokrovitelj.getKonferencije().stream()
                            .anyMatch(konferencija -> konferencija.getIdKonferencija().equals(idKonferencija))).toList();

            for (Pokrovitelj p : list) {
                pokroviteljService.deletePokrovitelj(p.getImePokrovitelja());
            }
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
