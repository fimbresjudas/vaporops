package Controladores;

import Modelos.Usuario;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller_principalAdmin {
    @FXML private Label id, nombre;
    @FXML private AnchorPane paneAdmin;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
    }

    public void cerrarSesion(){
        Ventanas.v.cerrarSesion(paneAdmin);
    }

    public void almacen(){
        Ventanas.v.almacen(paneAdmin);
    }

    public void ventas(){
        Ventanas.v.ventas(paneAdmin);
    }

    public void usuarios(){
        Ventanas.v.usuarios(paneAdmin);
    }

    public void registro_Ventas(){
        Ventanas.v.registro_Ventas(paneAdmin);
    }

    public void buscar_Producto(){
        Ventanas.v.buscar_Producto(paneAdmin);
    }
}
