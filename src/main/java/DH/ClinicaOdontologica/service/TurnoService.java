package DH.ClinicaOdontologica.service;


import DH.ClinicaOdontologica.dao.TurnoDAOLISTA;
import DH.ClinicaOdontologica.dao.iDao;
import DH.ClinicaOdontologica.model.Turno;

import java.util.List;

public class TurnoService {
    private iDao<Turno> turnoiDao;

    public TurnoService() {
        turnoiDao= new TurnoDAOLISTA();
    }
    public Turno guardarTurno(Turno turno){
        return turnoiDao.guardar(turno);
    }
    public List<Turno> buscarTodos(){
        return turnoiDao.buscarTodos();
    }
    public Turno buscarPorId(Integer id){
        return turnoiDao.buscarPorId(id);
    }
    public void eliminarTurno (Turno turno){
          turnoiDao.eliminar(turno);
    }
    public void  actualizarTurno (Turno turno){
        turnoiDao.actualizar(turno);
    }
}
