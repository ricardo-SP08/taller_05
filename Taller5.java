
class DatabaseConnection {
    private static DatabaseConnection instanciaUnica;
   

    private DatabaseConnection() {

        System.out.println("[DatabaseConnection] Conexión establecida con la base de datos central.");
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new DatabaseConnection();
        }
        return instanciaUnica;
    }

    public void ejecutarConsulta(String sql) {
        System.out.println("[DatabaseConnection] Ejecutando query: " + sql);
    }
}



abstract class Producto {
    protected String id;
    protected String nombre;
    protected double precio;

    public Producto(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public abstract void mostrarDetalles();
}

class ProductoFisico extends Producto {
    private double peso;
    private String dimensiones;

    public ProductoFisico(String id, String nombre, double precio, double peso, String dimensiones) {
        super(id, nombre, precio);
        this.peso = peso;
        this.dimensiones = dimensiones;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("-> [Producto Físico] ID: " + id + ", Nombre: " + nombre + 
                           ", Precio: $" + precio + ", Peso: " + peso + "kg, Dimensiones: " + dimensiones);
    }
}

class ProductoDigital extends Producto {
    private double tamanoMB;
    private String formato;

    public ProductoDigital(String id, String nombre, double precio, double tamanoMB, String formato) {
        super(id, nombre, precio);
        this.tamanoMB = tamanoMB;
        this.formato = formato;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("-> [Producto Digital] ID: " + id + ", Nombre: " + nombre + 
                           ", Precio: $" + precio + ", Tamaño: " + tamanoMB + "MB, Formato: " + formato);
    }
}



abstract class ProductFactory {

}

class PhysicalProductFactory extends ProductFactory {
    public ProductoFisico crearProductoFisico(String id, String nombre, double precio, double peso, String dimensiones) {
        return new ProductoFisico(id, nombre, precio, peso, dimensiones);
    }
}

class DigitalProductFactory extends ProductFactory {
    public ProductoDigital crearProductoDigital(String id, String nombre, double precio, double tamanoMB, String formato) {
        return new ProductoDigital(id, nombre, precio, tamanoMB, formato);
    }
}