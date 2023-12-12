package com;

import com.domain.Konferencija;
import com.domain.Korisnik;
import com.rest.KorisnikController;
import com.service.KonferencijeService;
import com.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Component
public class DataInitializerTest {
        @Autowired
        private PasswordEncoder encoder;
        @Autowired
        private KorisnikService korisnikService;
        @Autowired
        private KonferencijeService konferencijeService;

        @Value("${opp.test.korisnik.emails}")
        private String testEmails;

        @Value("${opp.test.korisnik.passwords}")
        private String testPasswords;

        @Value("${opp.test.konferencija.password}")
        private String testKonfPassword;
    @Value("${opp.test.konferencija.ime}")
    private String testKonfIme;
    @Value("${opp.test.konferencija.id}")
    private String testKonfId;
    @Value("${opp.test.konferencija.username}")
    private String testKonfUsername;
    @Value("${opp.test.konferencija.mjesto}")
    private String testKonfMjesto;

        @EventListener
        public void appReady(ApplicationReadyEvent event) {
            LocalDateTime startDate = LocalDateTime.of(2023, 11, 1, 10, 0);
            LocalDateTime endDate = LocalDateTime.of(2023, 11, 3, 18, 30);
            Konferencija konferencija=new Konferencija(Integer.parseInt(testKonfId),testKonfIme,testKonfMjesto,startDate,endDate,testKonfPassword,testKonfUsername);
            konferencijeService.createKonferencija(konferencija);
            String[] emails = testEmails.split(",");
            String[] passwords=testPasswords.split(",");
            for (int i = 0; i < emails.length; i++) {
                korisnikService.createKorisnik(makeKorisnik(emails[i], encoder.encode(passwords[i]),konferencija));
            }
        }
    private Korisnik makeKorisnik(String email, String pass, Konferencija konf) {
        Korisnik korisnik = new Korisnik();
        korisnik.setEmail(email);
        korisnik.setLozinka(pass);
        korisnik.setAdmin(email.equals("admin@gmail.com"));
        korisnik.setKonferencija(konf);
        return korisnik;
    }
}
