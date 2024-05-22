package test;

import dao.impl.OdontologoMemoria;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OdontologoMemoriaTest {
    private static Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService();

    @Test
    @DisplayName("Test busqueda todos los odontologos")
    void testBusquedaTodos() {
        Odontologo odontologo = new Odontologo(3,11111,"Juan","Perez");
        odontologoService.guardarOdontologoMemoria(odontologo);

        List<Odontologo> odontologos = odontologoService.listarOdontologosMemoria();
        LOGGER.info(odontologos);
        assertEquals(1, odontologos.size());
    }
}
