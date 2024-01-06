package com.mapper.FotografijaMappers;

import com.domain.Fotografija;
import com.dto.FotografijaDTOs.FotografijaGetDTO;

public class FotografijaGetMapper {
    public static FotografijaGetDTO toDTO(Fotografija fotografija){
        return new FotografijaGetDTO(fotografija.getIdFotografija(), fotografija.getFilePath());
    }

}
