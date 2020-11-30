package Vistas;

import Modelos.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.swing.text.html.ImageView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Vistas/login.fxml"));
        primaryStage.setTitle("Vapor Ops: Punto de Venta");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        DBManager.initConecction();
        DBManager.connectionBitacora();
        launch(args);
    }

}
