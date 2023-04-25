CREATE DATABASE EC1_RodriguezMonzon;

use EC1_RodriguezMonzon;

CREATE TABLE areas(
	id_area int not null primary key auto_increment,
    nom_area varchar(64),
    cant_clien_area int
);

CREATE TABLE clientes(
	id_cliente int not null primary key auto_increment,
    nom_cliente varchar(64),
    id_area int,
    dni_cliente varchar(8),
    edad_cliente int,
    genero_cliente char(1) CHECK (genero_cliente IN ("M", "F")),
    FOREIGN KEY (id_area) REFERENCES areas(id_area)
);

INSERT INTO areas(nom_area, cant_clien_area) 
VALUES ("Computadoras", 64), 
		("Equipos", 32),
		("Monitores", 16);	

INSERT INTO clientes(nom_cliente, id_area, dni_cliente, edad_cliente, genero_cliente)
VALUES ("Luis Enrique", 1, "79864758", 21, "M"),
		("Miguel Rodriguez", 2, "19824751", 21, "M"),
        ("Juan Altamirano", 3, "18167758", 21, "M");
        
SELECT * FROM areas;
SELECT * FROM clientes;

DELIMITER $$
CREATE PROCEDURE areasFromIdCliente(IN p_id_cliente INT)
BEGIN
    SELECT areas.nom_area, areas.cant_clien_area, clientes.id_cliente, clientes.nom_cliente
    FROM areas
    JOIN clientes ON areas.id_area = clientes.id_area
    WHERE clientes.id_cliente = p_id_cliente;
END$$
DELIMITER ;

CALL areasFromIdCliente(1)