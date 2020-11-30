package Controladores;

import Modelos.DBManager;
import Modelos.Ventanas;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller_nuevoIngreso {
    @FXML private AnchorPane ingreso;
    @FXML private Label id, nombre, nombreP, distribuidorP;
    @FXML private TextField codigoP, precioIngreso, cantidadIngreso;
    private Boolean bandera = false;

    public void initialize(){
        Ventanas.v.initialize(id,nombre);
        Ventanas.v.numericOnly(codigoP);
        Ventanas.v.numericOnly(precioIngreso);
        Ventanas.v.numericOnly(cantidadIngreso);
    }

    public void atrasAlmacen(){
        Ventanas.v.almacen(ingreso);
    }

    public void buscar() {
        if (!codigoP.getText().equals("")) {
            if (DBManager.datosProducto(codigoP.getText())) {
                bandera = true;
                nombreP.setText(nombreP.getText() + Ventanas.productoIngreso.getNombre());
                distribuidorP.setText(distribuidorP.getText() + Ventanas.productoIngreso.getDistribuidor());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "HAY CAMPOS VACIOS :(");
                alert.show();
            }
        }
    }

    public void aceptarIngreso() {
        if (!precioIngreso.getText().equals("") && !cantidadIngreso.getText().equals("")) {
            if (bandera) {
                DBManager.registarIngreso(Integer.parseInt(precioIngreso.getText()), Integer.parseInt(cantidadIngreso.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ingreso realizado con exito");
                alert.show();
                atrasAlmacen();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No hemos seleccionado ningun producto");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hay cambos vacios :(");
            alert.show();
        }
    }
}
