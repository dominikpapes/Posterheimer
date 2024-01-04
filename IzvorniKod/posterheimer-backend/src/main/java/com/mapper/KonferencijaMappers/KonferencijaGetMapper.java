package com.mapper.KonferencijaMappers;

import com.domain.Konferencija;
import com.dto.KonferencijaDTOs.KonferencijaGetDTO;

public class KonferencijaGetMapper {
    public static KonferencijaGetDTO toDTO(Konferencija konferencija) {
        return new KonferencijaGetDTO(
                konferencija.getIdKonferencija(),
                konferencija.getImeKonferencija()
        );
    }
}
