package DH.ClinicaOdontologica.dao;

import DH.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOList implements iDao<Odontologo>{
    private static final Logger logger = Logger.getLogger(OdontologoDAOList.class);

    private List<Odontologo> listaOdontologos;

    public OdontologoDAOList(){
        logger.info("Lista creada con exito!");
        listaOdontologos = new ArrayList<>();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        listaOdontologos.add(odontologo);
        logger.info("Se inserto correctamente el odontologo en la lista");

        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Odontologo odontologo) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Devolviendo lista");
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        return null;
    }
}