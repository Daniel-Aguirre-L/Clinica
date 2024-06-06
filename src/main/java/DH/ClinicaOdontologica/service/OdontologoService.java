package DH.ClinicaOdontologica.service;


import DH.ClinicaOdontologica.dao.OdontologoDAOH2;
import DH.ClinicaOdontologica.dao.OdontologoDAOList;
import DH.ClinicaOdontologica.dao.iDao;
import DH.ClinicaOdontologica.model.Odontologo;


import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;
    private iDao<Odontologo> odontologoiDaoList;

    public OdontologoService() {
        odontologoiDao = new OdontologoDAOH2();
        odontologoiDaoList = new OdontologoDAOList();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){

        return odontologoiDao.guardar(odontologo);
    }

    public Odontologo buscarPorID(Integer id){
        return  odontologoiDao.buscarPorId(id);
    }

    public void actualizarOdontologo(Odontologo odontologo){
        odontologoiDao.actualizar(odontologo);
    }

    public List<Odontologo> buscarTodos(){

        return odontologoiDao.buscarTodos();
    }

    public void eliminarOdontologo (Odontologo odontologo) {
        odontologoiDao.eliminar(odontologo);
    }

    public Odontologo guardarOdontologoList(Odontologo odontologo){

        return odontologoiDaoList.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologosList(){

        return odontologoiDaoList.buscarTodos();
    }

}