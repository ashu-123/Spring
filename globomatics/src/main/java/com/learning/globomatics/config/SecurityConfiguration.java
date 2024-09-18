package com.learning.globomatics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    JwtWebSecurityConfigurer
//        .forRS256(apiAudience, issuer)
//        .configure(http)
//        .authorizeRequests()
//        .antMatchers(HttpMethod.POST, "/api/v1/bikes").permitAll()
//        .antMatchers(HttpMethod.GET, "/api/v1/bikes").hasAuthority("view:registrations")
//        .antMatchers(HttpMethod.GET, "/api/v1/bikes/**").hasAuthority("view:registration")
//        .anyRequest().authenticated();
//	}4
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(c -> c
                        .requestMatchers(HttpMethod.POST, "/api/v1/bikes").permitAll()// .hasAuthority("SCOPE_read:posts")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bikes").hasAuthority("view:registrations")// access("#oauth2.hasScope('write:posts')")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bikes/**").hasAuthority("view:registration")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                .build();
    }
	
	

}