package Controladores;

import Modelos.DBManager;
import Modelos.Producto;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.TabableView;

public class Controller_almacen {
    @FXML private AnchorPane almacen;
    @FXML private Label id, nombre;
    @FXML private TableView<Producto> almacenT = new TableView<>();
    @FXML private TableColumn<Producto, String> codigoC = new TableColumn<>();
    @FXML private TableColumn<Producto, String> nombreC = new TableColumn<>();
    @FXML private TableColumn<Producto, String> precioC = new TableColumn<>();
    @FXML private TableColumn<Producto, String> cantidadTotalC = new TableColumn<>();
    @FXML private TableColumn<Producto, String> cantidadActualC = new TableColumn<>();

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        almacenT.setItems(DBManager.registroProductos());
        codigoC.setCellValueFactory(cellData -> cellData.getValue().idSimpleProperty());
        nombreC.setCellValueFactory(cellData -> cellData.getValue().nombreSimpleProperty());
        precioC.setCellValueFactory(cellData -> cellData.getValue().precioSimpleProperty());
        cantidadTotalC.setCellValueFactory(cellData -> cellData.getValue().stockTotalSimpleProperty());
        cantidadActualC.setCellValueFactory(cellData -> cellData.getValue().stockActualSimpleProperty());
    }

    public void atrasPanelPrincipal (){
        Ventanas.v.cargarPanelPrincipal(almacen);
    }

    public void nuevoProducto(){
        Ventanas.v.nuevoProducto(almacen);
    }

    public void nuevoDistribuidor(){
        Ventanas.v.nuevoDistribuidor(almacen);
    }

    public void nuevoIngreso(){
        Ventanas.v.nuevoIngresoProducto(almacen);
    }
}
