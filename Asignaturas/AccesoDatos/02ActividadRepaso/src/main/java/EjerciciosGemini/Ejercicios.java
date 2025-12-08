package EjerciciosGemini;

import java.sql.*;

public class Ejercicios {

    private static Connection conexion;
    private static String url = "jdbc:mysql://localhost:3306/ventas";
    private static String user = "root";
    private static String password = "alumnoDAM";


    public static void main(String[] args) {


       conexion = null;

        try {
            conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (conexion != null) {
            System.out.println("Conexión existosa.");
        }else{
            System.out.println("Conexión errónea.");
        }

        boolean ej1 = false;

//        try {
//            ej1 = ejercicio1(1, 1, 5, conexion);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (ej1) {
//            System.out.println("Producto insertado correctamente.");
//        }else{
//            System.out.println("Error al insertar producto.");
//        }


//        try {
//            ejercicio2(2017, conexion);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            ejercicio3(7, conexion);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            ejercicio4(2000.0, conexion);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        try {
            ejercicio5("l", "2015-08-21", "2025-04-21", conexion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        int afectados = 0;
//
//        try {
//            afectados = ejercicio6(15.0,2019, conexion);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (afectados == 0) {
//            System.out.println("No se ha modificado el precio de ningun pedido.");
//        }else{
//            System.out.println("Se ha modificado el precio de " + afectados + " pedidos.");
//        }

//        try {
//            ejercicio7(1,5,200.0, conexion);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    public static boolean ejercicio1(int idCliente, int idComercial, double total, Connection conexion) throws SQLException {

        boolean clienteValido = existeCliente(idCliente, conexion);
        boolean comercialValido = existeComercial(idComercial, conexion);

        if (clienteValido && comercialValido) {

            String sentenciaSQL = "Insert into pedido (total, fecha, id_cliente, id_comercial) values (?,CURDATE(),?,?)";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setDouble(1, total);
            sentencia.setInt(2, idCliente);
            sentencia.setInt(3, idComercial);
            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                return true;
            }

        }

        return false;
    }

    public static void ejercicio2(int anio, Connection conexion) throws SQLException {

        String sentenciaSQL = "select co.nombre, sum(p.total) as total_vendido, co.comisión\n" +
                "from pedido p, comercial co\n" +
                "where p.id_comercial = co.id \n" +
                "and year(p.fecha) = ?\n" +
                "group by co.id";

        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
        sentencia.setInt(1, anio);
        ResultSet resultado = sentencia.executeQuery();

        boolean hay = false;

            while (resultado.next()) {
                hay = true;
                double beneficio = resultado.getDouble("total_vendido") * resultado.getDouble("comisión");
                System.out.println(resultado.getString("nombre") + " ha vendido un total de " + beneficio);
            }

            if (!hay){
                System.out.println("No hay pedidos en el año " + anio);
            }

            sentencia.close();
            resultado.close();
    }

    public static void ejercicio3(int idCliente, Connection conexion) throws SQLException {

        String sql = "{ ? = call obtener_categoria_cliente(?) }";

        CallableStatement sentencia = conexion.prepareCall(sql);
        sentencia.setInt(2, idCliente);
        sentencia.registerOutParameter(1, Types.INTEGER);

        sentencia.execute();

        int categoria = sentencia.getInt(1);
        System.out.println("La categoria del cliente con id " + idCliente + " es: " + categoria);


    }

    public static void ejercicio4(double importeMinimo, Connection conexion) throws SQLException {

        String sql = "select cl.nombre, sum(p.total) as gasto_total\n" +
                "from pedido p, cliente cl\n" +
                "where cl.id = p.id_cliente \n" +
                "group by cl.id\n" +
                "having gasto_total > ?\n" +
                "order by gasto_total desc\n";

        boolean hay;

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setDouble(1, importeMinimo);

            try (ResultSet resultado = sentencia.executeQuery()) {

                hay = false;

                while (resultado.next()) {
                    hay = true;
                    System.out.println(resultado.getString("nombre") + " ha gastado en total de " + resultado.getDouble("gasto_total"));
                }
            }
        }

        if (!hay){
            System.out.println("No hay clientes que hayan gastado mas de " + importeMinimo);
        }

    }

    public static void ejercicio5(String letraApellido1, String fechaIni, String fechaFin, Connection conexion) throws SQLException {

        String sql = "select p.id, cl.nombre, cl.apellido1, p.fecha, p.total\n" +
                "from pedido p, cliente cl\n" +
                "where cl.id = p.id_cliente \n" +
                "and cl.apellido1 like ? \n" +
                "and p.fecha between ? and ?\n";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, letraApellido1+"%");
        sentencia.setString(2, fechaIni);
        sentencia.setString(3, fechaFin);

        boolean hay = false;

        try (ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                hay = true;
                System.out.println("ID del pedido: " + resultado.getInt("id") + " | " +
                        resultado.getString("nombre") +" "+ resultado.getString("apellido1") +
                        " | " + resultado.getString("fecha") + " | " + " total del pedido " + resultado.getDouble("total"));
            }
        }

        if (!hay){
            System.out.println("No hay pedidos disponibles.");
        }

    }

    public static int ejercicio6(double porcentaje,int anio, Connection conexion) throws SQLException {

        porcentaje = 1 + (porcentaje / 100);

        String sql = "UPDATE pedido\n" +
                "SET total= total *?\n" +
                "WHERE year(fecha) = ?";

        int filas = 0;
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setDouble(1, porcentaje);
            sentencia.setInt(2, anio);

            filas = sentencia.executeUpdate();
        }

        return  filas;
    }

    public static void ejercicio7(int idOrigen, int idDestino, double cantidadMax, Connection conexion) throws SQLException {

        boolean comercial1 = false;
        boolean comercial2 = false;

        comercial1 = existeComercial(idOrigen, conexion);
        comercial2 = existeComercial(idDestino, conexion);

        if (comercial1 && comercial2) {

            String sql = "UPDATE pedido\n" +
                    "SET id_comercial=?\n" +
                    "WHERE id_comercial=?\n" +
                    "and total < ?;\n";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idDestino);
            sentencia.setInt(2, idOrigen);
            sentencia.setDouble(3, cantidadMax);

            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Se han modificado " + filas + " pedidos");
            }else{
                System.out.println("No se ha modificado ningun pedido");
            }

        }

    }


    private static boolean existeCliente(int idCliente, Connection conexion) throws SQLException {

        String sentenciaSQL = "select * from cliente where id = ?";
        PreparedStatement pst = conexion.prepareStatement(sentenciaSQL);


        pst.setInt(1, idCliente);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return true;
        }

        System.out.println("Cliente no encontrado en la bbdd.");
        return false;
    }

    private static boolean existeComercial(int idComercial, Connection conexion) throws SQLException {

        String sentenciaSQL = "select * from comercial where id = ?";
        PreparedStatement pst = conexion.prepareStatement(sentenciaSQL);


        pst.setInt(1, idComercial);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return true;
        }

        System.out.println("Comercial no encontrado en la bbdd.");
        return false;
    }

}
