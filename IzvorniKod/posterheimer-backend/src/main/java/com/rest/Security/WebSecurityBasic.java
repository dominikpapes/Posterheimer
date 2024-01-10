package com.rest.Security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = false)
public class WebSecurityBasic {
  private final AuthenticationConfiguration authenticationConfiguration;

  public WebSecurityBasic(AuthenticationConfiguration authenticationConfiguration) {
    this.authenticationConfiguration = authenticationConfiguration;
  }
  /*
   * @Bean
   * 
   * @Profile("basic-security")
   * 
   * @Order(Ordered.HIGHEST_PRECEDENCE)
   * public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
   * http.authorizeHttpRequests(authorize -> authorize
   * .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).
   * permitAll()
   * .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
   * .anyRequest().authenticated());
   * http.formLogin(withDefaults());
   * http.httpBasic(withDefaults());
   * http.csrf(AbstractHttpConfigurer::disable);
   * http.headers((headers) ->
   * headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
   * return http.build();
   * }
   */

  @Bean
  @Profile("form-security")
  public SecurityFilterChain spaFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authz -> authz
            .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/konferencije")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/verify")).permitAll()
            .anyRequest().authenticated())
        .exceptionHandling(e -> e
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .csrf(AbstractHttpConfigurer::disable)
        .headers(headers -> headers
            .frameOptions().sameOrigin())
        .sessionManagement(
            sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(
            new JwtTokenFilter(
                "0tt0RZKDc6o3uNQp5q9JOA2T_A60mye3wA9v4jklLARgtLfuD9VsjgsgWDr8Ltx-MH0Biq-2GDbD-T5I-_5alw"),
            UsernamePasswordAuthenticationFilter.class);
    ;

    http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  @Profile({ "basic-security", "form-security" })
  public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers(PathRequest.toH2Console()).permitAll()
        .anyRequest().authenticated());
    http.securityMatcher(PathRequest.toH2Console());
    http.csrf(AbstractHttpConfigurer::disable);
    http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
    return http.build();
  }

}
