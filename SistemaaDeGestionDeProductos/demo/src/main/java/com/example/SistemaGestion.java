package RepasoCapitulo4.SistemaaDeGestionDeProductos.demo.src.main.java.com.example;

import java.util.Scanner;

public class SistemaGestion {

    public static void main(String[] args) {
        // Inicializamos el Scanner para leer del teclado
        final Scanner scanner = new Scanner(System.in);
        
        // Creamos nuestro inventario vacio
        Inventario inventario = new Inventario();
        
        int opcion = 0;

        // Bucle del Menu Principal
        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTION DE PRODUCTOS");
            System.out.println("========================================");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto por codigo");
            System.out.println("3. Listar todos los productos");
            System.out.println("4. Listar productos con stock");
            System.out.println("5. Listar productos sin stock");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Modificar stock de producto");
            System.out.println("8. Aplicar descuento general");
            System.out.println("9. Mostrar estadisticas");
            System.out.println("10. Calcular valor total del inventario");
            System.out.println("11. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            // Leemos la entrada del usuario como texto primero
            String entrada = scanner.nextLine();

            // Validacion basica: si escribio algo, lo convertimos a numero
            if (entrada.length() > 0) {
                opcion = Integer.parseInt(entrada);
            } else {
                opcion = 0; // Si dio Enter vacio, repetimos el menu
            }

            // Switch para manejar cada caso del menu
            switch (opcion) {
                case 1 -> {
                    // AGREGAR
                    System.out.println("--- NUEVO PRODUCTO ---");
                    System.out.print("Codigo: ");
                    String cod = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nom = scanner.nextLine();
                    
                    System.out.print("Precio: ");
                    double pre = Double.parseDouble(scanner.nextLine());
                    
                    System.out.print("Cantidad: ");
                    int cant = Integer.parseInt(scanner.nextLine());

                    Producto nuevoP = new Producto(cod, nom, pre, cant);
                    if (inventario.agregarProducto(nuevoP)) {
                        System.out.println("Producto agregado exitosamente.");
                    } else {
                        System.out.println("Inventario lleno o codigo duplicado.");
                    }
                }

                case 2 -> {
                    // BUSCAR
                    System.out.print("Ingrese codigo a buscar: ");
                    String buscaCod = scanner.nextLine();
                    Producto encontrado = inventario.buscarProducto(buscaCod);
                    
                    if (encontrado != null) {
                        encontrado.mostrarInfo();
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                }

                case 3 -> // LISTAR TODOS
                    inventario.listarProductos();

                case 4 -> // CON STOCK
                    inventario.listarProductosConStock();

                case 5 -> // SIN STOCK
                    inventario.listarProductosSinStock();

                case 6 -> {
                    // ELIMINAR
                    System.out.print("Ingrese codigo a eliminar: ");
                    String elimCod = scanner.nextLine();
                    if (inventario.eliminarProducto(elimCod)) {
                        System.out.println("Producto eliminado.");
                    } else {
                        System.out.println("No se encontro el producto.");
                    }
                }

                case 7 -> {
                    // MODIFICAR STOCK
                    System.out.print("Ingrese codigo del producto: ");
                    String modCod = scanner.nextLine();
                    Producto prodMod = inventario.buscarProducto(modCod);
                    
                    if (prodMod != null) {
                        System.out.println("Producto actual: " + prodMod.getNombre() + " (Stock: " + prodMod.getCantidad() + ")");
                        System.out.println("1. Agregar Stock");
                        System.out.println("2. Retirar Stock");
                        System.out.print("Elige: ");
                        int subOpc = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Cantidad: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        
                        if (subOpc == 1) {
                            prodMod.agregarStock(qty);
                            System.out.println("Stock actualizado.");
                        } else if (subOpc == 2) {
                            if (prodMod.retirarStock(qty)) {
                                System.out.println("Stock retirado.");
                            } else {
                                System.out.println("No hay suficiente stock.");
                            }
                        }
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                }

                case 8 -> {
                    // DESCUENTO GENERAL
                    System.out.print("Ingrese porcentaje de descuento: ");
                    double desc = Double.parseDouble(scanner.nextLine());
                    inventario.aplicarDescuentoGeneral(desc);
                }

                case 9 -> // ESTADISTICAS
                    CalculadoraEstadisticas.mostrarEstadisticas(
                        inventario.getProductos(),
                        inventario.getTotalProductos()
                    );

                case 10 -> {
                    // VALOR TOTAL
                    double total = inventario.calcularValorTotalInventario();
                    System.out.println("Valor total del inventario: $" + total);
                }

                case 11 -> System.out.println("saliendo...");

                default -> System.out.println("Opcion no valida.");
            }

        } while (opcion != 11); // Se repite hasta que elijan salir
        
        scanner.close();
    }
}