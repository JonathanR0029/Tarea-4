package tarea.individual.módulo.pkg3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Libro {
    private final String titulo;
    private final String autor;
    private final int anioPublicacion;
    private final String genero;
    private boolean disponible;
    private final List<Double> calificaciones;
    private final List<String> comentarios;

    public Libro(String titulo, String autor, int anioPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponible = true;
        this.calificaciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("El libro '" + titulo + "' ha sido prestado.");
        } else {
            System.out.println("El libro '" + titulo + "' no está disponible.");
        }
    }

    public void devolver() {
        if (!disponible) {
            disponible = true;
            System.out.println("El libro '" + titulo + "' ha sido devuelto.");
        } else {
            System.out.println("El libro '" + titulo + "' ya está disponible.");
        }
    }

    public void mostrarInformacion() {
        String estado = disponible ? "Disponible" : "Prestado";
        System.out.println("Título: " + titulo + 
                           "\nAutor: " + autor + 
                           "\nAño de Publicación: " + anioPublicacion + 
                           "\nGénero: " + genero + 
                           "\nEstado: " + estado);
    }

    public void agregarCalificacion(double calificacion) {
        calificaciones.add(calificacion);
        System.out.println("Calificación de " + calificacion + " añadida para el libro '" + titulo + "'.");
    }

    public void agregarComentario(String comentario) {
        comentarios.add(comentario);
        System.out.println("Comentario añadido para el libro '" + titulo + "'.");
    }

    public double obtenerPromedioCalificaciones() {
        if (calificaciones.isEmpty()) {
            return 0;
        }

        double suma = 0;
        for (double calificacion : calificaciones) {
            suma += calificacion;
        }

        return suma / calificaciones.size();
    }

    public void mostrarComentarios() {
        System.out.println("Comentarios para el libro '" + titulo + "':");
        for (String comentario : comentarios) {
            System.out.println("- " + comentario);
        }
    }

    public List<Double> getCalificaciones() {
        return calificaciones;
    }

    public List<String> getComentarios() {
        return comentarios;
    }
}

class Biblioteca {
    private final List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void buscarPorTitulo(String titulo) {
        System.out.println("Buscando libros con el título '" + titulo + "'...");

        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.mostrarInformacion();
                System.out.println(); 
            }
        }
    }

    public void buscarPorAutor(String autor) {
        System.out.println("Buscando libros del autor '" + autor + "'...");

        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                libro.mostrarInformacion();
                System.out.println();
            }
        }
    }

    public void prestarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.prestar();
                return;
            }
        }

        System.out.println("El libro '" + titulo + "' no se encuentra en la biblioteca.");
    }

    public void devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.devolver();
                return;
            }
        }

        System.out.println("El libro '" + titulo + "' no se encuentra en la biblioteca.");
    }

    public void mostrarLibrosDisponibles() {
        System.out.println("\nLibros disponibles en la biblioteca:");

        for (Libro libro : libros) {
            libro.mostrarInformacion();
            System.out.println(); 
        }
    }

    public void mostrarCalificacionesComentarios() {
        System.out.println("\nCalificaciones y Comentarios de los libros en la biblioteca:");

        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Calificación Promedio: " + libro.obtenerPromedioCalificaciones());
            libro.mostrarComentarios();
            System.out.println(); 
        }
    }

    public void agregarCalificacion(String titulo, double calificacion) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.agregarCalificacion(calificacion);
                return;
            }
        }

        System.out.println("El libro '" + titulo + "' no se encuentra en la biblioteca.");
    }

    public void agregarComentario(String titulo, String comentario) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.agregarComentario(comentario);
                return;
            }
        }

        System.out.println("El libro '" + titulo + "' no se encuentra en la biblioteca.");
    }

    public void mostrarEstadisticasGeneros() {
        Map<String, Integer> contadorGeneros = new HashMap<>();

        for (Libro libro : libros) {
            contadorGeneros.put(libro.getGenero(), contadorGeneros.getOrDefault(libro.getGenero(), 0) + 1);
        }

        System.out.println("\nEstadísticas de géneros:");

        for (Map.Entry<String, Integer> entry : contadorGeneros.entrySet()) {
            System.out.println("Género: " + entry.getKey() + ", Cantidad: " + entry.getValue());
        }
    }

    public void guardarLibrosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Biblioteca Virtual.txt"))) {
            for (Libro libro : libros) {
                writer.write("Título: " + libro.getTitulo());
                writer.newLine();
                writer.write("Autor: " + libro.getAutor());
                writer.newLine();
                writer.write("Año de Publicación: " + libro.getAnioPublicacion());
                writer.newLine();
                writer.write("Género: " + libro.getGenero());
                writer.newLine();
                writer.write("Estado: " + (libro.isDisponible() ? "Disponible" : "Prestado"));
                writer.newLine();
                writer.write("Calificaciones: " + libro.getCalificaciones().toString());
                writer.newLine();
                writer.write("Comentarios: " + libro.getComentarios().toString());
                writer.newLine();
                writer.newLine(); 
            }
            System.out.println("Información de los libros guardada en 'Biblioteca Virtual.txt'");
        } catch (IOException e) {
            System.out.println("Error al guardar la información en el archivo: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

     
        biblioteca.agregarLibro(new Libro("Drácula", "Bram Stoker", 1897, "Terror gótico"));
        biblioteca.agregarLibro(new Libro("El resplandor", "Stephen King", 1977, "Terror psicológico"));
        biblioteca.agregarLibro(new Libro("Frankenstein", "Mary Shelley", 1818, "Ciencia ficción/terror gótico"));
        biblioteca.agregarLibro(new Libro("La llamada de Cthulhu", "H.P. Lovecraft", 1928, "Terror cósmico"));
        biblioteca.agregarLibro(new Libro("El exorcista", "William Peter Blatty", 1971, "Terror sobrenatural"));

        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("\n--- Biblioteca Virtual ---");
                System.out.println("1. Agregar un libro");
                System.out.println("2. Buscar libro por título");
                System.out.println("3. Buscar libro por autor");
                System.out.println("4. Prestar un libro");
                System.out.println("5. Devolver un libro");
                System.out.println("6. Mostrar libros disponibles");
                System.out.println("7. Agregar calificación a un libro");
                System.out.println("8. Agregar comentario a un libro");
                System.out.println("9. Mostrar calificaciones y comentarios");
                System.out.println("10. Guardar libros en archivo");
                System.out.println("11. Mostrar estadísticas de géneros");
                System.out.println("12. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Ingrese el autor del libro: ");
                        String autor = scanner.nextLine();
                        System.out.print("Ingrese el año de publicación: ");
                        int anio = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el género del libro: ");
                        String genero = scanner.nextLine();
                        biblioteca.agregarLibro(new Libro(titulo, autor, anio, genero));
                        System.out.println("El libro '" + titulo + "' ha sido agregado correctamente.");
                        break;

                    case 2:
                        System.out.print("Ingrese el título del libro a buscar: ");
                        String tituloBuscar = scanner.nextLine();
                        biblioteca.buscarPorTitulo(tituloBuscar);
                        break;

                    case 3:
                        System.out.print("Ingrese el autor a buscar: ");
                        String autorBuscar = scanner.nextLine();
                        biblioteca.buscarPorAutor(autorBuscar);
                        break;

                    case 4:
                        System.out.print("Ingrese el título del libro a prestar: ");
                        String tituloPrestar = scanner.nextLine();
                        biblioteca.prestarLibro(tituloPrestar);
                        break;

                    case 5:
                        System.out.print("Ingrese el título del libro a devolver: ");
                        String tituloDevolver = scanner.nextLine();
                        biblioteca.devolverLibro(tituloDevolver);
                        break;

                    case 6:
                        biblioteca.mostrarLibrosDisponibles();
                        break;

                    case 7:
                        System.out.print("Ingrese el título del libro para agregar calificación: ");
                        String tituloCalificar = scanner.nextLine();
                        System.out.print("Ingrese la calificación (0-10): ");
                        double calificacion = scanner.nextDouble();
                        biblioteca.agregarCalificacion(tituloCalificar, calificacion);
                        break;

                    case 8:
                        System.out.print("Ingrese el título del libro para agregar comentario: ");
                        String tituloComentar = scanner.nextLine();
                        System.out.print("Ingrese su comentario: ");
                        String comentario = scanner.nextLine();
                        biblioteca.agregarComentario(tituloComentar, comentario);
                        break;

                    case 9:
                        biblioteca.mostrarCalificacionesComentarios();
                        break;

                    case 10:
                        biblioteca.guardarLibrosEnArchivo();
                        break;

                    case 11:
                        biblioteca.mostrarEstadisticasGeneros();
                        break;

                    case 12:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            } while (opcion != 12);
        }
    }
}