package DH.ClinicaOdontologica.controller;


import DH.ClinicaOdontologica.entity.Odontologo;
import DH.ClinicaOdontologica.entity.Paciente;
import DH.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){

        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("paciente actualizado con exito");
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

}
