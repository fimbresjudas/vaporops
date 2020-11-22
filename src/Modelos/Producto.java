package Modelos;

import javafx.beans.property.SimpleStringProperty;

public class Producto {
    private long codigo;
    private String nombre, detalles, distribuidor;
    private  int precioVenta, stock_total, stock_actual;
    private SimpleStringProperty idSimple = new SimpleStringProperty();
    private SimpleStringProperty nombreSimple = new SimpleStringProperty();
    private SimpleStringProperty distribuidorSimple = new SimpleStringProperty();
    private SimpleStringProperty detallesSimple = new SimpleStringProperty();
    private SimpleStringProperty precioSimple = new SimpleStringProperty();
    private SimpleStringProperty stockTotalSimple = new SimpleStringProperty();
    private SimpleStringProperty stockActualSimple = new SimpleStringProperty();

    public Producto(long codigo, String nombre, String detalles, String distribuidor, int precioVenta, int stock_total, int stock_actual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.detalles = detalles;
        this.distribuidor = distribuidor;
        this.precioVenta = precioVenta;
        this.stock_total = stock_total;
        this.stock_actual = stock_actual;
        idSimple.set(String.valueOf(codigo));
        nombreSimple.set(nombre);
        distribuidorSimple.set(distribuidor);
        detallesSimple.set(detalles);
        precioSimple.set(String.valueOf(precioVenta));
        stockTotalSimple.set(String.valueOf(stock_total));
        stockActualSimple.set(String.valueOf(stock_actual));
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock_total() {
        return stock_total;
    }

    public void setStock_total(int stock_total) {
        this.stock_total = stock_total;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public String getIdSimple() {
        return idSimple.get();
    }

    public SimpleStringProperty idSimpleProperty() {
        return idSimple;
    }

    public void setIdSimple(String idSimple) {
        this.idSimple.set(idSimple);
    }

    public String getNombreSimple() {
        return nombreSimple.get();
    }

    public SimpleStringProperty nombreSimpleProperty() {
        return nombreSimple;
    }

    public void setNombreSimple(String nombreSimple) {
        this.nombreSimple.set(nombreSimple);
    }

    public String getDistribuidorSimple() {
        return distribuidorSimple.get();
    }

    public SimpleStringProperty distribuidorSimpleProperty() {
        return distribuidorSimple;
    }

    public void setDistribuidorSimple(String distribuidorSimple) {
        this.distribuidorSimple.set(distribuidorSimple);
    }

    public String getDetallesSimple() {
        return detallesSimple.get();
    }

    public SimpleStringProperty detallesSimpleProperty() {
        return detallesSimple;
    }

    public void setDetallesSimple(String detallesSimple) {
        this.detallesSimple.set(detallesSimple);
    }

    public String getPrecioSimple() {
        return precioSimple.get();
    }

    public SimpleStringProperty precioSimpleProperty() {
        return precioSimple;
    }

    public void setPrecioSimple(String precioSimple) {
        this.precioSimple.set(precioSimple);
    }

    public String getStockTotalSimple() {
        return stockTotalSimple.get();
    }

    public SimpleStringProperty stockTotalSimpleProperty() {
        return stockTotalSimple;
    }

    public void setStockTotalSimple(String stockTotalSimple) {
        this.stockTotalSimple.set(stockTotalSimple);
    }

    public String getStockActualSimple() {
        return stockActualSimple.get();
    }

    public SimpleStringProperty stockActualSimpleProperty() {
        return stockActualSimple;
    }

    public void setStockActualSimple(String stockActualSimple) {
        this.stockActualSimple.set(stockActualSimple);
    }
}
