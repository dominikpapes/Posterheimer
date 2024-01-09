package com.rest.EmailAutomation;

import com.dao.KonferencijaRepository;
import com.dao.KorisnikRepository;
import com.dao.PosterRepository;
import com.domain.Konferencija;
import com.domain.Korisnik;
import com.domain.Poster;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmailService {
    private JavaMailSender emailSender;
    private final KonferencijaRepository konferencijaRepository;
    private final KorisnikRepository korisnikRepository;
    private final PosterRepository posterRepository;


    public EmailService(JavaMailSender emailSender,
                        KonferencijaRepository konferencijaRepository,
                        KorisnikRepository korisnikRepository,
                        PosterRepository posterRepository) {
        this.emailSender = emailSender;
        this.konferencijaRepository = konferencijaRepository;
        this.korisnikRepository = korisnikRepository;
        this.posterRepository = posterRepository;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendReminder(Konferencija conf) {
        AtomicInteger i = new AtomicInteger();
        String bodyObavijest = "Poštovani,\n Pozivamo vas na dodijelu nagrada za tri najbolja postera na konferenciji " + conf.getImeKonferencija()
                + ". Dodijela se odrzava na zadnji dan konferencije: " + conf.getDatumVrijemeZavrsetka().getDayOfMonth()+"."
                +conf.getDatumVrijemeZavrsetka().getMonthValue()+"."+conf.getDatumVrijemeZavrsetka().getYear()+"."+" u "
                +conf.getDatumVrijemeZavrsetka().plusHours(17).getHour()+" sati" + ".\n Srdačno";
        List<Korisnik> korisnici = korisnikRepository.findAll().stream().filter(korisnik -> Objects.equals(korisnik.getKonferencijaId(), conf.getIdKonferencija())).toList();
        for (Korisnik k : korisnici) {
            sendSimpleEmail(k.getEmail(), "Ceremonija dodjele nagrada", bodyObavijest);
        }
        List<Poster> posters = posterRepository.findAll().stream()
                .filter(poster -> poster.getKonferencija().getIdKonferencija().equals(conf.getIdKonferencija())).toList()
                .stream().sorted((p1, p2) -> Integer.compare(p2.getBrGlasova(), p1.getBrGlasova())).limit(3).toList();
        posters.forEach(p -> {
            String bodyPozivnice = "Poštovani,\n Pozivamo vas na svečanu dodjelu nagrada na konferenciji " + conf.getImeKonferencija() + " za ostvareno " + i.getAndIncrement()
                    + " mjesto. Radujemo se vašem dolasku.\n Srdačno";
            sendSimpleEmail(p.getPosterEmail(), "Pozivnica na ddodjelu nagrada", bodyPozivnice);
        });

    }
}

