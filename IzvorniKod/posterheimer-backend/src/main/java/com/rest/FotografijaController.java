package com.rest;

import com.domain.Fotografija;
import com.domain.Konferencija;
import com.dto.FotografijaDTOs.FotografijaGetDTO;
import com.dto.FotografijaDTOs.FotografijaPostDTO;
import com.mapper.FotografijaMappers.FotografijaGetMapper;
import com.service.FotografijaService;
import com.service.KonferencijeService;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fotografije")
public class FotografijaController {
    @Autowired
    private FotografijaService fotografijaService;
    @Autowired
    private KonferencijeService konferencijeService;

    @GetMapping("/idKonferencija/{idKonferencija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER"})
    public List<FotografijaGetDTO> fotografije(@PathVariable("idKonferencija") Integer idKonferencija) {
        return fotografijaService.listAll().stream().filter(fotografija -> fotografija.getKonferencija().getIdKonferencija()
                .equals(idKonferencija)).map(FotografijaGetMapper::toDTO).toList();
    }

    @GetMapping("/id/{idFotografija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN","ROLE_USER"})
    public FotografijaGetDTO getFotografija(@PathVariable("idFotografija") Integer idFotografija) {
        Fotografija fotografija = fotografijaService.fetch(idFotografija);
        return FotografijaGetMapper.toDTO(fotografija);
    }

    @PostMapping("")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN")
    public ResponseEntity<Fotografija> createFotografija(@RequestBody FotografijaPostDTO fotografijaDTO) {
        Optional<Fotografija> existingFotografija = fotografijaService.findByIdFotografija(fotografijaDTO.getIdFotografija());
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(fotografijaDTO.getIdKonferencija());
        if(existingFotografija.isPresent()){
            if (existingFotografija.get().getKonferencija().getIdKonferencija().equals(fotografijaDTO.getIdKonferencija())) {
                throw new RequestDeniedException("Fotografija with id: " + fotografijaDTO.getIdFotografija() + " already exists!");
            }
        }
        if(existingKonferencija.isPresent()){
            Fotografija fotografija = new Fotografija();
            fotografija.setKonferencija(existingKonferencija.get());
            fotografija.setIdFotografija(fotografijaDTO.getIdFotografija());
            fotografija.setFilePath(fotografijaDTO.decodeBase64String(fotografijaDTO.getFilePath()));
            Fotografija saved = fotografijaService.createFotografija(fotografija);
            existingKonferencija.get().setFotografije(saved);
            return ResponseEntity.created(URI.create("/fotografije/" + saved.getIdFotografija())).body(saved);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id/{idFotografija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public Fotografija deleteFotografija(@PathVariable("idFotografija") Integer idFotografija){
        return fotografijaService.deleteFotografija(idFotografija);
    }

    @DeleteMapping("/idKonferencija/{idKonferencija}")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN"})
    public ResponseEntity<Object> deleteFotografijaByKonferencija(@PathVariable("idKonferencija") Integer idKonferencija){
        Optional<Konferencija> existingKonferencija = konferencijeService.findById(idKonferencija);
        if(existingKonferencija.isPresent()) {
            List<Fotografija> list = fotografijaService.listAll().stream()
                    .filter(fotografija -> fotografija.getKonferencija().getIdKonferencija().equals(idKonferencija)).toList();
            for (Fotografija fotografija : list) {
                fotografijaService.deleteFotografija(fotografija.getIdFotografija());
            }
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
