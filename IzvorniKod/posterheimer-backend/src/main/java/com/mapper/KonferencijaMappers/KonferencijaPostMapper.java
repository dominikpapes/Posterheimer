package com.mapper.KonferencijaMappers;

import com.domain.Konferencija;
import com.dto.KonferencijaDTOs.KonferencijaPostDTO;

public class KonferencijaPostMapper {
    public static Konferencija toEntity(KonferencijaPostDTO dto) {
        Konferencija konferencija = new Konferencija();
        konferencija.setIdKonferencija(dto.getIdKonferencija());
        konferencija.setImeKonferencija(dto.getImeKonferencija());
        konferencija.setMjesto(dto.getMjesto());
        konferencija.setDatumVrijemePocetka(dto.getDatumVrijemePocetka());
        konferencija.setDatumVrijemeZavrsetka(dto.getDatumVrijemeZavrsetka());
        konferencija.setGenericPassword(dto.getGenericPassword());
        konferencija.setGenericUsername(dto.getGenericUsername());
        return konferencija;
    }
}
