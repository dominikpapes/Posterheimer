package com.service;

import com.domain.Konferencija;
import com.domain.Pokrovitelj;
import java.util.List;
import java.util.Optional;

public interface PokroviteljService {

    List<Pokrovitelj> listAll();

    Pokrovitelj fetch(String imePokrovitelja);

    Pokrovitelj createPokrovitelj(Pokrovitelj pokrovitelj);

    Pokrovitelj updatePokroviteljIme(Pokrovitelj pokrovitelj, String NewImePokrovitelja);

    Pokrovitelj deletePokrovitelj(String imePokrovitelja);

    Optional<Pokrovitelj> findByImePokrovitelj(String imePokrovitelja);

}
