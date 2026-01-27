package RepasoCapitulo4.SistemaaDeGestionDeProductos.demo.src.main.java.com.example;

public class CalculadoraEstadisticas {

    // Calcula el promedio de precios
    public static double calcularPromedioPrecio(Producto[] productos, int cantidad) {
        if (cantidad == 0) return 0;
        
        double sumaPrecios = 0;
        int contadorValidos = 0;

        for (int i = 0; i < cantidad; i++) {
            double precio = productos[i].getPrecio();
            sumaPrecios += precio;
            contadorValidos++;
        }

        return sumaPrecios / contadorValidos;
    }

    // Encuentra el producto mas caro
    public static Producto encontrarProductoMasCaro(Producto[] productos, int cantidad) {
        if (cantidad == 0) return null;

        Producto masCaro = productos[0];

        for (int i = 1; i < cantidad; i++) {
            if (productos[i].getPrecio() > masCaro.getPrecio()) {
                masCaro = productos[i];
            }
        }
        return masCaro;
    }

    // Encuentra el producto mas barato
    public static Producto encontrarProductoMasBarato(Producto[] productos, int cantidad) {
        if (cantidad == 0) return null;

        Producto masBarato = productos[0];

        for (int i = 1; i < cantidad; i++) {
            if (productos[i].getPrecio() < masBarato.getPrecio()) {
                masBarato = productos[i];
            }
        }
        return masBarato;
    }

    // Cuenta productos en un rango de precio
    public static int contarProductosPorRangoPrecio(Producto[] productos, int cantidad, double min, double max) {
        int contador = 0;
        for (int i = 0; i < cantidad; i++) {
            double precio = productos[i].getPrecio();
            if (precio >= min && precio <= max) {
                contador++;
            }
        }
        return contador;
    }

    // Calcula promedio del valor total
    public static double calcularValorPromedioInventario(Producto[] productos, int cantidad) {
        if (cantidad == 0) return 0;
        
        double valorTotal = 0;
        for(int i = 0; i < cantidad; i++) {
            valorTotal += productos[i].calcularValorTotal();
        }
        return valorTotal / cantidad;
    }

    // Muestra reporte completo de estadisticas
    public static void mostrarEstadisticas(Producto[] productos, int cantidad) {
        if (cantidad == 0) {
            System.out.println("Inventario vacio.");
            return;
        }

        System.out.println("========================================");
        System.out.println("       ESTADISTICAS DEL INVENTARIO      ");
        System.out.println("========================================");
        System.out.println("Total de productos: " + cantidad);
        System.out.println("Precio promedio: $" + calcularPromedioPrecio(productos, cantidad));
        
        Producto caro = encontrarProductoMasCaro(productos, cantidad);
        System.out.println("Producto mas caro: " + caro.getNombre() + " ($" + caro.getPrecio() + ")");
        
        Producto barato = encontrarProductoMasBarato(productos, cantidad);
        System.out.println("Producto mas barato: " + barato.getNombre() + " ($" + barato.getPrecio() + ")");
        
        double valorTotal = 0;
        int conStock = 0;
        for(int i=0; i<cantidad; i++) {
            valorTotal += productos[i].calcularValorTotal();
            if(productos[i].hayStock()) conStock++;
        }
        
        System.out.println("Valor total inventario: $" + valorTotal);
        System.out.println("Productos con stock: " + conStock);
        System.out.println("Productos sin stock: " + (cantidad - conStock));
        System.out.println("========================================");
    }
}