package Modelos;

import javafx.beans.property.SimpleStringProperty;

public class Venta {
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty total = new SimpleStringProperty();
    private SimpleStringProperty efectivo = new SimpleStringProperty();
    private SimpleStringProperty cambio = new SimpleStringProperty();
    private SimpleStringProperty empleadoVenta = new SimpleStringProperty();
    private SimpleStringProperty fecha = new SimpleStringProperty();


    public Venta(String id, String total, String efectivo, String cambio, String empleadoVenta, String fecha) {
        this.id.set(id);
        this.total.set(total);
        this.efectivo.set(efectivo);
        this.cambio.set(cambio);
        this.empleadoVenta.set(empleadoVenta);
        this.fecha.set(fecha);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public String getEfectivo() {
        return efectivo.get();
    }

    public SimpleStringProperty efectivoProperty() {
        return efectivo;
    }

    public String getCambio() {
        return cambio.get();
    }

    public SimpleStringProperty cambioProperty() {
        return cambio;
    }

    public String getEmpleadoVenta() {
        return empleadoVenta.get();
    }

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public SimpleStringProperty empleadoVentaProperty() {
        return empleadoVenta;


    }
}
