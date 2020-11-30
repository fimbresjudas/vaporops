package Controladores;

import Modelos.DBManager;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller_nuevoDistribuidor {
    @FXML private AnchorPane nuevoDistribuidor;
    @FXML private Label id, nombre;
    @FXML private TextField codigoDis, nombreDis;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        Ventanas.v.numericOnly(codigoDis);
    }

    public void atrasAlmacen(){
        Ventanas.v.almacen(nuevoDistribuidor);
    }

    public void aceptarAltaDis(){
        if (!codigoDis.getText().equals("") && !nombreDis.getText().equals("")){
            if(!DBManager.codigoExistenteDistribuidor(codigoDis.getText())) {
                DBManager.registrarDistribuidor(codigoDis.getText(), nombreDis.getText().toUpperCase());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "El distribuidor fue agregado con exito");
                alert.show();
                Ventanas.v.almacen(nuevoDistribuidor);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El codigo ingresado ya existe");
                alert.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Hay campos vacios :(");
            alert.show();
        }
    }
}
