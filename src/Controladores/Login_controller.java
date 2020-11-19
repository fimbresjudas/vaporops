package Controladores;

import Modelos.DBManager;
import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Login_controller {
    @FXML
    TextField usuario;
    @FXML
    PasswordField password;
    @FXML
    Button iniciar;
    @FXML
    AnchorPane login;


    public void validacion() {
        Boolean validacion = DBManager.validacionLogin(usuario.getText(), password.getText());
        if (validacion) {
            Ventanas.crearUsuario(usuario.getText());
            Ventanas.v.cargarPanelPrincipal(login);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Hay un error en el usuario o en la contraseña");
            alert.show();
        }
    }

}
