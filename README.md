# Practica Global 2ºEvaluacion

## ¿Qué hace este programa?
Es un sistema para administrar conciertos, artistas y entradas. Permite guardar datos nuevos, borrar los que ya no necesites y ver toda la información guardada en una base de datos de Oracle.

Para que el programa funcione, utiliza **Maven**, que se encarga de conectar el código con el driver de la base de datos de forma automática.

## Organización del código
El código está separado en varias partes para que sea más fácil de entender y arreglar:

* **Clase Main**: Es el punto de inicio. Se conecta a la base de datos y muestra el menú principal.
* **Menús (Artista, Concierto y Entrada)**: Son archivos separados que controlan las opciones de cada sección (añadir, borrar o listar).
* **Clases de datos (Artista, Concierto y Entrada)**: Son moldes que sirven para guardar la información de cada objeto con sus variables y métodos (getters y setters), constructo y su toString personalizado.

Cuando elijas añadir algo, el programa te pedirá los datos por teclado. Para borrar, solo tendrás que escribir el número de ID del registro.

## Cómo usarlo
1. **Ejecutar**: Abre el proyecto en tu IDE (como IntelliJ o Eclipse) y dale al botón de la flecha verde para empezar.
2. **Base de datos**: Debes tener instalado Oracle SQL con las tablas de artista, concierto y entrada ya creadas para que el programa pueda guardar los datos.
3. **Menú principal**: Al arrancar, verás 4 opciones. Las tres primeras entran en cada sección y la cuarta cierra el programa.
4. **Moverse por los menús**: Dentro de cada apartado puedes elegir qué hacer. Siempre hay una opción para volver atrás al menú principal si quieres cambiar de sección.
