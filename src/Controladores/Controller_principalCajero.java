package Controladores;

import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller_principalCajero {
    @FXML Label id, nombre;
    @FXML AnchorPane panePrincipalCajero;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);;
    }

    public void cerrarSesion(){
        Ventanas.v.cerrarSesion(panePrincipalCajero);
    }

    public void ventas(){
        Ventanas.v.ventas(panePrincipalCajero);
    }

    public void buscar_Producto(){
        Ventanas.v.buscar_Producto(panePrincipalCajero);
    }

}
