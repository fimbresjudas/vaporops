package Controladores;

import Modelos.DBManager;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class Controller_detallesUsuario {
    @FXML private Label id, nombre, codigolbl, nombrelbl, usuariolbl, telefonolbl, direccionlbl;
    @FXML private AnchorPane detallesUsuario;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        codigolbl.setText(codigolbl.getText() + Ventanas.usuarioDetalles.getId());
        nombrelbl.setText(nombrelbl.getText() + Ventanas.usuarioDetalles.getNombre());
        usuariolbl.setText(usuariolbl.getText() + Ventanas.usuarioDetalles.getUsuario());
        telefonolbl.setText(telefonolbl.getText() + Ventanas.usuarioDetalles.getTelefono());
        direccionlbl.setText(direccionlbl.getText() + Ventanas.usuarioDetalles.getDireccion());
    }

    public void atrasPanelUsuarios(){
        Ventanas.v.usuarios(detallesUsuario);
    }

    public void editarUsuario(){
        Ventanas.v.editarUsuario(detallesUsuario);
    }

    public void eliminarUsuario(){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que desea eliminar el usuario?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.get() == ButtonType.OK) {
            DBManager.eliminarUsuario(String.valueOf(Ventanas.usuarioDetalles.getId()));
            Ventanas.v.usuarios(detallesUsuario);
        }
    }
}
