package DH.ClinicaOdontologica.controller;




import DH.ClinicaOdontologica.entity.Odontologo;
import DH.ClinicaOdontologica.entity.Paciente;
import DH.ClinicaOdontologica.entity.Turno;
import DH.ClinicaOdontologica.exception.BadRequestException;
import DH.ClinicaOdontologica.exception.ResourceNotFoundException;
import DH.ClinicaOdontologica.service.OdontologoService;
import DH.ClinicaOdontologica.service.PacienteService;
import DH.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            throw new BadRequestException("Paciente o Odontólogo no encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
        if (turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado con éxito");
        } else {
            throw new ResourceNotFoundException("Turno con ID: " + turno.getId() + " no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con éxito");
        } else {
            throw new ResourceNotFoundException("Turno con ID: " + id + " no encontrado");
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno);
        } else {
            throw new ResourceNotFoundException("Turno con ID: " + id + " no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }
}