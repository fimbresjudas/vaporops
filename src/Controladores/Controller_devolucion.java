package Controladores;

import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller_devolucion {
    @FXML Label id, nombre;
    @FXML AnchorPane devolucion;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
    }


    public void atrasPanelPrincipal (){
        Ventanas.v.cargarPanelPrincipal(devolucion);
    }
}
