package DH.ClinicaOdontologica.controller;



import DH.ClinicaOdontologica.entity.Odontologo;
import DH.ClinicaOdontologica.entity.Turno;
import DH.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(turno.getId());
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("turno actualizado con exito");
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turno no encontrado");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

}