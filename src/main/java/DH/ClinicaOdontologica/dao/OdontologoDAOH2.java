package DH.ClinicaOdontologica.dao;


import DH.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{

    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);

    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_BY_MATRICULA="SELECT * ODONTOLOGOS WHERE MATRICULA=?";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("iniciando las operaciones de guardado de :  "+odontologo.getMatricula());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();
            ResultSet clave= psInsert.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {

        logger.info("iniciando la operacion de buscado de un odontologo con id : "+id);
        Connection connection= null;
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSElectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSElectOne.setInt(1,id);
            ResultSet rs= psSElectOne.executeQuery();
            while(rs.next()){
                odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return odontologo;
    }

    @Override
    public void eliminar(Odontologo odontologo) {
        logger.warn("Iniciando las operaciones para eliminar el odontologo con id : " + odontologo.getId());
        Connection connection = null;
        try {
            connection= BD.getConnection();
            PreparedStatement psDelete= connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, odontologo.getId());
            psDelete.execute();

        } catch (Exception e) {
            logger.error(e.getMessage());

        }
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.warn("iniciando las operaciones de actualizacion de un odontologo con id : "+odontologo.getId());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, odontologo.getMatricula());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3, odontologo.getApellido());
            psUpdate.setInt(4,odontologo.getId());
            psUpdate.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo> listaOdontologos = new ArrayList<>();

        Connection connection = null;

        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rsConsultAll = statement.executeQuery(SQL_SELECT_ALL);

            while(rsConsultAll.next()){
                listaOdontologos.add(new Odontologo(rsConsultAll.getInt(1),rsConsultAll.getString(2),rsConsultAll.getString(3),rsConsultAll.getString(4)));
            }
            logger.info("Devolviendo lista BD");

        } catch (Exception e){
            logger.warn(e.getMessage());
        }
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        logger.info("iniciando la busqueda por matricula: "+string);
        Connection connection=null;
        Odontologo odontologo= null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectE= connection.prepareStatement(SQL_SELECT_BY_MATRICULA);
            psSelectE.setString(1,string);
            ResultSet rs= psSelectE.executeQuery();
            while(rs.next()){
                odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));

            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return odontologo;
    }
}