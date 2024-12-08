package Modelo;

public class MProveedor {
    private int proveedorID;
    private String nombre;
    private String direccion;
    private String telefono;
    private String estado;

    // Constructor vacío
    public MProveedor() {}

    // Constructor completo
    public MProveedor(int proveedorID, String nombre, String direccion, String telefono, String estado) {
        this.proveedorID = proveedorID;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    // Getters y Setters
    public int getProveedorID() { return proveedorID; }
    public void setProveedorID(int proveedorID) { this.proveedorID = proveedorID; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
