package Controladores;

import Modelos.DBManager;
import Modelos.Ventanas;
import Modelos.Venta;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller_registroVentas {
    @FXML private Label id, nombre;
    @FXML private AnchorPane registroVentas;
    @FXML private TableView<Venta> ventasTable = new TableView<>();
    @FXML private TableColumn<Venta, String> codigoCol = new TableColumn<>();
    @FXML private TableColumn<Venta, String> fechaCol = new TableColumn<>();
    @FXML private TableColumn<Venta, String> totalCol = new TableColumn<>();
    @FXML private TableColumn<Venta, String> efectivoCol = new TableColumn<>();
    @FXML private TableColumn<Venta, String> cambioCol = new TableColumn<>();
    @FXML private TableColumn<Venta, String> usuarioCol = new TableColumn<>();

    public void initialize() {
        Ventanas.v.initialize(id, nombre);
        ventasTable.setItems(DBManager.registroVentas());
        codigoCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        fechaCol.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        totalCol.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
        efectivoCol.setCellValueFactory(cellData -> cellData.getValue().efectivoProperty());
        cambioCol.setCellValueFactory(cellData -> cellData.getValue().cambioProperty());
        usuarioCol.setCellValueFactory(cellData -> cellData.getValue().empleadoVentaProperty());
        Ventanas.detalles_de_venta = null;
        ventasTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    detallesVenta(ventasTable.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void detallesVenta(Venta venta) {
        Ventanas.detalles_de_venta = venta;
        Ventanas.v.cargarDetallesVenta(registroVentas);
    }

    public void atrasRegistro (){
        Ventanas.v.registros(registroVentas);
    }

}
