package DH.ClinicaOdontologica.security;


import DH.ClinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //aca ya no funciona postman ni nada que no se autentique.
public class WebSecurityConfig {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //proveedor de autenticaciones DAO
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        //aca debe venir el usuario y desencriptador de claves
      DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
      provider.setUserDetailsService(usuarioService);
      provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }
    //aca deberiamos hablar de las autorizaciones
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz)-> authz
                        .requestMatchers("/odontologos_pages/post_odontologos.html","/odontologos_pages/get_odontologos.html",
                                "/pacientes_pages/post_pacientes.html","/pacientes_pages/get_pacientes.html")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());
        return http.build();

    }

}
