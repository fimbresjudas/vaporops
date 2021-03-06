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

public class Controller_buscarProducto {
    @FXML private Label id, nombre;
    @FXML private AnchorPane buscar_Producto;
    @FXML private TextField buscarTF;
    @FXML private TableView<Producto> productosT = new TableView<>();
    @FXML private TableColumn <Producto, String> codigoC = new TableColumn<>();
    @FXML private TableColumn <Producto, String> nombreC = new TableColumn<>();
    @FXML private TableColumn <Producto, String> precio_vC = new TableColumn<>();
    @FXML private TableColumn <Producto, String> cantidadActualC = new TableColumn<>();
    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        productos = DBManager.registroProductos();
        productosT.setItems(productos);
        codigoC.setCellValueFactory(cellData -> cellData.getValue().idSimpleProperty());
        nombreC.setCellValueFactory(cellData -> cellData.getValue().nombreSimpleProperty());
        precio_vC.setCellValueFactory(cellData -> cellData.getValue().precioSimpleProperty());
        cantidadActualC.setCellValueFactory(cellData -> cellData.getValue().stockActualSimpleProperty());
        productosT.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2){
                    detallesProducto(productosT.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void detallesProducto(Producto producto) {
        Ventanas.productoDetalles = producto;
        Ventanas.v.detallesProducto(buscar_Producto);
    }


    public void atrasPanelPrincipal (){
        Ventanas.v.cargarPanelPrincipal(buscar_Producto);
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
