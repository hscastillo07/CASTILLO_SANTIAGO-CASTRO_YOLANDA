package dao.impl;

import dao.iDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements iDao<Odontologo> {
    private static final Logger LOGGER=Logger.getLogger(OdontologoDaoH2.class);

    private static String SQL_INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    private static String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection= null;
        OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
        Odontologo odontologoARetornar = null;
        try{
            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            psInsert.setInt(1,odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.executeUpdate();
            //necesito capturas las id
            ResultSet rs= psInsert.getGeneratedKeys();
            while (rs.next()){
                Integer id = rs.getInt(1);
                odontologoARetornar = new Odontologo(id,odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());


            }
            LOGGER.info("Odontologo guardado: "+odontologoARetornar);
            connection.commit();
            connection.setAutoCommit(true);


        }catch (Exception e){
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.info(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoARetornar;
    }

    @Override
    public List<Odontologo> buscarTodos() {

        Connection connection= null;
        List<Odontologo> odontologos= new ArrayList<>();

        OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
        try{
            connection= H2Connection.getConnection();
            PreparedStatement psselectAll= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= psselectAll.executeQuery();
            while (rs.next()){

                //construir el odontologo
                Odontologo odontologo= new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
                odontologos.add(odontologo);
            }


        }catch (Exception e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologos;
    }

}
