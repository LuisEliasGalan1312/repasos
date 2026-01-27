package RepasoCapitulo4.SistemaaDeGestionDeProductos.demo.src.main.java.com.example;

public class Inventario {
    
    // Arreglo fijo de 100 posiciones
    private final Producto[] productos;
    // Contador de productos reales registrados
    private int totalProductos;

    public Inventario() {
        this.productos = new Producto[100];
        this.totalProductos = 0;
    }

    // Agrega un producto si hay espacio y no existe el codigo
    public boolean agregarProducto(Producto p) {
        if (this.totalProductos >= 100) {
            return false; // Inventario lleno
        }

        if (buscarProducto(p.getCodigo()) != null) {
            return false; // Codigo duplicado
        }

        this.productos[this.totalProductos] = p;
        this.totalProductos++;

        return true;
    }

    // Busca un producto por su codigo unico
    public Producto buscarProducto(String codigo) {
        for (int i = 0; i < this.totalProductos; i++) {
            if (this.productos[i].getCodigo().equals(codigo)) {
                return this.productos[i];
            }
        }
        return null; // No encontrado
    }

    // Elimina un producto y mueve los demas para tapar el hueco
    public boolean eliminarProducto(String codigo) {
        int posicionEncontrada = -1;

        // Buscamos la posicion
        for (int i = 0; i < this.totalProductos; i++) {
            if (this.productos[i].getCodigo().equals(codigo)) {
                posicionEncontrada = i;
                break;
            }
        }

        if (posicionEncontrada == -1) {
            return false;
        }

        // Movemos los elementos a la izquierda
        for (int i = posicionEncontrada; i < this.totalProductos - 1; i++) {
            this.productos[i] = this.productos[i + 1];
        }

        // Limpiamos la ultima posicion y bajamos contador
        this.productos[this.totalProductos - 1] = null;
        this.totalProductos--;

        return true;
    }

    public void listarProductos() {
        if (this.totalProductos == 0) {
            System.out.println("Inventario vacio.");
            return;
        }
        System.out.println("--- TODOS LOS PRODUCTOS ---");
        for (int i = 0; i < this.totalProductos; i++) {
            this.productos[i].mostrarInfo();
        }
    }

    public void listarProductosConStock() {
        System.out.println("--- PRODUCTOS CON STOCK ---");
        for (int i = 0; i < this.totalProductos; i++) {
            if (this.productos[i].hayStock()) {
                this.productos[i].mostrarInfo();
            }
        }
    }

    public void listarProductosSinStock() {
        System.out.println("--- PRODUCTOS AGOTADOS ---");
        for (int i = 0; i < this.totalProductos; i++) {
            if (!this.productos[i].hayStock()) {
                this.productos[i].mostrarInfo();
            }
        }
    }

    // Suma el valor total de todo el inventario
    public double calcularValorTotalInventario() {
        double total = 0;
        for (int i = 0; i < this.totalProductos; i++) {
            total += this.productos[i].calcularValorTotal();
        }
        return total;
    }

    // Aplica descuento a todos los productos
    public void aplicarDescuentoGeneral(double porcentaje) {
        for (int i = 0; i < this.totalProductos; i++) {
            this.productos[i].aplicarDescuento(porcentaje);
        }
        System.out.println("Descuento aplicado a " + this.totalProductos + " productos.");
    }

    public int contarProductosConStock() {
        int contador = 0;
        for (int i = 0; i < this.totalProductos; i++) {
            if (this.productos[i].hayStock()) {
                contador++;
            }
        }
        return contador;
    }
    
    // Getters para usar en estadisticas
    public Producto[] getProductos() {
        return this.productos;
    }
    
    public int getTotalProductos() {
        return this.totalProductos;
    }
}