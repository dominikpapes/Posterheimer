package com.service.impl;

import com.dao.KonferencijaRepository;
import com.domain.Konferencija;
import com.service.KonferencijeService;
import com.service.EntityMissingException;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class KonferencijeServiceJpa implements KonferencijeService {
    //odi se vecinom poziva repository konferencije
    @Autowired
    private KonferencijaRepository konferencijaRepository;

    @Override
    public List<Konferencija> listAll() {
        return konferencijaRepository.findAll();
    }

    //imena metoda govore manje vise sama za sebe
    @Override
        public Optional<Konferencija> findById(Integer konferencijaId) {
        return konferencijaRepository.findById(konferencijaId);
    }

    @Override
    public Konferencija fetch(Integer konferencijaId) {
        return findById(konferencijaId).orElseThrow(
                () -> new EntityMissingException(Konferencija.class, konferencijaId)
        );
    }

    @Override
    public Konferencija createKonferencija(Konferencija konferencija) {
        validate(konferencija);
        //gledamo unique uvjete da su ispunjeni
        if (konferencijaRepository.countByIdKonferencije(konferencija.getIdKonferencija()) > 0)
            throw new RequestDeniedException(
                    "Konferencija with id " + konferencija.getIdKonferencija() + " already exists"
            );
        else if (konferencijaRepository.countByImeKonferencija(konferencija.getImeKonferencija()) > 0)
            throw new RequestDeniedException(
                    "Konferencija with ime " + konferencija.getImeKonferencija() + " already exists"
            );
        return konferencijaRepository.save(konferencija);
    }

    @Override
    public Konferencija updateKonferencija(Konferencija konferencija, Integer id) {
        validate(konferencija);
        Integer konferencijId = konferencija.getIdKonferencija();
        if (!konferencijaRepository.existsById(konferencijId))
            throw new EntityMissingException(Konferencija.class, konferencijId);
        if (konferencijaRepository.existsById(konferencijId))
            throw new RequestDeniedException(
                    "Konferencija with id " + konferencija.getIdKonferencija() + " already exists"
            );
        else if (konferencijaRepository.existsByImeKonferencijaNot(konferencija.getImeKonferencija()))
            throw new RequestDeniedException(
                    "Konferencija with ime " + konferencija.getImeKonferencija() + " already exists"
            );
        return konferencijaRepository.save(konferencija);
    }

    @Override
    public Konferencija deleteKonferencija(Integer idKonferencija) {
        Konferencija konferencija = fetch(idKonferencija);
        konferencijaRepository.delete(konferencija);
        return konferencija;
    }
    //validiramo konferenciju da ima dobro definirane atribute
    private void validate(Konferencija konferencija) {
        Assert.notNull(konferencija, "Konferencija object must be given");
        Integer idKonferencija=konferencija.getIdKonferencija();
        Assert.isTrue(konferencija.getIdKonferencija() != null, "Id konferencija must be given");
        Assert.hasText(konferencija.getImeKonferencija(),"Ime konferencije must be given");
        Assert.hasText(konferencija.getMjesto(),"Mjesto konferencije must be given");
        Assert.isTrue(konferencija.getDatumVrijemePocetka() instanceof LocalDateTime,"Date time pocetka must be given");
        Assert.isTrue(konferencija.getDatumVrijemeZavrsetka() instanceof LocalDateTime,"Date time zavrsetka must be given");
    }
}
