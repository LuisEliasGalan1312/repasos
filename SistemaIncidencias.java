import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaIncidencias {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Incidencia> listaIncidencias = new ArrayList<>();
        int generadorId = 1;
        int opcion = -1;

        System.out.println("==== Bienvenidos ====");

        while (opcion != 0) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Registrar incidencia");
            System.out.println("2. Listar incidencias");
            System.out.println("3. Buscar incidencias por palabra clave");
            System.out.println("0. Salir");
            System.out.print("Digita una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digita un numero del menu.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa la descripcion (minimo 10 caracteres): ");
                    String desc = scanner.nextLine();
                    
                    System.out.print("Ingresa la fecha (dd/MM/yyyy): ");
                    String fecha = scanner.nextLine();
                    
                    System.out.print("Ingresa el nivel de prioridad (ALTA, MEDIA, BAJA): ");
                    String prio = scanner.nextLine();

                    try {
                        Incidencia nuevaIncidencia = new Incidencia(generadorId, desc, fecha, prio);
                        listaIncidencias.add(nuevaIncidencia);
                        System.out.println("Incidencia registrada con exito. (ID: " + generadorId + ")");
                        generadorId++;
                    } catch (DescripcionInvalidaException | FechaInvalidaException | PrioridadInvalidaException e) {
                        System.out.println("\n>> FALLO EL REGISTRO: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("\n>> Ocurrio un error inesperado: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTADO DE INCIDENCIAS ---");
                    if (listaIncidencias.isEmpty()) {
                        System.out.println("Aun no hay incidencias registradas.");
                    } else {
                        for (Incidencia incidencia : listaIncidencias) {
                            System.out.println(incidencia.toString());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingresa la palabra clave a buscar: ");
                    String palabraClave = scanner.nextLine().trim().toLowerCase();
                    boolean encontrada = false;

                    System.out.println("\n--- RESULTADOS DE BUSQUEDA ---");
                    for (Incidencia incidencia : listaIncidencias) {
                        if (incidencia.getDescripcion().toLowerCase().contains(palabraClave)) {
                            System.out.println(incidencia.toString());
                            encontrada = true;
                        }
                    }
                    
                    if (!encontrada) {
                        System.out.println("No se encontraron incidencias con la palabra: '" + palabraClave + "'");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion incorrecta. Por favor, elige un numero del 0 al 3.");
            }
        }
        
        scanner.close();
    }
}