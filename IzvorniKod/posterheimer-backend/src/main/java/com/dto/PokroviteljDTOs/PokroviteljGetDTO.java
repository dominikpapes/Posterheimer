package com.dto.PokroviteljDTOs;

public class PokroviteljGetDTO {
    private String imePokrovitelja;

    public PokroviteljGetDTO() {
    }
    public PokroviteljGetDTO(String imePokrovitelja) {
        this.imePokrovitelja = imePokrovitelja;
    }

    public String getImePokrovitelja() {
        return imePokrovitelja;
    }
    public void setImePokrovitelja(String imePokrovitelja) {
        this.imePokrovitelja = imePokrovitelja;
    }
}
