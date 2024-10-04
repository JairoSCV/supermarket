package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.app.security.filter.JWTokenValidator;
import com.app.security.service.IUsuarioService;
import com.app.security.util.JwtUtils;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers(HttpMethod.POST,"/auth/**").permitAll();

                    //Controlador Productos
                    auth.requestMatchers(HttpMethod.POST,"/productos/save").hasAnyRole("ADMIN","USER");
                    auth.requestMatchers(HttpMethod.GET,"/productos/findAll").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/productos/findById/{id}").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/productos/categoria/{id}").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE,"/productos/delete/{id}").hasAnyRole("ADMIN","USER");



                    auth.requestMatchers(HttpMethod.POST,"/auth/save").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.GET,"/swagger-ui/index.html").hasRole("ADMIN");
                    auth.anyRequest().denyAll();
                }
            )
            .addFilterBefore(new JWTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(IUsuarioService usuarioService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //Cifrado de contraseñas
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //    public static void main(String[] args) {
    //        System.out.println(new BCryptPasswordEncoder().encode("123")); 
    //    }
}
