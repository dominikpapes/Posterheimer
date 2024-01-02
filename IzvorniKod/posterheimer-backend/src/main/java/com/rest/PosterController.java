package com.rest;

import com.domain.Konferencija;
import com.domain.Korisnik;
import com.domain.Poster;
import com.dto.PosterDTOs.PosterGetDTO;
import com.dto.PosterDTOs.PosterPostDTO;
import com.mapper.PosterMapper.PosterGetMapper;
import com.service.EntityMissingException;
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

    @GetMapping("/idKonferencija/{idKonferencija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER", "ROLE_VISITOR"})
    public List<PosterGetDTO> posterList(@PathVariable("idKonferencija") Integer idKonferencija){
        return posterService.listAll().stream().filter(poster -> poster.getKonferencija().getIdKonferencija()
                        .equals(idKonferencija)).map(PosterGetMapper::toDTO).toList();
    }
    @PostMapping("")
    public ResponseEntity<Poster> createPoster(@RequestBody PosterPostDTO posterDTO) {
        Optional<Poster> existingPoster = posterService.findByImePoster(posterDTO.getImePoster());
        Optional<Konferencija> existingKonf = konferencijeService.findById(posterDTO.getIdKonferencija());
        if(existingPoster.isPresent()){
            if (existingPoster.get().getKonferencija().getIdKonferencija().equals(posterDTO.getIdKonferencija())) {
                throw new RequestDeniedException("Poster with name: " + posterDTO.getImePoster() + " already exists!");
            }
        }
        if(existingKonf.isPresent()){
            Poster poster=new Poster();
            poster.setKonferencija(existingKonf.get());
            poster.setPosterEmail(posterDTO.getPosterEmail());
            poster.setImePoster(posterDTO.getImePoster());
            poster.setImeAutor(posterDTO.getImeAutor());
            poster.setPrezimeAutor(posterDTO.getPrezimeAutor());
            poster.setBrGlasova(0);
            poster.setFilePath(posterDTO.getFilePath());
            Poster saved=posterService.createPoster(poster);
            existingKonf.get().setPoster(saved);
            return ResponseEntity.created(URI.create("/posteri/" + saved.getImePoster())).body(saved);
        }
        else
            return ResponseEntity.notFound().build();
    }
    @GetMapping("/ime/{imePoster}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_USER"})
    public PosterGetDTO getPoster(@PathVariable("imePoster") String imePoster) {
        Poster p = posterService.fetch(imePoster);
        return PosterGetMapper.toDTO(p);
    }

    @DeleteMapping("/ime/{imePostera}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public Poster deletePoster(@PathVariable("imePostera") String imePostera){
        return posterService.deletePoster(imePostera);
    }
    @DeleteMapping("/idKonferencija/{idKonferencija}")
    @Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Object> deletePoster(@PathVariable("idKonferencija") Integer idKonferencija){
        Optional<Konferencija> existingKonf = konferencijeService.findById(idKonferencija);
        if(existingKonf.isPresent()) {
            List<Poster> list = posterService.listAll().stream().filter(poster -> poster.getKonferencija().getIdKonferencija().equals(idKonferencija)).toList();
            for (Poster p : list) {
                posterService.deletePoster(p.getImePoster());
            }
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
