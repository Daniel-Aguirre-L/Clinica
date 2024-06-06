package DH.ClinicaOdontologica.controller;


import DH.ClinicaOdontologica.model.Paciente;
import DH.ClinicaOdontologica.service.PacienteService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService= new PacienteService();
    }
    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
    @GetMapping ("/{id}")
    public Paciente buscarPacientePorId(@PathVariable Integer id){
        return pacienteService.buscarPorID(id);
    }

    @GetMapping ()
    public List<Paciente> listarPacientes(){
        return pacienteService.buscarTodos();
    }

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){

        Paciente pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.actualizarPaciente(paciente);
            return "paciente actualizado con exito";
        }else{
            return "paciente no encontrado";
        }

    }
    @DeleteMapping("/{id}")
    public String eliminarPaciente (@PathVariable Integer id) {
        Paciente pacienteBuscado = pacienteService.buscarPorID(id);
        if(pacienteBuscado!=null){
            pacienteService.eliminarPaciente(pacienteBuscado);
            return "paciente eliminado con exito";
        }else{
            return "paciente no encontrado";
        }

    }

}