package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DBManager {
    static String user = "postgres";
    static String password= "Unisierra20";


    private static Connection connection;

    public static void initConecction(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vaporops",user,password);
            System.out.println("Conexión lista");
        }
        catch (Exception e){

        }
    }


    public static boolean validacionLogin(String user, String password) {
        Boolean bandera = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT usuario, pgp_sym_decrypt(contraseña::bytea,'AES_KEY') FROM public.usuario");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getString(1).equals(user) && result.getString(2).equals(password)){
                    bandera = true;
                }
            }
        }
        catch (Exception e){
        }
        return bandera;
    }


    public static ResultSet datosUsuario(String user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT u.codigo, u.usuario, u.nombre, tu.nombre_tipo as \"tipo_usuario\", u.telefono, u.direccion FROM usuario u inner join tipo_usuario tu on u.tipo_usuario = tu.codigo_tipo");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                if (result.getString("usuario").equals(user)){
                    return result;
                }
            }
        }
        catch (Exception e){
        }
        return null;
    }

    public static boolean codigoExistente(String codigo) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT codigo FROM usuario");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                if (result.getString("codigo").equals(codigo)){
                    return true;

                }
            }
            return false;
        }
        catch (Exception e){
        }
        return true;
    }

    public static void altaUsuario(String contraseña) {
        try {
            int tipo_usuario = tipoUsuario(Usuario.usuarioNuevo);
            if (tipo_usuario !=-1) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO USUARIO (codigo, nombre, usuario, contraseña, tipo_usuario, telefono, direccion) VALUES(" + Usuario.usuarioNuevo.getId() + ", '" + Usuario.usuarioNuevo.getNombre() + "', '" + Usuario.usuarioNuevo.getUsuario() + "', PGP_SYM_ENCRYPT('" + contraseña + "','AES_KEY'), " + tipo_usuario + ", '" + Usuario.usuarioNuevo.getTelefono() + "', '" + Usuario.usuarioNuevo.getDireccion() + "')");
                statement.execute();
            }
        }
        catch (Exception e){
        }
    }

    private static int tipoUsuario(Usuario usuario) {
        switch (usuario.getTipo_usuario()){
            case "ADMINISTRADOR":
                return 1;
            case "JEFE DE CAJEROS":
                return 2;
            case "CAJERO":
                return 3;
        }

        return -1;
    }

    public static ObservableList<Usuario> registroUsuarios() {
        try{
            ObservableList<Usuario> registroUsuarios = FXCollections.observableArrayList();;
            PreparedStatement statement = connection.prepareStatement("SELECT u.codigo, u.usuario, u.nombre, tu.nombre_tipo as \"tipo_usuario\", u.telefono, u.direccion FROM usuario u inner join tipo_usuario tu on u.tipo_usuario = tu.codigo_tipo");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                long id = result.getLong("codigo");
                String usuario = result.getString("usuario");
                String nombre = result.getString("nombre");
                String tipo_usuario = result.getString("tipo_usuario");
                String telefono = result.getString("telefono");
                String direccion = result.getString("direccion");
                registroUsuarios.add(new Usuario(id,nombre,usuario,direccion,telefono,tipo_usuario));
            }
            return registroUsuarios;
        }
        catch (Exception e){
        }
        return null;
    }

    public static void eliminarUsuario(String codigo) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM usuario where codigo = '"+codigo+"'");
            statement.executeQuery();
        }
        catch (Exception e){
        }
    }

    public static void editarUsuario(Usuario usuarioDetalles) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE usuario set nombre ='"+usuarioDetalles.getNombre()+"', usuario = '"+usuarioDetalles.getUsuario()+"', tipo_usuario = "+tipoUsuario(usuarioDetalles)+", telefono = '"+usuarioDetalles.getTelefono()+"', direccion = '"+usuarioDetalles.getDireccion()+"' where codigo = '"+usuarioDetalles.getId()+"'");
            statement.executeQuery();
        }
        catch (Exception e){
        }
    }

    public static void editarUsuario_Contraseña(Usuario usuarioDetalles, String contraseña) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE usuario set nombre ='"+usuarioDetalles.getNombre()+"', usuario = '"+usuarioDetalles.getUsuario()+"', tipo_usuario = "+tipoUsuario(usuarioDetalles)+", telefono = '"+usuarioDetalles.getTelefono()+"', direccion = '"+usuarioDetalles.getDireccion()+"', contraseña = PGP_SYM_ENCRYPT('"+contraseña+"','AES_KEY') where codigo = '"+usuarioDetalles.getId()+"'");
            statement.executeQuery();
        }
        catch (Exception e){
        }
    }

    public static void registrarDistribuidor(String codigo, String nombre) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO distribuidor (codigo, nombre) VALUES ("+codigo+",'"+nombre+"')");
            statement.executeQuery();
        }
        catch (Exception e){
        }
    }

    public static boolean codigoExistenteDistribuidor(String codigo) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT codigo FROM distribuidor");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                if (result.getString("codigo").equals(codigo)){
                    return true;

                }
            }
            return false;
        }
        catch (Exception e){
        }
        return true;
    }

    public static ObservableList<String> distribuidores() {
        try{
            ObservableList<String> registroDistribuidores = FXCollections.observableArrayList();;
            PreparedStatement statement = connection.prepareStatement("SELECT * from distribuidor");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                registroDistribuidores.add(result.getString("nombre"));
            }
            return registroDistribuidores;
        }
        catch (Exception e){
        }
        return null;
    }

    public static boolean codigoExistenteProducto(String codigo) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT codigo FROM producto");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                if (result.getString("codigo").equals(codigo)){
                    return true;

                }
            }
            return false;
        }
        catch (Exception e){
        }
        return true;
    }

    public static void agregarProductoNuevo(Producto nuevoProducto) {
        try {
            long ditribuidor = numeroDistribuidor(nuevoProducto.getDistribuidor());
            if (ditribuidor > 0) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO producto (codigo, nombre, codigo_distribuidor, descripcion, precio_venta, stock_total, stock_actual) VALUES ("+nuevoProducto.getCodigo()+", '"+nuevoProducto.getNombre()+"', "+ditribuidor+", '"+nuevoProducto.getDetalles()+"', "+nuevoProducto.getPrecioVenta()+", + "+nuevoProducto.getStock_actual()+", "+nuevoProducto.getStock_total()+")");
                statement.executeQuery();
            }
        }
        catch (Exception e){
        }
    }

    private static long numeroDistribuidor(String distribuidor) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM distribuidor where nombre = '"+distribuidor+"'");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                return result.getLong("codigo");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }

    public static boolean datosProducto(String codigoP) {
        try {
            PreparedStatement statement = connection.prepareStatement("select p.codigo, p.nombre, d.nombre as \"distribuidor\", p.descripcion, p.precio_venta, p.stock_total, p.stock_actual from producto p inner join distribuidor d on p.codigo_distribuidor = d.codigo where p.codigo ="+codigoP);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Ventanas.productoIngreso=new Producto(result.getLong("codigo"), result.getString("nombre"), result.getString("descripcion"), result.getString("distribuidor"), result.getInt("precio_venta"), result.getInt("stock_total"), result.getInt("stock_actual"));
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public static void registarIngreso() {

    }

    public static void registarIngreso(int precio_ingreso, int cantidad_ingreso) {
        try {
            System.out.println("HOLAAA");
            Ventanas.productoIngreso.setStock_total(Ventanas.productoIngreso.getStock_actual() + cantidad_ingreso);
            Ventanas.productoIngreso.setStock_actual(Ventanas.productoIngreso.getStock_actual() + cantidad_ingreso);
            actualizacionStockProducto();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate fechaingreso =  LocalDate.now();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ingreso_stock (precio_ing, cantidad_ingreso, fecha_ingreso, codigo_producto) VALUES ("+precio_ingreso+", "+cantidad_ingreso+", '"+fechaingreso+"', "+Ventanas.productoIngreso.getCodigo()+")");
            statement.executeQuery();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private static void actualizacionStockProducto() {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE producto set stock_total =" + Ventanas.productoIngreso.getStock_total() + ", stock_actual = " + Ventanas.productoIngreso.getStock_actual() + " where codigo =" + Ventanas.productoIngreso.getCodigo());
            statement.executeQuery();
        }
        catch (Exception e){
            System.out.println(e+"HOLA");
        }
    }
}
