


package com.rest.EmailAutomation;


import com.dao.KonferencijaRepository;
import com.domain.Konferencija;
import com.rest.EmailAutomation.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private KonferencijaRepository konferencijaRepository;

    @Autowired
    private EmailService emailService;


    @Scheduled(cron = "0 0 0 * * *")
    public void sendConferenceReminders() {
        LocalDateTime twoDaysBefore = LocalDateTime.now().plusDays(2);
        List<Konferencija> upcomingConferences = konferencijaRepository.findAll().stream()
                .filter(konferencija -> konferencija.getDatumVrijemeZavrsetka().isBefore(twoDaysBefore)).toList();

        for (Konferencija conf : upcomingConferences) {
            if (!conf.isVotingReminderSent()) {
                emailService.sendReminder(conf);
                conf.setVotingReminderSent(true);
                konferencijaRepository.save(conf);
            }
        }
    }
}