package com.service;

import com.domain.Konferencija;

import java.util.List;
import java.util.Optional;

public interface KonferencijeService {
    List<Konferencija> listAll();

    Konferencija fetch(Integer idKonferencija);

    Konferencija createKonferencija(Konferencija konferencija);

    Konferencija updateKonferencija(Konferencija konferencija, Integer id);

    Konferencija deleteKonferencija(Integer idKonferencija);

    Optional<Konferencija> findById(Integer idKonferencija);
}
