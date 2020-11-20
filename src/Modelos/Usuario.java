package Modelos;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;

public class Usuario {
    public static Usuario user, usuarioNuevo;
    private long id;
    private String nombre,usuario, direccion, telefono, tipo_usuario;
    private SimpleStringProperty idP = new SimpleStringProperty();
    private SimpleStringProperty nombreP = new SimpleStringProperty();
    private SimpleStringProperty usuarioP = new SimpleStringProperty();
    private SimpleStringProperty direccionP = new SimpleStringProperty();
    private SimpleStringProperty telefonoP = new SimpleStringProperty();
    private SimpleStringProperty tipo_usuarioP = new SimpleStringProperty();



    public Usuario(String username) {
        this.usuario = username;
        cargarInfromacion();
    }

    public Usuario(long id, String nombre, String usuario, String direccion, String telefono, String tipo_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo_usuario = tipo_usuario;
        this.idP.set(String.valueOf(id));
        this.nombreP.set(nombre);
        this.usuarioP.set(usuario);
        this.direccionP.set(direccion);
        this.telefonoP.set(telefono);
        this.tipo_usuarioP.set(tipo_usuario);
    }

    private void cargarInfromacion() {
        try {
            ResultSet result = DBManager.datosUsuario(usuario);
            id = result.getInt("codigo");
            nombre = result.getString("nombre");
            direccion = result.getString("direccion");
            telefono = result.getString("telefono");
            tipo_usuario = result.getString("tipo_usuario");
        }catch (Exception e){

        }
    }

    public void altaNuevoUsuario(String contraseña){
        DBManager.altaUsuario(contraseña);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public static Usuario getUser() {
        return user;
    }

    public SimpleStringProperty idPProperty() {
        return idP;
    }

    public SimpleStringProperty nombrePProperty() {
        return nombreP;
    }

    public SimpleStringProperty telefonoPProperty() {
        return telefonoP;
    }

    public SimpleStringProperty tipo_usuarioPProperty() {
        return tipo_usuarioP;
    }
}
