import java.sql.*;
import java.util.ArrayList;

public class ActividadRepaso {


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
            ejercicio1 = listarPedidos("Adela Salas Díaz",conexion);
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


    public static ArrayList<String> listarPedidos(String nombreCliente, Connection conex) throws SQLException {

        ArrayList<String> lista = new ArrayList<>();

        String[] nombres = nombreCliente.split(" ");

        String sentenciaSQL = "select p.id, p.total, p.fecha, p.id_cliente, p.id_comercial \n" +
                "from pedido p, cliente cl, comercial co \n" +
                "where p.id_cliente = cl.id " +
                "and p.id_comercial = co.id " +
                "and cl.nombre = ? " +
                "and cl.apellido1 = ? " +
                "and cl.apellido2 = ?";

        PreparedStatement pst = conex.prepareStatement(sentenciaSQL);
        pst.setString(1, nombres[0]);
        pst.setString(2, nombres[1]);
        pst.setString(3, nombres[2]);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            int idPedido = rs.getInt("id");
            double total = rs.getDouble("total");

            String fecha = rs.getString("fecha");

            String resultado = "Pedido Nº: " + idPedido + ". Total: " + total +
                    ". Fecha: " + fecha;

            lista.add(resultado);
        }

        return lista;
    }


}
