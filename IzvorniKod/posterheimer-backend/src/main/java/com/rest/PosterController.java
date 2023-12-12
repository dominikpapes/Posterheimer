package com.rest;

import com.domain.Konferencija;
import com.domain.Poster;
import com.service.KonferencijeService;
import com.service.PosterService;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posteri")
public class PosterController {
    @Autowired
    PosterService posterService;
    @Autowired
    KonferencijeService konferencijeService;

    @GetMapping("")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER", "ROLE_VISITOR"})
    public List<Poster> posterList(){
        return posterService.listAll();
    }
    //TODO SRUSI CITAV BACKEND, NIS NE MOZE RESPOND POSLAT, NE MICAT COMMENT
    @PostMapping("")
    public ResponseEntity<Poster> createPoster(@RequestBody Poster poster) {
        Optional<Poster> existingPoster = posterService.findByImePoster(poster.getImePoster());
        Optional<Konferencija> existingKonf = konferencijeService.findById(poster.getKonferencija().getIdKonferencija());
        if(existingPoster.isPresent()){
            if (existingPoster.get().getKonferencija().getIdKonferencija().equals(poster.getKonferencija().getIdKonferencija())) {
                throw new RequestDeniedException("Poster with name: " + poster.getImePoster() + " already exists!");
            }
        }
        if(existingKonf.isPresent()){
            poster.setKonferencija(existingKonf.get());
            Poster saved=posterService.createPoster(poster);
            existingKonf.get().setPoster(saved);
            return ResponseEntity.created(URI.create("/posteri/" + saved.getImePoster())).body(saved);
        }
        else
            return ResponseEntity.notFound().build();
    }
    @GetMapping("{imePoster}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_USER"})
    public Poster getPoster(@PathVariable("imePoster") String imePoster) {
        return posterService.fetch(imePoster);
    }

    @DeleteMapping("{imePostera}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public Poster deletePoster(@PathVariable("imePostera") String imePostera){
        return posterService.deletePoster(imePostera);
    }
}
