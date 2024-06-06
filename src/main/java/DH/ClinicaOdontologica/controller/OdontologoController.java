package DH.ClinicaOdontologica.controller;

import DH.ClinicaOdontologica.model.Odontologo;
import DH.ClinicaOdontologica.service.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService = new OdontologoService();
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorId(@PathVariable Integer id) {
        return odontologoService.buscarPorID(id);
    }

    @GetMapping()
    public List<Odontologo> listarOdontologos() {
        return odontologoService.buscarTodos();
    }

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo) {
        return odontologoService.guardarOdontologo(odontologo);
    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo) {

        Odontologo odontologoBuscado = odontologoService.buscarPorID(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizarOdontologo(odontologo);
            return "odontologo actualizado con exito";
        } else {
            return "odontologo no encontrado";
        }

    }

    @DeleteMapping("/{id}")
    public String eliminarOdontologo(@PathVariable Integer id) {
        Odontologo odontologoBuscado = odontologoService.buscarPorID(id);
        if (odontologoBuscado != null) {
            odontologoService.eliminarOdontologo(odontologoBuscado);
            return "odontologo eliminado con exito";
        } else {
            return "odontologo no encontrado";
        }
    }
}