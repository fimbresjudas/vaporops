package Modelos;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.util.locale.provider.HostLocaleProviderAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

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
}
