package com.mapper.PokroviteljMappers;

import com.domain.Pokrovitelj;
import com.dto.PokroviteljDTOs.PokroviteljGetDTO;

public class PokroviteljGetMapper {
    public static PokroviteljGetDTO toDTO(Pokrovitelj pokrovitelj) {
        return new PokroviteljGetDTO(
                pokrovitelj.getImePokrovitelja());
    }

}
