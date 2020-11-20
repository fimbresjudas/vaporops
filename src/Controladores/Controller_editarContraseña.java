package Controladores;

import Modelos.DBManager;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class Controller_editarContraseña {
    @FXML Label id, nombre;
    @FXML AnchorPane contraseñaUsuario;
    @FXML PasswordField contraseña, contraseñaDos;

    public void initialize() {
        Ventanas.v.initialize(id, nombre);
    }

    public void editarContraseña(){
        if (contraseña.getText().equals(contraseñaDos.getText())) {
            DBManager.editarUsuario_Contraseña(Ventanas.usuarioDetalles, contraseña.getText());
            Ventanas.v.cerrarSesion(contraseñaUsuario);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"El usuario ha sido editado con exito");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Las contraseñas son diferentes");
            alert.show();
        }
    }

    public void atrasPanelDetallesUsuario(){
        Ventanas.v.cargarDetallesUsuario(contraseñaUsuario);
    }

    public void atrasPanelEditarUsuario(){
        Ventanas.v.editarUsuario(contraseñaUsuario);
    }

}
