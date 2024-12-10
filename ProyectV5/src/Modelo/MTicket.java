package Modelo;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MTicket {
    private int ordenID; // Llave primaria
    private int empleadoID; // Llave foránea
    private int productoID; // Llave foránea
    private BigDecimal cantidad; // Decimal con dos decimales
    private BigDecimal precioUnitario; // Decimal con dos decimales
    private BigDecimal subtotal; // Decimal con dos decimales
    private BigDecimal total; // Decimal con dos decimales
    private LocalDateTime fecha; // Fecha y hora
    private BigDecimal descuento; // Puede ser nulo

    // Constructor vacío
    public MTicket() {}

    // Constructor con parámetros
    public MTicket(int ordenID, int empleadoID, int productoID, BigDecimal cantidad, BigDecimal precioUnitario,
                  BigDecimal subtotal, BigDecimal total, LocalDateTime fecha, BigDecimal descuento) {
        this.ordenID = ordenID;
        this.empleadoID = empleadoID;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.total = total;
        this.fecha = fecha;
        this.descuento = descuento;
    }

    // Getters y Setters
    public int getOrdenID() {
        return ordenID;
    }

    public void setOrdenID(int ordenID) {
        this.ordenID = ordenID;
    }

    public int getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public int getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ordenID=" + ordenID +
                ", empleadoID=" + empleadoID +
                ", productoID=" + productoID +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", fecha=" + fecha +
                ", descuento=" + descuento +
                '}';
    }
}

