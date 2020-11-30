package Controladores;

import Modelos.DBManager;
import Modelos.Detalles_Ventas;
import Modelos.Venta;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Controller_detalles_venta {
    @FXML private Label id, nombre;
    @FXML private AnchorPane detalles_venta;
    @FXML private Label numeroVenta, totalLbl, efectivoLbl, cambioLbl;
    @FXML private TableView<Detalles_Ventas> ventasT = new TableView<>();
    @FXML private TableColumn<Detalles_Ventas, String> cantidadCol = new TableColumn<>();
    @FXML private TableColumn<Detalles_Ventas, String> nombreCol = new TableColumn<>();
    @FXML private TableColumn<Detalles_Ventas, String> precioUnitarioCol = new TableColumn<>();
    @FXML private TableColumn<Detalles_Ventas, String> totalCol = new TableColumn<>();

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        numeroVenta.setText(numeroVenta.getText() + Ventanas.detalles_de_venta.getId());
        totalLbl.setText(totalCol.getText()+Ventanas.detalles_de_venta.getTotal());
        cambioLbl.setText(cambioLbl.getText()+Ventanas.detalles_de_venta.getCambio());
        efectivoLbl.setText(efectivoLbl.getText()+Ventanas.detalles_de_venta.getEfectivo());
        ventasT.setItems(DBManager.registroDetallesdeVenta(Ventanas.detalles_de_venta.getId()));
        cantidadCol.setCellValueFactory(cellData -> cellData.getValue().cantidadSimpleProperty());
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().productoSimpleProperty());
        precioUnitarioCol.setCellValueFactory(cellData -> cellData.getValue().precioProductoSimpleProperty());
        totalCol.setCellValueFactory(cellData -> cellData.getValue().totalDetallesSimpleProperty());
    }

    public void atrasVentas (){
        Ventanas.v.cargaRegistrosVentas(detalles_venta);
    }
}
