package com.mapper.FotografijaMappers;

import com.domain.Fotografija;
import com.dto.FotografijaDTOs.FotografijaPostDTO;

public class FotografijaPostMapper {
    public static Fotografija toEntity(FotografijaPostDTO dto) {
        Fotografija fotografija = new Fotografija();
        fotografija.setIdFotografija(dto.getIdFotografija());
        fotografija.setFilePath(dto.decodeBase64String(dto.getFilePath()));
        return fotografija;
    }
}
