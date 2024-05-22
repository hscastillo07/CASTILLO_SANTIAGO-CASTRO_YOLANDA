package service;

import dao.iDao;
import dao.impl.OdontologoDaoH2;
import dao.impl.OdontologoMemoria;
import model.Odontologo;

import java.util.List;

public class OdontologoService {

    private iDao<Odontologo> odontologoiDao = new OdontologoDaoH2();
    private iDao<Odontologo> odontologoiDaoMemoria = new OdontologoMemoria();

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public Odontologo guardarOdontologoMemoria(Odontologo odontologo){
        return odontologoiDaoMemoria.guardar(odontologo);
    }
    public List<Odontologo> listarOdontologos(){
        return odontologoiDao.buscarTodos();
    }
    public List<Odontologo> listarOdontologosMemoria(){
        return odontologoiDaoMemoria.buscarTodos();
    }
}
