package DH.ClinicaOdontologica.controller;


import DH.ClinicaOdontologica.model.Odontologo;
import DH.ClinicaOdontologica.model.Paciente;
import DH.ClinicaOdontologica.model.Turno;
import DH.ClinicaOdontologica.service.OdontologoService;
import DH.ClinicaOdontologica.service.PacienteService;
import DH.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService= new TurnoService();
        pacienteService= new PacienteService();
        odontologoService= new OdontologoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        Paciente pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarPorID(turno.getOdontologo().getId());
        if(pacienteBuscado!=null&&odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTurno(@PathVariable Integer id, @RequestBody Turno turnoActualizado) {
        Turno turnoExistente = turnoService.buscarPorId(id);
        if (turnoExistente != null) {
            turnoActualizado.setId(id);  // Asegurar que el ID no cambie
            turnoService.actualizarTurno(turnoActualizado);
            return ResponseEntity.ok("Turno actualizado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turno no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping ("/{id}")
    public ResponseEntity <Turno> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    @DeleteMapping
    public ResponseEntity<Turno> eliminarTurno(@RequestBody Turno turno) {
        Turno turnoBuscado = turnoService.buscarPorId(turno.getId());
        if (turnoBuscado != null) {
            turnoService.eliminarTurno(turno);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
