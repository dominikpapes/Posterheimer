package com.service;

import com.domain.Konferencija;
import com.domain.Pokrovitelj;
import java.util.List;
import java.util.Optional;

public interface PokroviteljService {

    List<Pokrovitelj> listAll();

    Pokrovitelj fetch(Integer idPokrovitelj);

    Pokrovitelj createPokrovitelj(Pokrovitelj pokrovitelj);

    Pokrovitelj updatePokroviteljIme(Pokrovitelj pokrovitelj, String NewImePokrovitelja);

    Pokrovitelj deletePokrovitelj(Integer idPokrovitelj);

    Optional<Pokrovitelj> findByImePokrovitelj(String imePokrovitelja);

    Optional<Pokrovitelj> findByIdPokrovitelj(Integer idPokrovitelj);

    void save(Pokrovitelj pokrovitelj);

}
