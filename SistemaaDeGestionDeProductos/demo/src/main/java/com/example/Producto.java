package RepasoCapitulo4.SistemaaDeGestionDeProductos.demo.src.main.java.com.example;

public final class Producto {
    // Atributos privados
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    // Constructor completo con validaciones
    public Producto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.setPrecio(precio);     // Valida precio positivo
        this.setCantidad(cantidad); // Valida cantidad positiva
    }

    // Constructor vacio por defecto
    public Producto(){
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    // Valida que el precio no sea negativo
    public void setPrecio(double precio) {
        if(precio >= 0) {
            this.precio = precio;
        } else {
            System.out.println("monto no es valido.");
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    // Valida que la cantidad no sea negativa
    public void setCantidad(int cantidad) {
        if(cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            System.out.println("cantidad no es valida.");
        }
    }

    // Calcula precio por cantidad
    public double calcularValorTotal() {
        return this.precio * this.cantidad;
    }

    // Aplica descuento
    public void aplicarDescuento(double porcentaje) {
        if(porcentaje < 0 || porcentaje > 100) {
            System.out.println("Porcentaje no valido.");
            return;
        }
        double descuento = this.precio * (porcentaje / 100);
        this.precio = this.precio - descuento;
    }

    // Retira stock si hay suficiente
    public boolean retirarStock(int cantidadRetirar) {
        if (cantidadRetirar <= 0) return false;

        if (cantidadRetirar <= this.cantidad) {
            this.cantidad = this.cantidad - cantidadRetirar;
            return true;
        } else {
            return false;
        }
    }

    // Agrega stock
    public void agregarStock(int agregarcantidad) {
        if (agregarcantidad > 0) {
            this.cantidad = this.cantidad + agregarcantidad;
        } else {
            System.out.println("Cantidad a agregar no valida.");
        }
    }

    // Verifica si queda inventario
    public boolean hayStock(){
        return this.cantidad > 0;
    }

    // Muestra informacion formateada en consola
    public void mostrarInfo() {
        System.out.println("=================================");
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: $" + this.precio);
        System.out.println("Cantidad: " + this.cantidad);
        System.out.println("Valor Total: $" + this.calcularValorTotal());
        
        if (hayStock()) {
            System.out.println("Stock: Disponible");
        } else {
            System.out.println("Stock: Agotado");
        }
        System.out.println("=================================");
    }
}