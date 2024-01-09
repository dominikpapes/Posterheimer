package com.mapper.PosterMapper;

import com.domain.Poster;
import com.dto.PosterDTOs.PosterGetWithVotesDTO;

public class PosterGetWithVotesMapper {
    public static PosterGetWithVotesDTO toDTO(Poster poster) {
        return new PosterGetWithVotesDTO(poster.getImePoster(), poster.getImeAutor(),
                                            poster.getPrezimeAutor(), poster.getBrGlasova());
    }

}
