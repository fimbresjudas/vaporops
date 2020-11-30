package Controladores;

import Modelos.DBManager;
import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller_editarUsuario {
    private ObservableList<String> tipos_usuario = FXCollections.observableArrayList("ADMINISTRADOR", "JEFE DE CAJEROS", "CAJERO");
    @FXML private Label id, nombre, codigoUsuario;
    @FXML private AnchorPane editarUsuario;
    @FXML private TextField nombreTf, usuarioTf, telefonoTf;
    @FXML private TextArea direccionTa;
    @FXML private ComboBox tipo_usuarioC = new ComboBox();

    public void initialize(){
        Ventanas.v.numericOnly(telefonoTf);
        tipo_usuarioC.setItems(tipos_usuario);
        tipo_usuarioC.setVisibleRowCount(3);
        Ventanas.v.initialize(id,nombre);
        codigoUsuario.setText(codigoUsuario.getText()+Ventanas.usuarioDetalles.getId());
        nombreTf.setText(Ventanas.usuarioDetalles.getNombre());
        usuarioTf.setText(Ventanas.usuarioDetalles.getUsuario());
        telefonoTf.setText(Ventanas.usuarioDetalles.getTelefono());
        direccionTa.setText(Ventanas.usuarioDetalles.getDireccion());
        switch (Ventanas.usuarioDetalles.getTipo_usuario()){
            case "ADMINISTRADOR" :
                tipo_usuarioC.setValue(tipos_usuario.get(0));
                break;
            case "JEFE DE CAJEROS" :
                tipo_usuarioC.setValue(tipos_usuario.get(1));
                break;
            case "CAJERO" :
                tipo_usuarioC.setValue(tipos_usuario.get(2));
                break;
        }
    }


    public void atrasPanelDetallesUsuario(){
        Ventanas.v.cargarDetallesUsuario(editarUsuario);
    }

    public void editarDatosUsuario() {
        if (validacionVacios()) {
               Ventanas.usuarioDetalles.setNombre(nombreTf.getText());
               Ventanas.usuarioDetalles.setUsuario(usuarioTf.getText());
               Ventanas.usuarioDetalles.setTelefono(telefonoTf.getText());
               Ventanas.usuarioDetalles.setDireccion(direccionTa.getText());
               Ventanas.usuarioDetalles.setTipo_usuario(tipo_usuarioC.getValue().toString());
               DBManager.editarUsuario(Ventanas.usuarioDetalles);
               Ventanas.v.usuarios(editarUsuario);
               Alert alert = new Alert(Alert.AlertType.INFORMATION,"El usuario ha sido editado con exito");
               alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Hay campos vacios :(");
            alert.show();
        }
    }

    public void editarContraseña(){
        if (validacionVacios()) {
            Ventanas.usuarioDetalles.setNombre(nombreTf.getText());
            Ventanas.usuarioDetalles.setUsuario(usuarioTf.getText());
            Ventanas.usuarioDetalles.setTelefono(telefonoTf.getText());
            Ventanas.usuarioDetalles.setDireccion(direccionTa.getText());
            Ventanas.usuarioDetalles.setTipo_usuario(tipo_usuarioC.getValue().toString());
            Ventanas.v.editarContraseña(editarUsuario);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Hay campos vacios :(");
            alert.show();
        }
    }

    private boolean validacionVacios() {

        if (!nombreTf.getText().equals("") && !usuarioTf.getText().equals("") && !telefonoTf.getText().equals("") && !direccionTa.getText().equals("") && tipo_usuarioC.getValue()!=null){
            return true;
        }
        else {
            return false;
        }
    }
}
