package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

public class DBManager {
    static String user = "postgres";
    static String password= "Unisierra20";


    public static Connection connectionBitacora;
    private static Connection connection;

    public static void initConecction(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vaporops",user,password);
            System.out.println("Conexión lista");
        }
        catch (Exception e){

        }
    }

    public static void connectionBitacora(){
        try {
            connectionBitacora = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bitacora_vaporops",user,password);
            System.out.println("Conexión Bitacora lista");
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
               actualizaBitacora("INSERT", "USUARIOS");
            }
        }
        catch (Exception e){
        }
    }

    private static void actualizaBitacora(String accion, String tabla_involucrada) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate fechaingreso = LocalDate.now();
            PreparedStatement statementBitacora = connectionBitacora.prepareStatement("INSERT INTO bitacora (responsable_movimiento, accion, fecha_accion, tabla_involucrada) values ('" + Ventanas.user.getNombre().toUpperCase() + "', '"+accion+"', '" + fechaingreso + "', '"+tabla_involucrada+"')");
            statementBitacora.execute();
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
            ObservableList<Usuario> registroUsuarios = FXCollections.observableArrayList();
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
            statement.execute();
            actualizaBitacora("DELETE", "USUARIO");
        }
        catch (Exception e){
        }
    }

    public static void editarUsuario(Usuario usuarioDetalles) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE usuario set nombre ='"+usuarioDetalles.getNombre()+"', usuario = '"+usuarioDetalles.getUsuario()+"', tipo_usuario = "+tipoUsuario(usuarioDetalles)+", telefono = '"+usuarioDetalles.getTelefono()+"', direccion = '"+usuarioDetalles.getDireccion()+"' where codigo = '"+usuarioDetalles.getId()+"'");
            statement.execute();
            actualizaBitacora("UPDATE", "USUARIO");
        }
        catch (Exception e){
        }
    }

    public static void editarUsuario_Contraseña(Usuario usuarioDetalles, String contraseña) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE usuario set nombre ='"+usuarioDetalles.getNombre()+"', usuario = '"+usuarioDetalles.getUsuario()+"', tipo_usuario = "+tipoUsuario(usuarioDetalles)+", telefono = '"+usuarioDetalles.getTelefono()+"', direccion = '"+usuarioDetalles.getDireccion()+"', contraseña = PGP_SYM_ENCRYPT('"+contraseña+"','AES_KEY') where codigo = '"+usuarioDetalles.getId()+"'");
            statement.execute();
            actualizaBitacora("UPDATE", "USUARIO");
        }
        catch (Exception e){
        }
    }

    public static void registrarDistribuidor(String codigo, String nombre) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO distribuidor (codigo, nombre) VALUES ("+codigo+",'"+nombre+"')");
            statement.execute();
            actualizaBitacora("INSERT", "DISTRIBUIDOR");
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
                statement.execute();
                actualizaBitacora("INSERT", "PRODUCTO");
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
        }
        return -1;
    }

    public static boolean datosProducto(String codigoP) {
        try {
            PreparedStatement statement = connection.prepareStatement("Select p.codigo, p.nombre, d.nombre as \"distribuidor\", p.descripcion, p.precio_venta, p.stock_total, p.stock_actual from producto p inner join distribuidor d on p.codigo_distribuidor = d.codigo where p.codigo ="+codigoP);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Ventanas.productoIngreso=new Producto(result.getLong("codigo"), result.getString("nombre"), result.getString("descripcion"), result.getString("distribuidor"), result.getInt("precio_venta"), result.getInt("stock_total"), result.getInt("stock_actual"));
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }

    public static void registarIngreso(int precio_ingreso, int cantidad_ingreso) {
        try {
            Ventanas.productoIngreso.setStock_total(Ventanas.productoIngreso.getStock_actual() + cantidad_ingreso);
            Ventanas.productoIngreso.setStock_actual(Ventanas.productoIngreso.getStock_actual() + cantidad_ingreso);
            actualizacionStockProducto();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate fechaingreso =  LocalDate.now();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ingreso_stock (precio_ing, cantidad_ingreso, fecha_ingreso, codigo_producto) VALUES ("+precio_ingreso+", "+cantidad_ingreso+", '"+fechaingreso+"', "+Ventanas.productoIngreso.getCodigo()+")");
            statement.execute();
            actualizaBitacora("INSERT", "INGRESO_STOCK");
        }
        catch (Exception e){
        }
    }

    private static void actualizacionStockProducto() {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE producto set stock_total =" + Ventanas.productoIngreso.getStock_total() + ", stock_actual = " + Ventanas.productoIngreso.getStock_actual() + " where codigo =" + Ventanas.productoIngreso.getCodigo());
            statement.execute();
            actualizaBitacora("UPDATE", "PRODUCTO");
        }
        catch (Exception e){
        }
    }

    public static ObservableList<Producto> registroProductos() {
        try{
            ObservableList<Producto> registroProducto = FXCollections.observableArrayList();;
            PreparedStatement statement = connection.prepareStatement("Select p.codigo, p.nombre, d.nombre as \"distribuidor\", p.descripcion, p.precio_venta, p.stock_total, p.stock_actual from producto p inner join distribuidor d on p.codigo_distribuidor = d.codigo");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                long id = result.getLong("codigo");
                String nombre = result.getString("nombre");
                String distribuidor = result.getString("distribuidor");
                String descripcion =  result.getString("descripcion");
                int precio_venta = result.getInt("precio_venta");
                int stock_total = result.getInt("stock_total");
                int stock_actual = result.getInt("stock_actual");
                registroProducto.add(new Producto(id,nombre,descripcion,distribuidor,precio_venta,stock_total,stock_actual));
            }
            return registroProducto;
        }
        catch (Exception e){
        }
        return null;
    }

    public static ObservableList<Producto> registroProductos(String busqueda) {
        try{
            ObservableList<Producto> registroProducto = FXCollections.observableArrayList();;
            PreparedStatement statement = connection.prepareStatement("Select p.codigo, p.nombre, d.nombre as \"distribuidor\", p.descripcion, p.precio_venta, p.stock_total, p.stock_actual from producto p inner join distribuidor d on p.codigo_distribuidor = d.codigo where p.nombre like '%"+busqueda+"%'");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                long id = result.getLong("codigo");
                String nombre = result.getString("nombre");
                String distribuidor = result.getString("distribuidor");
                String descripcion =  result.getString("descripcion");
                int precio_venta = result.getInt("precio_venta");
                int stock_total = result.getInt("stock_total");
                int stock_actual = result.getInt("stock_actual");
                registroProducto.add(new Producto(id,nombre,descripcion,distribuidor,precio_venta,stock_total,stock_actual));
            }
            return registroProducto;
        }
        catch (Exception e){
        }
        return null;
    }

    public static ObservableList<Ingreso> registrosIngresos() {
        try{
            ObservableList<Ingreso> registroIngresos= FXCollections.observableArrayList();
            PreparedStatement statement = connection.prepareStatement("Select s.codigo_ing, p.nombre, s.precio_ing, s.cantidad_ingreso, s.fecha_ingreso from ingreso_stock s inner join producto p on s.codigo_producto = p.codigo");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                String codigo = result.getString("codigo_ing");
                String nombreProducto = result.getString("nombre");
                String precio = result.getString("precio_ing");
                String cantidad =  result.getString("cantidad_ingreso");
                String fecha = result.getString("fecha_ingreso");
                registroIngresos.add(new Ingreso(codigo, nombreProducto, precio, cantidad, fecha));
            }
            return registroIngresos;
        }
        catch (Exception e){
        }
        return null;
    }

    public static long folioVenta() {
        LinkedList<Long> codigos = new LinkedList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("Select * from venta");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                codigos.add(result.getLong("codigo"));
            }

            if (codigos.size()!=0) {
                return (codigos.getLast() + 1);
            }
            else {
                return 1;
            }
        }
        catch (Exception e){
        }
        return -1;
    }

    public static Detalles_Ventas agregarProductoVenta(String codigo, int cantidad, long folioVenta) {
        try{
            PreparedStatement statement = connection.prepareStatement("Select p.codigo, p.nombre, d.nombre as \"distribuidor\", p.descripcion, p.precio_venta, p.stock_total, p.stock_actual from producto p inner join distribuidor d on p.codigo_distribuidor = d.codigo where p.codigo = "+ codigo);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long codigoProducto = result.getLong("codigo");
                String nombre = result.getString("nombre");
                int precio_venta = result.getInt("precio_venta");
                int stock_actual = result.getInt("stock_actual");

                if (stock_actual >= cantidad) {
                    return new Detalles_Ventas(codigoProducto,nombre, cantidad, precio_venta, (precio_venta * cantidad), folioVenta);
                }
            }
        }
        catch (Exception e){
        }
        return null;
    }

    public static void cargarVenta(long folioVenta, int total, String pago, int cambio, String usuarioVenta) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate fechaingreso = LocalDate.now();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO venta (codigo, fecha, total, efectivo, cambio, usuario_venta) values ("+folioVenta+",'"+fechaingreso+"', " + total + ", " + pago + ", " + cambio + ", " + usuarioVenta + ")");
            statement.execute();
            actualizaBitacora("INSERT", "VENTA");
        }
        catch (Exception e){
        }
    }

    public static void registroDetallesVenta(ObservableList<Detalles_Ventas> productos) {
        try {
            for (int i=0; i < productos.size(); i++) {
                Detalles_Ventas articulo = productos.get(i);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO detalles_venta (codigo_venta, cantidad, total_detalles, codigo_producto) values (" + articulo.getCodigoVenta() + ", " + articulo.getCantidad() + ", " + articulo.getTotal_detalles() + ", " + articulo.getCodigoProducto() + ")");
                statement.execute();
                actualizaBitacora("INSERT", "DETALLES_VENTAS");
                actualizarStock(articulo.getCantidad(), articulo.getCodigoProducto());
            }
        }
        catch (Exception e){

        }
    }

    private static void actualizarStock(int cantidad, long codigoProducto) {
        try {
            PreparedStatement statementP = connection.prepareStatement("Select * from producto");
            ResultSet result = statementP.executeQuery();
            int stock_actual = 0;
            while (result.next()) {
                if (result.getLong("codigo") == codigoProducto) {
                    stock_actual = result.getInt("stock_actual");
                }
            }
            if (stock_actual!=0) {
                stock_actual = (stock_actual - cantidad);
            }
            PreparedStatement statementUp = connection.prepareStatement("Update producto set stock_actual =" + stock_actual+" where codigo ="+codigoProducto);
            statementUp.execute();
            actualizaBitacora("UPDATE", "PRODUCTO");
        }
        catch (Exception e){
        }
    }

    public static ObservableList<Venta> registroVentas() {
        try {
            ObservableList<Venta> ventas = FXCollections.observableArrayList();
            PreparedStatement statementP = connection.prepareStatement("Select v.codigo, v.total, v.fecha, v.efectivo, v.cambio, u.nombre from venta v inner join usuario u on v.usuario_venta = u.codigo");
            ResultSet result = statementP.executeQuery();
            while (result.next()){
                String id = result.getString("codigo");
                String total = result.getString("total");
                String efectivo = result.getString("efectivo");
                String cambio = result.getString("cambio");
                String empleadoVenta =  result.getString("nombre");
                String fecha = result.getString("fecha");
                ventas.add(new Venta(id, total, efectivo, cambio, empleadoVenta, fecha));
            }
            return ventas;
        }
        catch (Exception e){
        }

        return null;
    }

    public static ObservableList<Detalles_Ventas> registroDetallesdeVenta(String id) {
        try {
            ObservableList<Detalles_Ventas> detalles = FXCollections.observableArrayList();
            PreparedStatement statement = connection.prepareStatement("Select dv.codigo_producto, dv.cantidad ,p.nombre, p.precio_venta, dv.total_detalles, dv.codigo_venta from detalles_venta dv inner join producto p on dv.codigo_producto = p.codigo where dv.codigo_venta = "+id);
            ResultSet result =statement.executeQuery();
            while (result.next()){
                long codigo_producto = result.getLong("codigo_producto");
                int cantidad = result.getInt("cantidad");
                String nombre = result.getString("nombre");
                long codigo_venta = result.getLong("codigo_venta");
                int precio_venta = result.getInt("precio_venta");
                int total_detalles = result.getInt("total_detalles");
                detalles.add(new Detalles_Ventas(codigo_producto, nombre, cantidad, precio_venta, total_detalles, codigo_venta));
            }
            return detalles;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}