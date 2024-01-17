package com.service;

import com.domain.Konferencija;

import java.util.List;
import java.util.Optional;

public interface KonferencijeService {
    //ovo je samo interface sa svim metodama u JPA je sve detaljno
    List<Konferencija> listAll();

    Konferencija fetch(Integer idKonferencija);

    Konferencija createKonferencija(Konferencija konferencija);

    Konferencija updateKonferencija(Konferencija konferencija, Integer id);

    Konferencija deleteKonferencija(Integer idKonferencija);

    Optional<Konferencija> findById(Integer idKonferencija);

    void save(Konferencija konferencija);
}
