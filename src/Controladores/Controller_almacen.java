package Controladores;

import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller_almacen {
    @FXML AnchorPane almacen;
    @FXML Label id, nombre;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
    }

    public void atrasPanelPrincipal (){
        Ventanas.v.cargarPanelPrincipal(almacen);
    }

    public void nuevoProducto(){
        Ventanas.v.nuevoProducto(almacen);
    }

    public void nuevoDistribuidor(){
        Ventanas.v.nuevoDistribuidor(almacen);
    }

    public void nuevoIngreso(){
        Ventanas.v.nuevoIngresoProducto(almacen);
    }
}
