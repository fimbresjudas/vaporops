package Modelos;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ventanas {

   public static Ventanas v = new Ventanas();
    public static Usuario user;
    public static Usuario usuarioDetalles;
    public static Producto nuevoproducto;
    public static Producto productoIngreso;
    public static Producto productoDetalles;
    public static Venta detalles_de_venta;


    public void numericOnly(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void initialize(Label id, Label nombre) {
        id.setText(String.valueOf(user.getId()));
        nombre.setText(user.getNombre());
    }

    public static void crearUsuario(String usuario) {
        Usuario.user = new Usuario(usuario);
        user = Usuario.user;
    }

    public void cargarPanelPrincipal(AnchorPane login){
        try {
            AnchorPane panelPrincipal;
            switch (user.getTipo_usuario()) {
                case "ADMINISTRADOR":
                    panelPrincipal = FXMLLoader.load(getClass().getResource("../Vistas/principal_admin.fxml"));
                    break;
                case "JEFE DE CAJEROS":
                    panelPrincipal = FXMLLoader.load(getClass().getResource("../Vistas/principal_jefe_cajero.fxml"));
                    break;
                case "CAJERO":
                    panelPrincipal = FXMLLoader.load(getClass().getResource("../Vistas/principal_cajero.fxml"));
                    break;
                default:
                    panelPrincipal = FXMLLoader.load(getClass().getResource("../Vistas/login.fxml"));
                    break;
            }
            login.getChildren().setAll(panelPrincipal);
        }
        catch (Exception e){
            System.out.println(e);

        }
    }

    public void cerrarSesion(AnchorPane pane){
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("../Vistas/login.fxml"));
            pane.getChildren().setAll(login);
            user = null;
        }
        catch (Exception e){

        }
    }

    public void almacen(AnchorPane pane){
        try {
            AnchorPane almacen = FXMLLoader.load(getClass().getResource("../Vistas/almacen.fxml"));
            pane.getChildren().setAll(almacen);
        }
        catch (Exception e){
            System.out.println(e);

        }
    }

    public void ventas(AnchorPane pane){
        try {
            AnchorPane ventas = FXMLLoader.load(getClass().getResource("../Vistas/ventas.fxml"));
            pane.getChildren().setAll(ventas);
        }
        catch (Exception e){
        }
    }

    public void usuarios(AnchorPane pane) {
        try {
            AnchorPane usuarios = FXMLLoader.load(getClass().getResource("../Vistas/usuarios.fxml"));
            pane.getChildren().setAll(usuarios);
        }
        catch (Exception e){

        }
    }

    public void registros(AnchorPane pane) {
        try {
            AnchorPane registros = FXMLLoader.load(getClass().getResource("../Vistas/registros.fxml"));
            pane.getChildren().setAll(registros);
        }
        catch (Exception e){
        }

    }

    public void buscar_Producto(AnchorPane pane) {
        try {
            AnchorPane buscar_Producto = FXMLLoader.load(getClass().getResource("../Vistas/buscar_producto.fxml"));
            pane.getChildren().setAll(buscar_Producto);
        }
        catch (Exception e){

        }
    }

    public void nuevoUsuario(AnchorPane pane) {
        try {
            AnchorPane nuevoUsuario = FXMLLoader.load(getClass().getResource("../Vistas/nuevo_usuario.fxml"));
            pane.getChildren().setAll(nuevoUsuario);

        }
        catch (Exception e){
        }
    }

    public void ingresarContraseña(AnchorPane pane) {
        try {
            AnchorPane ingresarContraseña = FXMLLoader.load(getClass().getResource("../Vistas/contraseña_usuario.fxml"));
            pane.getChildren().setAll(ingresarContraseña);

        }
        catch (Exception e){
        }
    }

    public void cargarDetallesUsuario(AnchorPane pane) {
        try {
            AnchorPane detallesUsuario = FXMLLoader.load(getClass().getResource("../Vistas/detalles_usuario.fxml"));
            pane.getChildren().setAll(detallesUsuario);

        }
        catch (Exception e){
        }
    }

    public void editarUsuario(AnchorPane pane) {
        try {
            AnchorPane editarUsuario = FXMLLoader.load(getClass().getResource("../Vistas/editar_usuario.fxml"));
            pane.getChildren().setAll(editarUsuario);

        }
        catch (Exception e){
        }

    }

    public void editarContraseña(AnchorPane pane) {
        try {
            AnchorPane editarContraseña = FXMLLoader.load(getClass().getResource("../Vistas/editar_contraseña.fxml"));
            pane.getChildren().setAll(editarContraseña);

        }
        catch (Exception e){
        }
    }

    public void nuevoProducto(AnchorPane pane) {
        try {
            AnchorPane nuevoProducto = FXMLLoader.load(getClass().getResource("../Vistas/nuevo_producto.fxml"));
            pane.getChildren().setAll(nuevoProducto);

        }
        catch (Exception e){
        }
    }

    public void nuevoDistribuidor(AnchorPane pane) {
        try {
            AnchorPane nuevoDistribuidor = FXMLLoader.load(getClass().getResource("../Vistas/nuevo_distribuidor.fxml"));
            pane.getChildren().setAll(nuevoDistribuidor);

        }
        catch (Exception e){
        }
    }

    public void nuevoIngresoProducto(AnchorPane pane) {
        try {
            AnchorPane nuevoIngreso = FXMLLoader.load(getClass().getResource("../Vistas/ingreso_stock.fxml"));
            pane.getChildren().setAll(nuevoIngreso);

        }
        catch (Exception e){
        }
    }

    public void detallesProducto(AnchorPane pane) {
        try {
            AnchorPane detallesProducto = FXMLLoader.load(getClass().getResource("../Vistas/detalles_producto.fxml"));
            pane.getChildren().setAll(detallesProducto);

        }
        catch (Exception e){
        }
    }

    public void cargaRegistrosVentas(AnchorPane pane) {
        try {
            AnchorPane registroVentas = FXMLLoader.load(getClass().getResource("../Vistas/registro_ventas.fxml"));
            pane.getChildren().setAll(registroVentas);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void cargaRegistrosIngreso(AnchorPane pane) {
        try {
            AnchorPane registroIngreso = FXMLLoader.load(getClass().getResource("../Vistas/registros_ingresos.fxml"));
            pane.getChildren().setAll(registroIngreso);

        }
        catch (Exception e){
        }
    }

    public void buscarProducto_ventas(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Vistas/buscar_producto_ventas.fxml"));
            Stage secondStage = new Stage();
            secondStage.setTitle("Vapor Ops: Punto de Venta");
            secondStage.setScene(new Scene(root, 700, 500));
            secondStage.setResizable(false);
            secondStage.show();

        }
        catch (Exception e){

        }
    }

    public void cargarDetallesVenta(AnchorPane pane) {
        try {
            AnchorPane detalles_ventas = FXMLLoader.load(getClass().getResource("../Vistas/detalles_ventas.fxml"));
            pane.getChildren().setAll(detalles_ventas);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
