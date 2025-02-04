
# LiterAlura

LiterAlura es un catálogo de libros interactivo que permite a los usuarios buscar libros, a través de la API Gutendex, y guardar los resultados en una base de datos. Este proyecto fue realizado como parte del Programa ONE de Alura Latam y Oracle. 

## Estructura del proyecto
El proyecto se divide en varios paquetes:
- **model**: Contiene los registros ApiResults, DataAutor y DataLibro, las clases Libro y Autor, y el enum Lenguaje.
- **principal**: Contiene la clase Principal, que maneja la interacción con el usuario y llama a los métodos correspondientes para cada opción del menú.
- **repository**: Contiene las interfaces de los repositorios de Autor y Libro para interactuar con la base de datos.
- **service**: Contiene las clases de servicio que manejan la lógica de la API.

## Funcionalidades
A continuación se muestra todas las acciones que permite realizar el programa:
- Buscar y registrar un libro mediante su título
- Mostrar todos los libros registrados en la base de datos
- Mostrar todos los autores registrados en la base de datos
- Buscar los autores registrados vivos durante determinado año
- Buscar los libros registrados en la base de datos en determinado idioma
- Mostrar el top 10 libros registrados en la base de datos más descargados
- Salir del programa

## Para ejecutar el proyecto
Para ejecutar este proyecto se necesita tener instalado Java en tu equipo, así como un IDE y pgAdmin 4. Durante el desarrollo del proyecto se utilizó Intellij como IDE. Los pasos para ejecutar el proyecto son los siguentes:

  1. Clona el repositorio a tu equipo local.
  2. Importa el proyecto a tu IDE.
  3. Ejecuta el proyecto.
  4. Sigue las instrucciones en la consola para interactuar con la aplicación.
  
## Ejemplo de uso
A continuación se muestra un ejemplo de uso de cada una de las funcionalidades disponibles
### 0. Menú del usuario
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/menu.PNG">

### 1. Buscar y registrar un libro mediante su título
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/opcion1.PNG">

### 2. Mostrar todos los libros registrados en la base de datos
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/opcion2.PNG">

### 3. Mostrar todos los autores registrados en la base de datos
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/opcion3.PNG">

### 4. Buscar los autores registrados vivos durante determinado año
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/opcion4.PNG">

### 5. Buscar los libros registrados en la base de datos en determinado idioma
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/opcion5.PNG">

### 6. Mostrar el top 10 libros registrados en la base de datos más descargados
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/opcion6.PNG">

### 0. Salir del programa
<img src="https://github.com/DanniiRiiv/LiterAlura/blob/master/img/opcion0.PNG">
