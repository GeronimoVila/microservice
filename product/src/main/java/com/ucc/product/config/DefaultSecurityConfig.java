package com.ucc.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration

public class DefaultSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //CONFIGURAR LAS REGLAS DE SEGURIDAD PARA LAS SOLICITUDES HTTP
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(aurhz -> aurhz
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll() //PERMITIR TODAS LAS SOLICITUDES GET
                        .requestMatchers(HttpMethod.POST, "/api/products/**").authenticated() // PERMITIR SOLO LOS AUTENTICADOS A LAS SOLICITUDES
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").authenticated()
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults()); //USAR AUTENTICACION BASICA PARA LAS SOLICITUDES

        return http.build();
    }

    //ESTABLECER DATOS CORRECTOS DE LOGIN
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("1234"))
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    //ENCRIPTAR LA CONTRASEÑA
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
