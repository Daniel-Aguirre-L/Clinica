package DH.ClinicaOdontologica.dao;


import DH.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;



public class TurnoDAOLISTA implements iDao<Turno>{
    private Logger logger= Logger.getLogger(TurnoDAOLISTA.class);
    private List<Turno> turnos= new ArrayList<>();
    private int siguienteId = 1;

    @Override
    public Turno guardar(Turno turno) {
        logger.info("iniciando las operaciones de guardado de un turno");
        PacienteDAOH2 pacienteDAOH2= new PacienteDAOH2();
        OdontologoDAOH2 odontologoDAOH2= new OdontologoDAOH2();
        turno.setPaciente(pacienteDAOH2.buscarPorId(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDAOH2.buscarPorId(turno.getOdontologo().getId()));
        turno.setId(siguienteId); // Asigna el siguiente ID al turno
        turnos.add(turno);
        siguienteId++; // Incrementa el contador para el siguiente ID único
        logger.info("turno guardado con exito");
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        for (Turno turno : turnos) {
            if(turno.getId().equals(id)){
                return turno;
            }else{
                System.out.println("turno no encontrado");
            }

        }
        return null;
    }

    @Override
    public void eliminar(Turno turnoAEliminar) {
        boolean removed = turnos.removeIf(turno -> turno.getId().equals(turnoAEliminar.getId()));
        if (removed) {
            logger.info("Turno eliminado con éxito");
        } else {
            logger.info("Turno no encontrado para eliminar");
        }
    }

    @Override
    public void actualizar(Turno turnoActualizado) {
        for (int i = 0; i < turnos.size(); i++) {
            Turno turno = turnos.get(i);
            if (turno.getId().equals(turnoActualizado.getId())) {
                turno.setPaciente(turnoActualizado.getPaciente());
                turno.setOdontologo(turnoActualizado.getOdontologo());
                turno.setFecha(turnoActualizado.getFecha());
                logger.info("Turno actualizado con éxito");
                return;
            }
        }
        logger.info("Turno no encontrado para actualizar");
    }

    @Override
    public List<Turno> buscarTodos() {
        logger.info("iniciando las operacion de mostrar todos los turnos");
        return turnos;
    }

    @Override
    public Turno buscarPorString(String string) {
        return null;
    }
}
