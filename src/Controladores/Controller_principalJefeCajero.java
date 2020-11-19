package Controladores;

import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller_principalJefeCajero {
    @FXML
    Label id, nombre;
    @FXML
    AnchorPane paneJefeCajero;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
    }

    public void cerrarSesion(){
        Ventanas.v.cerrarSesion(paneJefeCajero);
    }
}
