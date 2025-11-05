import java.lang.reflect.Type;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public  static Connection conexion(String url, String us, String pw){

        Connection cn=null;
        try {
            cn= DriverManager.getConnection(url, us, pw);
            System.out.println("Conexión realizada con exito");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error de conexión de BD "+e.getMessage());;
        }


        return cn;
    }
    public static void insertarProducto(Connection cn,Producto p){
        String sentenciaSql = "INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?,?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = cn.prepareStatement(sentenciaSql);
            sentencia.setString(1, p.getNombre());
            sentencia.setDouble(2,p.getPrecio());
            sentencia.setInt(3,p.getCantidad());
            sentencia.executeUpdate();
            System.out.println("Producto insertado de forma correcta");
            sentencia.close();
        } catch (SQLException sqle) {
            System.out.println("Error en la inserción "+sqle.getMessage());;
        }
    }

    public static boolean eliminarProducto(Connection cn, Integer idProducto) throws SQLException {
        String sentenciaSql ="{ ?=call fn_eliminar_producto(?)}";
        CallableStatement sentenciaPreparada=cn.prepareCall(sentenciaSql);
        sentenciaPreparada.registerOutParameter(1, Types.BOOLEAN);
        sentenciaPreparada.setInt(2,idProducto);
        sentenciaPreparada.execute();
        return sentenciaPreparada.getBoolean(1);
    }

    public static boolean borrarProductoFuncion(Connection cn, int idProducto) throws SQLException {

        String sql = "?"

    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       Connection cn=conexion("jdbc:mysql://localhost:3306/tienda","root","alumno");
       Producto p=new Producto("producto prueba3",120.0, 12);
   //    insertarProducto(cn,p);

        try {
            if (eliminarProducto(cn,11))
                System.out.println("Producto eliminado correctamente");
            else
                System.out.println("El producto no se ha eliminado");
        } catch (SQLException e) {
            System.out.println("Error sql: "+e.getMessage());;
        }
        try {
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error a cerrar la conexión");;
        }
    }
}