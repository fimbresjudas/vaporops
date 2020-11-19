package Modelos;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Ventanas {

   public static Ventanas v = new Ventanas();
    private static Usuario user;

    public void initialize(Label id, Label nombre) {
        id.setText(String.valueOf(user.getId()));
        nombre.setText(user.getNombre());
    }

    public static void crearUsuario(String usuario) {
        Usuario.user = new Usuario(usuario);
        user = Usuario.user;
        System.out.println(user.getTipo_usuario());
    }

    public void cargarPanelPrincipal(AnchorPane login){
        try {
            AnchorPane panelPrincipal = null;
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

    public void registro_Ventas(AnchorPane pane) {
        try {
            AnchorPane registroVentas = FXMLLoader.load(getClass().getResource("../Vistas/registro_ventas.fxml"));
            pane.getChildren().setAll(registroVentas);
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

    public void devolucion(AnchorPane pane) {
        try {
            AnchorPane devolucion = FXMLLoader.load(getClass().getResource("../Vistas/devolucion.fxml"));
            pane.getChildren().setAll(devolucion);
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
}
