package Controladores;

import Modelos.DBManager;
import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class Controller_nuevoUsuario {
    ObservableList<String> tipos_usuario = FXCollections.observableArrayList("ADMINISTRADOR", "JEFE DE CAJEROS", "CAJERO");
    @FXML private Label id, nombre;
    @FXML private AnchorPane nuevoUsuario;
    @FXML private TextField codigoTf, nombreTf, usuarioTf, telefonoTf;
    @FXML private TextArea  direccionTa;
    @FXML private ComboBox tipo_usuarioC = new ComboBox();

    public void initialize(){
        tipo_usuarioC.setItems(tipos_usuario);
        tipo_usuarioC.setVisibleRowCount(3);
        Ventanas.v.initialize(id,nombre);
        Ventanas.v.numericOnly(codigoTf);
        Ventanas.v.numericOnly(telefonoTf);
    }


    public void atrasPanelUsuarios(){
        Ventanas.v.usuarios(nuevoUsuario);
    }

    public void ingresarContraseña() {
        if (validacionVacios()) {
            if (!existenciaUsuario()) {
                Usuario.usuarioNuevo = new Usuario(Long.parseLong(codigoTf.getText()), nombreTf.getText().toUpperCase(), usuarioTf.getText(), direccionTa.getText().toUpperCase(),  telefonoTf.getText(), ((String) tipo_usuarioC.getValue()).toUpperCase());
                Ventanas.v.ingresarContraseña(nuevoUsuario);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"El codigo ingresado ya existe");
                alert.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Hay campos vacios :(");
            alert.show();
        }
    }

    private boolean existenciaUsuario() {
        if (DBManager.codigoExistente(codigoTf.getText())){
            return true;
        }

        return false;
    }

    private boolean validacionVacios() {

        if (!codigoTf.getText().equals("") && !nombreTf.getText().equals("") && !usuarioTf.getText().equals("") && !telefonoTf.getText().equals("") && !direccionTa.getText().equals("") && tipo_usuarioC.getValue()!=null){
            return true;
        }
        else {
            return false;
        }
    }
}
