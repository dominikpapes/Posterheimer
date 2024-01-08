package com.rest;

import com.dto.CaptchaVerificationRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CaptchaController {

    private static final String SITE_SECRET="6LesATwpAAAAACC1q8tK6kLSTdySLyV8O0znHMVm";

    @PostMapping("/verify")
    //@Secured({"ROLE_SUPERUSER","ROLE_ADMIN", "ROLE_USER", "ROLE_VISITOR"})
    public String verify(@RequestBody CaptchaVerificationRequest request) {
        String captchaValue = request.getCaptchaValue();
        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + SITE_SECRET + "&response=" + captchaValue;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, null, String.class);
    }
}