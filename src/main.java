import api.GutendexAPI;
import database.Database;
import models.Book;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GutendexAPI gutendexAPI = new GutendexAPI();
        Database database = new Database();

        try {
            database.createTables();
        } catch (Exception e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n--- Catálogo de Libros ---");
            System.out.println("1. Buscar Libro por Título");
            System.out.println("2. Ver Detalles de un Libro");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String title = scanner.nextLine();
                    try {
                        List<Book> books = gutendexAPI.searchBooksByTitle(title);
                        for (Book book : books) {
                            System.out.println(book);
                            database.insertBook(book); // Guardar en la BD
                        }
                    } catch (Exception e) {
                        System.out.println("Error al buscar libros: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Funcionalidad no implementada aún.");
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }
}
