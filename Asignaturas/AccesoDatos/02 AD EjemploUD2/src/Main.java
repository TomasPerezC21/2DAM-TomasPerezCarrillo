import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        try{
            Connection conex = crearConexion();
            System.out.println("Conexión a la base de datos.");
            //Producto p = new Producto("prueba", 12, 4);
            //insertarProducto(p, conex);
//            int filas = modificarPrecioProducto(11, 700, conex);
//            if (filas > 0){
//                System.out.println("Precio modificado correctamente. Filas modificadas: " +filas );
//            }
//            for (Producto p : obtenerProductos(conex)){
//                System.out.println(p);
//            }
//            System.out.println("Introduce un precio para ver los items que cuesten más: ");
//            double precio = sc.nextDouble();
//            sc.close();

//            for(Producto p : verPrecio(conex, precio)){
//                System.out.println(p);
//            }
            if (borrarProductoFuncion(conex, 24)) {
                System.out.println("Producto eliminado exitosamente");
            }else{
                System.out.println("Producto no encontrado en la bbdd");
            }

        }catch (SQLException e){
            System.out.println("Error en la conexión: " + e.getMessage());
        }


    }

    public static Connection crearConexion() throws SQLException{

        Connection cn;
        cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root","alumnoDAM");
        return cn;
    }

    public static void insertarProducto(Producto p, Connection cn) throws SQLException {
        String setenciaSQL = "INSERT INTO productos\n" +
                "(nombre, precio, cantidad)\n" +
                "VALUES(?,?,?);";

        PreparedStatement pst = cn.prepareStatement(setenciaSQL);
        pst.setString(1, p.getNombre());
        pst.setDouble(2, p.getPrecio());
        pst.setInt(3, p.getCantidad());
        pst.execute();
    }

    public static int modificarPrecioProducto(int idProducto, double precioNuevo, Connection cn) throws SQLException{
        String sentenciaSQL = "UPDATE productos\n" +
                "SET precio=? WHERE id=?;";

        PreparedStatement pst = cn.prepareStatement(sentenciaSQL);
        pst.setDouble(1, precioNuevo);
        pst.setInt(2, idProducto);
        return pst.executeUpdate();

    }

    public static ArrayList<Producto> obtenerProductos(Connection cn) throws SQLException {

        String sql="SELECT nombre, precio, cantidad\n" +
                "FROM productos;";

        PreparedStatement pst = cn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        ArrayList<Producto> productos = new ArrayList<>();

        while(rs.next()){
           Producto p = new Producto(rs.getString(1), rs.getDouble(2), rs.getInt(3));
           productos.add(p);
        }

        return productos;
    }

//    public static ArrayList<Producto> verPrecio(Connection cn, double precio) throws SQLException{
//
//        String sql = "SELECT id, nombre, precio, cantidad\n" +
//                "FROM productos where precio >?;";
//
//        PreparedStatement pst = cn.prepareStatement(sql);
//        pst.setDouble(1, precio);
//        ResultSet rs = pst.executeQuery();
//        ArrayList<Producto> productos = new ArrayList<>();
//
////        while(rs.next()){
////            Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
////        }
//
//    }

    public static boolean borrarProductoFuncion(Connection cn, int idProducto) throws SQLException{

        String sql = "{ ?=call fn_eliminar_producto(?) }";
        CallableStatement cb = cn.prepareCall(sql);
        
        cb.registerOutParameter(1, Types.BOOLEAN);
        cb.setInt(2, idProducto);
        cb.execute();

        return cb.getBoolean(1);
    }


}
