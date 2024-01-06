package com.mapper.PokroviteljMappers;

import com.domain.Pokrovitelj;
import com.dto.PokroviteljDTOs.PokroviteljPostDTO;

public class PokroviteljPostMapper {
    public static Pokrovitelj toEntity(PokroviteljPostDTO dto) {
        Pokrovitelj pokrovitelj = new Pokrovitelj();
        pokrovitelj.setImePokrovitelja(dto.getImePokrovitelja());
        pokrovitelj.setPromotivniMaterijal(dto.decodeBase64String(dto.getPromotivniMaterijal()));
        pokrovitelj.setUrlPromo(dto.getUrlPromo());
        return pokrovitelj;
    }
}
