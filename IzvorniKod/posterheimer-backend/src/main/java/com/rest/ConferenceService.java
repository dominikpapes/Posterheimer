/*package com.rest;

import com.dao.KonferencijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ConferenceService {
    // Inject necessary repositories or other services
    @Autowired
    private KonferencijaRepository konferencijaRepository;

    @Autowired
    private EmailService emailService;

    // Other service methods...

    @Scheduled(cron = "0 0 * * * ?")
    public void sendConferenceReminders() {
        LocalDate twoDaysBefore = LocalDate.now().plusDays(2);
        List<Konferencija> upcomingConferences = konferencijaRepository.findConferencesEndingOn(twoDaysBefore);

        for (Konferencija conf : upcomingConferences) {
            if (!conf.isVotingReminderSent()) {
                emailService.sendReminder(conf);
                conf.setVotingReminderSent(true);
                konferencijaRepository.save(conf);
            }
        }
    }
}*/