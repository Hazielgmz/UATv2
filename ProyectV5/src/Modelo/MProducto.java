package Modelo;

import java.math.BigDecimal;

public class MProducto {

    private int codigoID;
    private String codigoBarras;
    private String nombreProducto;
    private int proveedorID;
    private BigDecimal stock;
    private BigDecimal precioUnitario;
    private BigDecimal costo;
    private String tipo;

    public MProducto() {}

    public MProducto(int codigoID, String codigoBarras, String nombreProducto, int proveedorID,
                    BigDecimal stock, BigDecimal precioUnitario, BigDecimal costo, String tipo) {
        this.codigoID = codigoID;
        this.codigoBarras = codigoBarras;
        this.nombreProducto = nombreProducto;
        this.proveedorID = proveedorID;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.costo = costo;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getCodigoID() {
        return codigoID;
    }

    public void setCodigoID(int codigoID) {
        this.codigoID = codigoID;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getProveedorID() {
        return proveedorID;
    }

    public void setProveedorID(int proveedorID) {
        this.proveedorID = proveedorID;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigoID=" + codigoID +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", proveedorID=" + proveedorID +
                ", stock=" + stock +
                ", precioUnitario=" + precioUnitario +
                ", costo=" + costo +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
