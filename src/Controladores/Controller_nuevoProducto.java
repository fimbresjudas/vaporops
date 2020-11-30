package Controladores;

import Modelos.DBManager;
import Modelos.Producto;
import Modelos.Ventanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller_nuevoProducto {
    @FXML private AnchorPane nuevoProducto;
    @FXML private Label id, nombre;
    @FXML private TextField codigoP, nombreP, precioventaP;
    @FXML private TextArea detallesP;
    @FXML private ComboBox distribuidorC = new ComboBox();
    private ObservableList<String> distribuidores;


    public void initialize(){
        distribuidores = DBManager.distribuidores();
        Ventanas.v.initialize(id,nombre);
        distribuidorC.setItems(distribuidores);
        Ventanas.v.numericOnly(codigoP);
        Ventanas.v.numericOnly(precioventaP);
    }

    public void atrasAlmacen(){
        Ventanas.v.almacen(nuevoProducto);
    }

    public void agregarNuevoProducto (){
        if (!codigoP.getText().equals("") && !nombreP.getText().equals("") && !precioventaP.getText().equals("") && !detallesP.getText().equals("") && distribuidorC.getValue()!=null){
            if (!DBManager.codigoExistenteProducto(codigoP.getText())){
               Ventanas.nuevoproducto = new Producto(Long.parseLong(codigoP.getText()), nombreP.getText().toUpperCase(), detallesP.getText().toUpperCase(), ((String) distribuidorC.getValue()).toUpperCase(), Integer.parseInt(precioventaP.getText()) ,0, 0);
                DBManager.agregarProductoNuevo(Ventanas.nuevoproducto);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Producto agregado con exito");
                alert.show();
                atrasAlmacen();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ese codigo de producto ya existe");
                alert.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "HAY CAMPOS VACIOS :(");
            alert.show();
        }
    }
}
