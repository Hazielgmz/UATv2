package Modelo;

public class MEmpleado {

    private int EmpleadoID;
    private String Nombre;
    private String ApellidoPaterno;
    private String email;
    private String direccion;
    private String telefono;
    private String usuario;
    private String clave;

    public MEmpleado(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // Getters
    public int getEmpleadoID() {
        return EmpleadoID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    // Setters
    public void setEmpleadoID(int empleadoID) {
        this.EmpleadoID = empleadoID;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.ApellidoPaterno = apellidoPaterno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
