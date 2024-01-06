package com.rest;

import com.domain.Konferencija;
import com.domain.Korisnik;
import com.domain.Poster;
import com.dto.PosterDTOs.PosterGetDTO;
import com.dto.PosterDTOs.PosterPostDTO;
import com.dto.PosterDTOs.PosterVoteDTO;
import com.mapper.PosterMapper.PosterGetMapper;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posteri")
public class PosterController {
    @Autowired
    PosterService posterService;
    @Autowired
    KonferencijeService konferencijeService;
    @Autowired
    KorisnikService korisnikService;

    @GetMapping("/idKonferencija/{idKonferencija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER", "ROLE_VISITOR"})
    public List<PosterGetDTO> posterList(@PathVariable("idKonferencija") Integer idKonferencija){
        return posterService.listAll().stream().filter(poster -> poster.getKonferencija().getIdKonferencija()
                        .equals(idKonferencija)).map(PosterGetMapper::toDTO).toList();
    }
    @PostMapping("")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
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
            poster.setFilePath(posterDTO.decodeBase64String(posterDTO.getFilePath()));
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
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public Poster deletePoster(@PathVariable("imePostera") String imePostera){
        return posterService.deletePoster(imePostera);
    }
    @DeleteMapping("/idKonferencija/{idKonferencija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
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
    @PutMapping("")
    //@Secured({"ROLE_SUPERUSER", "ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Object> vote(@RequestBody PosterVoteDTO posterVoteDTO) {
        Optional<Konferencija> existingKonf = konferencijeService.findById(posterVoteDTO.getIdKonferencija());
        Optional<Korisnik> existingKorisnik = korisnikService.findByEmail(posterVoteDTO.getEmail());
        Optional<Poster> existingPoster = posterService.findByImePoster(posterVoteDTO.getImePostera());
        boolean success = existingKorisnik.isPresent()&&existingPoster.isPresent()&&existingKonf.isPresent();
        if (success) {
            if (existingKonf.get().getDatumVrijemeZavrsetka().minusDays(2).isBefore(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voting is closed as the conference has ended.");
            }
            else if(existingKorisnik.get().isVoted()){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User has already voted.");
            }
            Poster poster = existingPoster.get();
            poster.vote();
            posterService.save(poster);
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
