package com.rest.Security;

import com.domain.Korisnik;
import com.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
public class KorisnikUserDetailsService implements UserDetailsService {
    @Autowired
    private KorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Korisnik korisnik = korisnikService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user '" + username + "'"));
        String hashedPassword = korisnik.getLozinka();

        return new User(username, hashedPassword, authorities(username));
    }

    private List<GrantedAuthority> authorities(String email) {
        if ("posterheimer@gmail.com".equals(email))
            return commaSeparatedStringToAuthorityList("ROLE_SUPERUSER");

        Korisnik korisnik = korisnikService.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No user '" + email + "'")
        );

        if (korisnik.isAdmin()) {
            return commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        }

        if (korisnik.isVisitor()) {
            return commaSeparatedStringToAuthorityList("ROLE_VISITOR");
        }

        return commaSeparatedStringToAuthorityList("ROLE_USER");
    }
}
