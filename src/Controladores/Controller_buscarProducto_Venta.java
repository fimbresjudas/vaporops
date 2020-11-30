package Controladores;

import Modelos.DBManager;
import Modelos.Producto;
import Modelos.Ventanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_buscarProducto_Venta {
    @FXML Label id, nombre;
    @FXML TextField buscarTF;
    @FXML TableView<Producto> productosT = new TableView<>();
    @FXML TableColumn<Producto, String> codigoC = new TableColumn<>();
    @FXML TableColumn <Producto, String> nombreC = new TableColumn<>();
    @FXML TableColumn <Producto, String> precio_vC = new TableColumn<>();
    @FXML TableColumn <Producto, String> cantidadActualC = new TableColumn<>();
    ObservableList<Producto> productos = FXCollections.observableArrayList();

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        productos = DBManager.registroProductos();
        productosT.setItems(productos);
        codigoC.setCellValueFactory(cellData -> cellData.getValue().idSimpleProperty());
        nombreC.setCellValueFactory(cellData -> cellData.getValue().nombreSimpleProperty());
        precio_vC.setCellValueFactory(cellData -> cellData.getValue().precioSimpleProperty());
        cantidadActualC.setCellValueFactory(cellData -> cellData.getValue().stockActualSimpleProperty());
    }

    public void buscarProducto(){
        productos = DBManager.registroProductos(buscarTF.getText().toUpperCase());
        productosT.setItems(productos);
        codigoC.setCellValueFactory(cellData -> cellData.getValue().idSimpleProperty());
        nombreC.setCellValueFactory(cellData -> cellData.getValue().nombreSimpleProperty());
        precio_vC.setCellValueFactory(cellData -> cellData.getValue().precioSimpleProperty());
        cantidadActualC.setCellValueFactory(cellData -> cellData.getValue().stockActualSimpleProperty());
    }
}
