package com.mapper.PosterMapper;

import com.domain.Korisnik;
import com.domain.Poster;
import com.dto.KorisnikDTOs.KorisnikPostDTO;
import com.dto.PosterDTOs.PosterPostDTO;

public class PosterPostMapper {
    public static Poster toEntity(PosterPostDTO dto) {
        Poster poster=new Poster();
        poster.setBrGlasova(0);
        poster.setImePoster(dto.getImePoster());
        poster.setPosterEmail(dto.getPosterEmail());
        poster.setFilePath(dto.getFilePath());
        poster.setImeAutor(dto.getImeAutor());
        poster.setPrezimeAutor(dto.getPrezimeAutor());
        return poster;
    }
}
