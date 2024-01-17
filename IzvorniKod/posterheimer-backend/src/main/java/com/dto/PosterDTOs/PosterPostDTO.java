package com.dto.PosterDTOs;

import com.service.RequestDeniedException;

import java.util.Base64;

public class PosterPostDTO {
        private String imePoster;
        private String imeAutor;
        private String prezimeAutor;
        private String filePath;
        private Integer idKonferencija;
        private String posterEmail;
        public PosterPostDTO(){}
        public PosterPostDTO(String imePoster,String imeAutor,String prezimeAutor,String posterEmail, String filePath,Integer idKonferencija){
            this.filePath=filePath;
            this.imePoster=imePoster;
            this.imeAutor=imeAutor;
            this.prezimeAutor=prezimeAutor;
            this.idKonferencija=idKonferencija;
            this.posterEmail=posterEmail;
        }

    public String getPosterEmail() {
        return posterEmail;
    }

    public void setPosterEmail(String posterEmail) {
        this.posterEmail = posterEmail;
    }

    public Integer getIdKonferencija() {
        return idKonferencija;
    }

    public void setIdKonferencija(Integer idKonferencije) {
        this.idKonferencija = idKonferencije;
    }

    public String getFilePath() {
            return filePath;
        }

    public String getImeAutor() {
            return imeAutor;
        }

    public String getImePoster() {
            return imePoster;
        }

    public String getPrezimeAutor() {
            return prezimeAutor;
        }

    public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    public void setImeAutor(String imeAutor) {
            this.imeAutor = imeAutor;
        }

    public void setImePoster(String imePoster) {
            this.imePoster = imePoster;
        }

    public void setPrezimeAutor(String prezimeAutor) {
            this.prezimeAutor = prezimeAutor;
        }

    public byte[] decodeBase64String(String filePath) {
        if (filePath.startsWith("JVBERi0")) {
            return Base64.getDecoder().decode(filePath);
        }
        else {
            throw new RequestDeniedException("File type not valid!");
        }
    }
}
