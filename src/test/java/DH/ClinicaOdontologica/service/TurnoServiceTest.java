package DH.ClinicaOdontologica.service;

import DH.ClinicaOdontologica.entity.Domicilio;
import DH.ClinicaOdontologica.entity.Odontologo;
import DH.ClinicaOdontologica.entity.Paciente;
import DH.ClinicaOdontologica.entity.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarTurno(){
        Odontologo odontologo= new Odontologo("310A","Zabdiel","Silvestre");
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);
        Paciente paciente= new Paciente("Jorgito","pereyra","11111", LocalDate.of(2024,6,20),new Domicilio("calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com");
        Paciente pacienteGuardado= pacienteService.guardarPaciente(paciente);
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.of(2024, 7, 20));
        Turno turnoGuardado = turnoService.guardarTurno(turno);

        assertEquals(1L, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId() {
        Long id = 1L;
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno() {
        Long id = 1L;
        Turno turno = turnoService.buscarPorId(id).orElseThrow();


        turno.setFecha(LocalDate.of(2024, 8, 20));
        turnoService.actualizarTurno(turno);

        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        assertEquals(LocalDate.of(2024, 8, 20), turnoBuscado.get().getFecha());
    }

    @Test
    @Order(4)
    public void listarTodos() {
        List<Turno> listaTurnos = turnoService.listarTodos();
        assertEquals(1, listaTurnos.size());
    }

    @Test
    @Order(5)
    public void eliminarTurno() {
        turnoService.eliminarTurno(1L);
        Optional<Turno> turnoEliminado = turnoService.buscarPorId(1L);
        assertFalse(turnoEliminado.isPresent());
    }
}