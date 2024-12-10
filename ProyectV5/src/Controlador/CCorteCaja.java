package Controlador;

public class CCorteCaja {
    private double totalEfectivo;
    private double totalVisaMaster;
    private double totalAmex;

    public CCorteCaja(double efectivo, double visaMaster, double amex) {
        this.totalEfectivo = efectivo;
        this.totalVisaMaster = visaMaster;
        this.totalAmex = amex;
    }

    public double getTotalEfectivo() {
        return totalEfectivo;
    }

    public double getTotalVisaMaster() {
        return totalVisaMaster;
    }

    public double getTotalAmex() {
        return totalAmex;
    }

    public boolean procesarRetiro(double monto, String metodo) {
        switch (metodo) {
            case "Efectivo":
                if (monto <= totalEfectivo) {
                    totalEfectivo -= monto;
                    return true;
                }
                break;
            case "Visa/Master":
                if (monto <= totalVisaMaster) {
                    totalVisaMaster -= monto;
                    return true;
                }
                break;
            case "AMEX":
                if (monto <= totalAmex) {
                    totalAmex -= monto;
                    return true;
                }
                break;
        }
        return false;
    }
}
