package DH.ClinicaOdontologica.security;


import DH.ClinicaOdontologica.entity.*;
import DH.ClinicaOdontologica.repository.OdontologoRepository;
import DH.ClinicaOdontologica.repository.PacienteRepository;
import DH.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado=  passwordEncoder.encode(passSinCifrar);
        Usuario usuario= new Usuario("jorgito","jpereryradh","user@user.com",passCifrado, UsuarioRole.ROLE_USER);
        Usuario usuario2= new Usuario("daniel","agathion","admin@admin.com",passCifrado, UsuarioRole.ROLE_ADMIN);

        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);

    }
}
