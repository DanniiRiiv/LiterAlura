package com.LiterAlura.principal;

import com.LiterAlura.model.ApiResults;
import com.LiterAlura.model.Autor;
import com.LiterAlura.model.DataLibro;
import com.LiterAlura.model.Libro;
import com.LiterAlura.model.Lenguaje;
import com.LiterAlura.repository.AutorRepository;
import com.LiterAlura.service.ConsumoAPI;
import com.LiterAlura.service.ConvertData;
import com.LiterAlura.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    Scanner getNumber = new Scanner(System.in);
    Scanner getSentence = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";

    @Autowired
    ConsumoAPI consumoAPI;

    @Autowired
    ConvertData converter;

    @Autowired
    LibroRepository libroRepository; // Inyectamos el repositorio de Libro

    @Autowired
    AutorRepository autorRepository;

    //Menu para el usuario
    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
            \n
            ------------------------- Menú de opciones -------------------------
            1 - Buscar y registrar libro mediante su título
            2 - Mostrar todos los libros registrados en la DB
            3 - Mostrar todos los autores registrados en la DB
            4 - Buscar los autores registrados vivos durante determinado año
            5 - Buscar los libros registrados en determinado idioma
            6 - Mostrar el top 10 libros registrados más descargados
            0 - Salir
            --------------------------------------------------------------------
            """;
            System.out.println(menu);
            opcion = getNumber.nextInt();

            switch (opcion) {
                case 1:
                    guardarLibro();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    buscarAutores();
                    break;
                case 5:
                    mostrarLibrosPorLenguaje();
                    break;
                case 6:
                    top10Libros();
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema ※\\(^o^)/※");
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }


    // 1 - Para buscar un libro mediante su título
    // 1.1 - Obtener la información de la API
    private DataLibro buscarLibro() {
        // Pedir el título del libro
        System.out.println("Ingresa el nombre del libro: ");
        String tituloLibro = getSentence.nextLine().toLowerCase();
        // Llamar a la API para conseguir la información
        var json = consumoAPI.getData(URL_BASE + "?search=" + tituloLibro.replace(" ","%20"));
        ApiResults apiResults = converter.getDates(json, ApiResults.class);
        // Verificar si hay resultados
        if (apiResults.books().isEmpty()) {
            return null;
        }
        // Devolver solo el primer resultado
        return apiResults.books().get(0);
    }
    // 1.2 - Para crear el libro solicitado por el usuario
    private void guardarLibro(){
        // Pide el libro a la API
        DataLibro userLibro = buscarLibro();

        // Verifica si se encontró un libro
        if (userLibro == null) {
            System.out.println("⚠ No se encontró ningún libro con ese título.\n");
            return;
        }

        // Verifica si el libro ya existe en la base de datos
        Optional<Libro> libroExistente = libroRepository.findByTitulo(userLibro.titulo());
        if (libroExistente.isPresent()) {
            System.out.println("\n⚠ El libro ya está registrado en la base de datos.\n");
            return;
        }

        // Crea el nuevo libro
        Libro nuevoLibro = new Libro(userLibro);

        // Guarda el nuevo libro en la base de datos
        libroRepository.save(nuevoLibro);

        // Imprime el libro guardado
        System.out.println("\n☑ Libro guardado en la base de datos correctamente.\n");
        System.out.println(nuevoLibro);
    }


    // 2 - Para mostrar todos los libros en la base de datos
    private void mostrarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("⚠ No hay libros registrados en la base de datos.\n");
        } else {
            System.out.println("Libros registrados en la base de datos:\n");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }


    // 3 - Mostrar todos los autores en la base de datos
    private void mostrarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("⚠ No hay libros registrados en la base de datos.\n");
        } else {
            System.out.println("Libros registrados en la base de datos:\n");
            for (Autor autor : autores) {
                System.out.println(autor);
            }
        }
    }


    // 4 - Buscar autores vivos durante determinado año
    private void buscarAutores() {
        // Pedir el año al usario
        System.out.println("Ingresa el año: ");
        int userYear = getNumber.nextInt();

        List<Autor> autores = autorRepository.findAuthorsAliveDuringYear(userYear);
        if (autores.isEmpty()) {
            System.out.println("⚠ No hay autores vivos durante ese año registrados en la base de datos.\n");
        } else {
            System.out.println("\nAutores registrados vivos durante " + userYear + ".\n");
            for (Autor autor : autores) {
                System.out.println(autor);
            }
        }
    }


    // 5 - Mostrar todos los libros en determinado idioma
    private void mostrarLibrosPorLenguaje() {
        System.out.println("Lenguajes disponibles en la base de datos: ");
        List<Lenguaje> lenguajesDisponibles = libroRepository.findDistinctLenguajes();
        int posicion = 1;
        for (Lenguaje lenguaje : lenguajesDisponibles) {
            System.out.println(posicion + "." + lenguaje);
            posicion++;
        }

        Lenguaje lenguaje = null;
        boolean lenguajeValido = false;
        // Pedir el lenguaje al usuario
        while (!lenguajeValido) {
            System.out.println("\nSelecciona un lenguaje: ");
            String lenguajeInput = getSentence.nextLine().toUpperCase();
            lenguaje = Lenguaje.valueOf(lenguajeInput);
            if (lenguajesDisponibles.contains(lenguaje)) {
                lenguajeValido = true;
            } else {
                System.out.println("⚠ Lenguaje no disponible. Por favor, intenta de nuevo.");
            }
        }

        // Imprimir libros
        List<Libro> libros = libroRepository.findByLenguaje(lenguaje);
        System.out.println("\nLibros registrados en " + lenguaje.toString().toLowerCase() + ":\n");
        posicion = 1;
        for (Libro libro : libros) {
            System.out.println(posicion + ". \"" + libro.getTitulo() + "\" - " + getFormattedAutores(libro));
            posicion++;
        }
    }


    // 6 - Mostrar top 10 libros
    private void top10Libros() {
        List<Libro> topLibros = libroRepository.findTop10ByOrderByDescargasDesc();
        if (topLibros.isEmpty()) {
            System.out.println("⚠ No hay libros registrados en la base de datos.");
        } else {
            System.out.println("\nTop 10 libros más descargados registrados en la base de datos:\n");
            int posicion = 1;
            for (Libro libro : topLibros) {
                System.out.println(posicion + ". Total de descargas: " + libro.getDescargas() + " -> " + libro.getTitulo() + "\" - " + getFormattedAutores(libro));
                posicion++;
            }
        }
    }



    // Método auxiliar para obtener los autores formateados
    private String getFormattedAutores(Libro libro) {
        StringBuilder sb = new StringBuilder();
        for (Autor autor : libro.getAutores()) {
            String fechaNacimiento = (autor.getFechaNacimiento() == 0) ? "desconocido" : String.valueOf(autor.getFechaNacimiento());
            String fechaMuerte = (autor.getFechaMuerte() == 0) ? "desconocido" : String.valueOf(autor.getFechaMuerte());

            sb.append(autor.getFormattedNombre())
                    .append(" (")
                    .append(fechaNacimiento)
                    .append(" - ")
                    .append(fechaMuerte)
                    .append(")");
        }
        return sb.toString();
    }

}