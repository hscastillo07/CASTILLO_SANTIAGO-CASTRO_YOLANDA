package dao.impl;
import dao.iDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class OdontologoMemoria implements iDao<Odontologo> {
        private Logger LOGGER = Logger.getLogger(OdontologoMemoria.class);
        List<Odontologo> odontologos = new ArrayList<>();


        @Override
        public Odontologo guardar(Odontologo odontologo) {
            Integer id = odontologos.size()+1;
            odontologo.setId(id);

            odontologos.add(odontologo);
            LOGGER.info("odontologo guardado: "+ odontologo);
            return odontologo;
        }

        @Override
        public List<Odontologo> buscarTodos () {
            return odontologos;
        }
    }

