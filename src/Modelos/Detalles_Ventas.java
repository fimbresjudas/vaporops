package Modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Detalles_Ventas {
    private long codigoVenta, codigoProducto;
    private String producto;
    private int cantidad, precioProducto, total_detalles;
    private SimpleStringProperty productoSimple = new SimpleStringProperty();
    private SimpleStringProperty cantidadSimple = new SimpleStringProperty();
    private  SimpleStringProperty precioProductoSimple = new SimpleStringProperty();
    private SimpleStringProperty totalDetallesSimple = new SimpleStringProperty();



    public Detalles_Ventas(long codigoProducto, String producto, int cantidad, int precioProducto, int total_detalles, long codigoVenta) {
        this.codigoProducto = codigoProducto;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
        this.total_detalles = total_detalles;
        this.codigoVenta = codigoVenta;
        this.productoSimple.set(producto);
        this.cantidadSimple.set(String.valueOf(cantidad));
        this.precioProductoSimple.set(String.valueOf(precioProducto));
        this.totalDetallesSimple.set(String.valueOf(total_detalles));
    }

    public long getCodigoVenta() {
        return codigoVenta;
    }

    public long getCodigoProducto() {
        return codigoProducto;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public int getTotal_detalles() {
        return total_detalles;
    }

    public String getProductoSimple() {
        return productoSimple.get();
    }

    public SimpleStringProperty productoSimpleProperty() {
        return productoSimple;
    }

    public String getCantidadSimple() {
        return cantidadSimple.get();
    }

    public SimpleStringProperty cantidadSimpleProperty() {
        return cantidadSimple;
    }

    public String getPrecioProductoSimple() {
        return precioProductoSimple.get();
    }

    public SimpleStringProperty precioProductoSimpleProperty() {
        return precioProductoSimple;
    }

    public String getTotalDetallesSimple() {
        return totalDetallesSimple.get();
    }

    public SimpleStringProperty totalDetallesSimpleProperty() {
        return totalDetallesSimple;
    }
}
