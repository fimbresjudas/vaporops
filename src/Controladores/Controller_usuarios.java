package Controladores;

import Modelos.DBManager;
import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.beans.property.SimpleStringProperty;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_usuarios{
    @FXML private Label id, nombre;
    @FXML private AnchorPane usuarios;
    @FXML private TableView<Usuario> tabla_usuarios;
    @FXML private TableColumn<Usuario, String> codigoCol = new TableColumn<>();
    @FXML private TableColumn<Usuario, String> nombreCol = new TableColumn<>();
    @FXML private TableColumn<Usuario, String> tipo_usuarioCol = new TableColumn<>();
    @FXML private TableColumn<Usuario, String> telefonoCol = new TableColumn<>();

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        System.out.println("Hola");
        tabla_usuarios.setItems((ObservableList<Usuario>) DBManager.registroUsuarios());
        codigoCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
        tipo_usuarioCol.setCellValueFactory(cellData -> cellData.getValue().getTipoUsuarioProperty());
        telefonoCol.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());

    }


    public void atrasPanelPrincipal (){
        Ventanas.v.cargarPanelPrincipal(usuarios);
    }

    public void altaNuevoUsuario() {
        Ventanas.v.nuevoUsuario(usuarios);
    }
}
