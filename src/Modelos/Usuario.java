package Modelos;

import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;

public class Usuario {
    public static Usuario user, usuarioNuevo;
    private long id;
    private String nombre,usuario, direccion, telefono, tipo_usuario;

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
    }

    private void cargarInfromacion() {
        try {
            System.out.println("hola");
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

    public String getIdString(){
        return String.valueOf(id);
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public SimpleStringProperty getIdProperty(){
        return new SimpleStringProperty(getIdString());
    }

    public SimpleStringProperty getNombreProperty(){
        return new SimpleStringProperty(nombre);
    }

    public SimpleStringProperty getUsuarioProperty(){
        return new SimpleStringProperty(usuario);
    }

    public SimpleStringProperty getDireccionProperty(){
        return new SimpleStringProperty(direccion);
    }

    public SimpleStringProperty getTelefonoProperty(){
        return new SimpleStringProperty(telefono);

    }

    public SimpleStringProperty getTipoUsuarioProperty(){
        return new SimpleStringProperty(tipo_usuario);

    }
}
