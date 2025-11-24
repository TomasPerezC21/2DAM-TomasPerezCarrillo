import java.sql.*;
import java.util.ArrayList;

public class ActividadRepaso2 {

    private static Connection conexion;
    private static final String url ="jdbc:mysql://localhost:3306/ventas";
    private static final String usuario = "root";
    private static final String password = "alumnoDAM";

    public static void main(String[] args) {


        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion establecida.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<String> ejercicio1 = new ArrayList<>();
        try {
            ejercicio1 = listarPedidos(2017,conexion);
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }

        if (ejercicio1.isEmpty()) {
            System.out.println("El cliente no tiene pedidos registrados.");
        }else{
            for (String e: ejercicio1) {
                System.out.println(e);
            }
        }


    }

    public static ArrayList<String> listarPedidos(int anio, Connection conex) throws SQLException {

        ArrayList<String> lista = new ArrayList<>();


        String sentenciaSQL = "select p.id, fecha, concat(nombre, \" \", apellido1, \" \", apellido2) nombreCompleto, total\n" +
                "from pedido p, comercial co\n" +
                "where p.id_comercial  = co.id \n" +
                "and year(fecha) = ? ";

        PreparedStatement pst = conex.prepareStatement(sentenciaSQL);
        pst.setInt(1, anio);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            int idPedido = rs.getInt("id");
            double total = rs.getDouble("total");

            String fecha = rs.getString("fecha");
            String nombreCompleto = rs.getString("nombreCompleto");

            String resultado = "ID del Pedido: " + idPedido + ". Total: " + total +
                    ". Fecha: " + fecha + ". Nombre Completo: " + nombreCompleto;

            lista.add(resultado);
        }

        return lista;
    }

}
