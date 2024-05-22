DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS (
                             ID INT AUTO_INCREMENT PRIMARY KEY,
                             MATRICULA INT NOT NULL,
                             NOMBRE VARCHAR(50) NOT NULL,
                             APELLIDO VARCHAR(50) NOT NULL
);

INSERT INTO ODONTOLOGOS
VALUES (DEFAULT, 5465657, 'Santiago', 'Castillo'),
       (DEFAULT, 54546456, 'Luis', 'Gómez');