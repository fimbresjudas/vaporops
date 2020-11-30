package Controladores;

import Modelos.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller_venta {
    @FXML private Label id, nombre, numeroVenta, totalLbl, cambioLbl, subtotalLbl;
    @FXML private TextField cantidadTF, codigoTF, pagoTF;
    @FXML private AnchorPane ventas;
    @FXML private TableView<Detalles_Ventas> ventasT = new TableView<>();
    @FXML private TableColumn<Detalles_Ventas, String> cantidadCol = new TableColumn<>();
    @FXML private TableColumn<Detalles_Ventas, String> nombreCol = new TableColumn<>();
    @FXML private TableColumn<Detalles_Ventas, String> precioUnitarioCol = new TableColumn<>();
    @FXML private TableColumn<Detalles_Ventas, String> totalCol = new TableColumn<>();
    static ObservableList<Detalles_Ventas> productos = FXCollections.observableArrayList();
    private long folioVenta;
    private int subtotal, total;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        folioVenta = (DBManager.folioVenta());
        numeroVenta.setText(numeroVenta.getText()+""+folioVenta);
        ventasT.setItems(productos);
        cantidadCol.setCellValueFactory(cellData -> cellData.getValue().cantidadSimpleProperty());
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().productoSimpleProperty());
        precioUnitarioCol.setCellValueFactory(cellData -> cellData.getValue().precioProductoSimpleProperty());
        totalCol.setCellValueFactory(cellData -> cellData.getValue().totalDetallesSimpleProperty());
        Ventanas.v.numericOnly(codigoTF);
        Ventanas.v.numericOnly(cantidadTF);
        Ventanas.v.numericOnly(pagoTF);
    }

    public void atrasPanelPrincipal (){
        Ventanas.v.cargarPanelPrincipal(ventas);
    }

    public void agregar(){
        if (!codigoTF.getText().equals("") && !cantidadTF.getText().equals("")) {
            Detalles_Ventas productoVenta = DBManager.agregarProductoVenta(codigoTF.getText(), Integer.parseInt(cantidadTF.getText()), folioVenta);
            if (productoVenta != null) {
                productos.add(productoVenta);
                ventasT.setItems(productos);
                cantidadCol.setCellValueFactory(cellData -> cellData.getValue().cantidadSimpleProperty());
                nombreCol.setCellValueFactory(cellData -> cellData.getValue().productoSimpleProperty());
                precioUnitarioCol.setCellValueFactory(cellData -> cellData.getValue().precioProductoSimpleProperty());
                totalCol.setCellValueFactory(cellData -> cellData.getValue().totalDetallesSimpleProperty());
                subtotal +=productoVenta.getTotal_detalles();
                subtotalLbl.setText("SUBTOTAL: $"+subtotal);
                codigoTF.setText("");
                cantidadTF.setText("");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Este articulo no existe o no hay la cantidad deseada");
                alert.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Codigo y/o Cantidad vacios");
        }
    }

    public void buscarProducto(){
        Ventanas.v.buscarProducto_ventas();
    }

    public void pagar(){
        if (!productos.isEmpty()) {
            if (!pagoTF.getText().equals("")) {
                if (Integer.parseInt(pagoTF.getText()) >= subtotal) {
                    total = subtotal;
                    totalLbl.setText(totalLbl.getText() + " $" + total);
                    cambioLbl.setText(cambioLbl.getText() + " $" + (Integer.parseInt(pagoTF.getText())-total));
                    DBManager.cargarVenta(folioVenta,total, pagoTF.getText(), (Integer.parseInt(pagoTF.getText())-total), id.getText());
                    DBManager.registroDetallesVenta(productos);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"¡Venta realizada con exito!");
                    alert.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Lo sentimos el pago no es suficiente");
                    alert.show();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "El campo de pago está vacio");
                alert.show();
            }
        }
         else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "EL CARRITO ESTA VACIO");
            alert.show();
        }
    }

}
