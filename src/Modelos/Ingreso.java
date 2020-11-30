package Modelos;

import javafx.beans.property.SimpleStringProperty;

public class Ingreso {
    private SimpleStringProperty codigo = new SimpleStringProperty();
    private SimpleStringProperty producto = new SimpleStringProperty();
    private SimpleStringProperty precioIngreso = new SimpleStringProperty();
    private SimpleStringProperty cantidadIngreso = new SimpleStringProperty();
    private SimpleStringProperty fecha = new SimpleStringProperty();

    public Ingreso(String codigo, String producto, String precioIngreso, String cantidadIngreso, String fecha) {
        this.codigo.set(codigo);
        this.producto.set(producto);
        this.precioIngreso.set(precioIngreso);
        this.cantidadIngreso.set(cantidadIngreso);
        this.fecha.set(fecha);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public SimpleStringProperty codigoProperty() {
        return codigo;
    }

    public String getProducto() {
        return producto.get();
    }

    public SimpleStringProperty productoProperty() {
        return producto;
    }

    public String getPrecioIngreso() {
        return precioIngreso.get();
    }

    public SimpleStringProperty precioIngresoProperty() {
        return precioIngreso;
    }

    public SimpleStringProperty cantidadIngresoProperty() {
        return cantidadIngreso;
    }
    public SimpleStringProperty fechaProperty() {
        return fecha;
    }
}
