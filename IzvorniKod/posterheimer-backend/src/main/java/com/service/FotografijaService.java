package com.service;

import com.domain.Fotografija;

import java.util.List;
import java.util.Optional;

public interface FotografijaService {

    List<Fotografija> listAll();

    Fotografija fetch(Integer idFotografija);

    Fotografija createFotografija(Fotografija fotografija);

    Fotografija updateFotografija(Fotografija fotografija, Integer newIdFotografija);

    Fotografija deleteFotografija(Integer idFotografija);

    Optional<Fotografija> findByIdFotografija(Integer idFotografija);

}
