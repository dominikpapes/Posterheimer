package com.mapper.KonferencijaMappers;

import com.domain.Konferencija;
import com.dto.KonferencijaDTOs.KonferencijaPostDTO;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class KonferencijaPostMapper {
    public static Konferencija toEntity(KonferencijaPostDTO dto) {
        Konferencija konferencija = new Konferencija();
        konferencija.setImeKonferencija(dto.getImeKonferencija());
        konferencija.setMjesto(dto.getMjesto());
        konferencija.setAdresa(dto.getAdresa());
        konferencija.setZipCode(dto.getZipCode());
        konferencija.setDatumVrijemePocetka(LocalDateTime.parse(dto.getDatumVrijemePocetka()));
        konferencija.setDatumVrijemeZavrsetka(LocalDateTime.parse(dto.getDatumVrijemeZavrsetka()));
        konferencija.setVideoUrl(dto.getVideoUrl());
        konferencija.setGenericPassword(dto.getGenericPassword());
        konferencija.setGenericUsername(dto.getGenericUsername());
        return konferencija;
    }
}
