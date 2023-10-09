-- Crear la base de datos "misionesbdd" si no existe
CREATE DATABASE IF NOT EXISTS misionesbdd;

-- Usar la base de datos "misionesbdd"
USE misionesbdd;

-- Crear la tabla "misiones" que coincide con la clase de Java
CREATE TABLE misiones (
    id_mision INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    nivel INT NOT NULL,
    recompensa INT NOT NULL,
    superada BOOLEAN NOT NULL,
    activa BOOLEAN NOT NULL
);


-- Insertar 10 misiones ficticias en la tabla "misiones"
INSERT INTO misiones (nombre, descripcion, nivel, recompensa, superada, activa) VALUES
    ('Rescate en la montaña', 'Rescata a un excursionista perdido en las montañas.', 5, 500, false, true),
    ('Caza de tesoros', 'Encuentra un tesoro escondido en una isla remota.', 3, 300, false, true),
    ('Entrega urgente', 'Entrega un paquete importante en menos de 24 horas.', 2, 200, false, true),
    ('Eliminación de amenaza', 'Elimina una amenaza de plagas en un campo de cultivo.', 4, 400, false, true),
    ('Exploración subacuática', 'Explora las profundidades del océano en busca de vida marina rara.', 6, 600, false, true),
    ('Rescate de animales', 'Rescata a animales atrapados en un incendio forestal.', 7, 700, false, true),
    ('Búsqueda de personas desaparecidas', 'Encuentra a personas desaparecidas en una ciudad.', 5, 500, false, true),
    ('Investigación paranormal', 'Investiga fenómenos paranormales en una casa encantada.', 8, 800, false, true),
    ('Protección de la vida marina', 'Protege a las ballenas de la caza furtiva en alta mar.', 9, 900, false, true),
    ('Misión secreta de espionaje', 'Realiza una misión de espionaje en un país extranjero.', 10, 1000, false, true);
