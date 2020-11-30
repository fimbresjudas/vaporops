package Controladores;

import Modelos.DBManager;
import Modelos.Ingreso;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Controller_registroIngreso {
    @FXML private Label id, nombre;
    @FXML private AnchorPane registroIngreso;
    @FXML private TableView<Ingreso>  registroIngresosT = new TableView<>();
    @FXML private TableColumn<Ingreso, String> codigoCol = new TableColumn<>();
    @FXML private TableColumn<Ingreso, String> productoCol = new TableColumn<>();
    @FXML private TableColumn<Ingreso, String> precioCol = new TableColumn<>();
    @FXML private TableColumn<Ingreso, String> cantidadCol = new TableColumn<>();
    @FXML private TableColumn<Ingreso, String> fechaCol = new TableColumn<>();

    public void initialize() {
        Ventanas.v.initialize(id, nombre);
        registroIngresosT.setItems(DBManager.registrosIngresos());
        codigoCol.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        productoCol.setCellValueFactory(cellData -> cellData.getValue().productoProperty());
        precioCol.setCellValueFactory(cellData -> cellData.getValue().precioIngresoProperty());
        cantidadCol.setCellValueFactory(cellData -> cellData.getValue().cantidadIngresoProperty());
        fechaCol.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
    }

    public void atrasRegistro (){
        Ventanas.v.registros(registroIngreso);
    }
}
