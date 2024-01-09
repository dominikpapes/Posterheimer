package com.rest.Security;

import com.dao.KorisnikRepository;
import com.domain.Korisnik;
import com.rest.KorisnikController;
import com.rest.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private KorisnikRepository korisnikRepository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            final String jwt = jwtUtil.generateToken(authentication);
            String role = authentication.getAuthorities().toString();
            Optional<Korisnik> korisnik=korisnikRepository.findByEmail(loginRequest.getUsername());
<<<<<<< HEAD
            if(korisnik.isEmpty()){
                throw new Exception();
            }
=======

>>>>>>> develop
            return ResponseEntity.ok(new AuthenticationResponse(jwt, role,korisnik.get().getIme(),korisnik.get().getPrezime()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }
}
