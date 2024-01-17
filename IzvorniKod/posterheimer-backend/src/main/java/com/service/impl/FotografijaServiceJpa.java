package com.service.impl;

import com.dao.FotografijaRepository;
import com.domain.Fotografija;
import com.service.EntityMissingException;
import com.service.FotografijaService;
import com.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class FotografijaServiceJpa implements FotografijaService {

    @Autowired
    private FotografijaRepository fotografijaRepository;

    @Override
    public List<Fotografija> listAll() { return fotografijaRepository.findAll(); }

    @Override
    public Fotografija fetch(Integer idFotografija) {
        return fotografijaRepository.findByIdFotografija(idFotografija).orElseThrow(
                () -> new EntityMissingException(Fotografija.class, idFotografija));
    }


    @Override
    @Transactional
    public Fotografija createFotografija(Fotografija fotografija) {
        validate(fotografija);
        Assert.notNull(fotografija, "Fotografija must not be null!");
        if (fotografijaRepository.countByIdFotografija(fotografija.getIdFotografija()) > 0)
            throw new RequestDeniedException(
                    "Fotografija with Id " + fotografija.getIdFotografija() + " already exists!"
            );
        return fotografijaRepository.save(fotografija);
    }

    @Override
    public Fotografija updateFotografija(Fotografija fotografija, Integer newIdFotografija) {
        validate(fotografija);
        Assert.isTrue(newIdFotografija != null, "Id fotografije must be given");
        if (!fotografijaRepository.existsByIdFotografija(fotografija.getIdFotografija()))
            throw new EntityMissingException(Fotografija.class, fotografija.getIdFotografija());
        if (fotografijaRepository.existsByIdFotografija(newIdFotografija))
            throw new RequestDeniedException(
                    "Fotografija with id " + newIdFotografija + " already exists!"
            );
        fotografijaRepository.delete(fotografija);
        fotografija.setIdFotografija(newIdFotografija);
        return fotografijaRepository.save(fotografija);
    }

    @Override
    @Transactional
    public Fotografija deleteFotografija(Integer idFotografija) {
        Fotografija fotografija = fetch(idFotografija);
        fotografijaRepository.delete(fotografija);
        return fotografija;
    }

    @Override
    public Optional<Fotografija> findByIdFotografija(Integer idFotografija) {
        return fotografijaRepository.findByIdFotografija(idFotografija);
    }

    private void validate(Fotografija fotografija) {
        //Assert.isTrue(fotografija.getIdFotografija() != null, "Id fotografije must be given");
        Assert.isTrue(fotografija.getFilePath() != null,
                "File Path must be given!");
    }

}
