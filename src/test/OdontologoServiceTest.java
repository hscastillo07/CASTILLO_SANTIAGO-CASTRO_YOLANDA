package test;
import dao.impl.OdontologoDaoH2;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OdontologoServiceTest {
    private static Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService();
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/CASTRO_YOLANDA-CASTILLO_SANTIAGO;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Test busqueda todos los odontologos")
    void testBusquedaTodos() {
        Odontologo odontologo = new Odontologo(3,11111,"Juan","Perez");
        odontologoService.guardarOdontologo(odontologo);

        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        LOGGER.info(odontologos);

        assertEquals(3, odontologos.size());
    }
}
