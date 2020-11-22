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

    public void registro_Ventas(){
        Ventanas.v.registro_Ventas(paneJefeCajero);
    }

    public void ventas(){
        Ventanas.v.ventas(paneJefeCajero);
    }

    public void buscar_Producto(){
        Ventanas.v.buscar_Producto(paneJefeCajero);
    }
}
