package umg.programacionII.DataBase.Model;


public class ModelProducto {
    private int id_producto;
    private String descripcion;
    private String origen;
    private int precio;  // Precio como double
    private int existencia;

    public ModelProducto(int id_producto, String descripcion, String origen, int precio, int existencia) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.origen = origen;
        this.precio = precio;
        this.existencia = existencia;
    }

    public ModelProducto() {}

    // Getters y Setters
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
}