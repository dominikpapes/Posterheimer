package com.mapper.PosterMapper;

import com.domain.Poster;
import com.dto.PosterDTOs.PosterGetDTO;

public class PosterGetMapper {
    public static PosterGetDTO toDTO(Poster poster){
        return new PosterGetDTO(poster.getImePoster(),poster.getImeAutor(),poster.getPrezimeAutor(),poster.getFilePath());
    }
}
