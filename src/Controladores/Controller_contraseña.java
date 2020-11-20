package Controladores;

import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller_contraseña {
    @FXML Label id, nombre;
    @FXML AnchorPane contraseñaUsuario;
    @FXML PasswordField contraseña, contraseñaDos;

    public void initialize() {
        Ventanas.v.initialize(id, nombre);
    }

    public void atrasPanelNuevoUsuario() {
        Ventanas.v.nuevoUsuario(contraseñaUsuario);
    }

    public void atrasPanelUsuarios() {
        Ventanas.v.usuarios(contraseñaUsuario);
    }


    public void altaTotal() {
        if (contraseña.getText().equals(contraseñaDos.getText())){
            Usuario.usuarioNuevo.altaNuevoUsuario(contraseña.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"USUARIO CREADO CON EXITO");
            alert.show();
            atrasPanelUsuarios();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Las contraseñas son diferentes");
            alert.show();
        }
    }
}
