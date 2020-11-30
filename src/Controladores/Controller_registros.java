package Controladores;

import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller_registros {
    @FXML private Label id, nombre;
    @FXML private AnchorPane registros;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
    }

    public void atrasPanelPrincipal (){
        Ventanas.v.cargarPanelPrincipal(registros);
    }

    public void registroVentas(){
        Ventanas.v.cargaRegistrosVentas(registros);
    }

    public void registroIngreso(){
        Ventanas.v.cargaRegistrosIngreso(registros);
    }
}
