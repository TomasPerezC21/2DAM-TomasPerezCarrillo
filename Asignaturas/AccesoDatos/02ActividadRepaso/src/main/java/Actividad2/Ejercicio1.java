package Actividad2;

import java.sql.*;
import java.util.ArrayList;

public class Ejercicio1 {

    private static Connection conexion;
    private static final String url ="jdbc:mysql://localhost:3306/libros";
    private static final String usuario = "root";
    private static final String password = "ArdienteS21";

    public static void main(String[] args) {

        /*
        Realiza un metodo que le pases el tipo de la editorial (SA, SL) y mostrará un listado
        de libros por editorial. El listado debe mostrar para cada editorial el nombre y en
        número de libros y a continuación mostrará un listado de libros de esa editorial,
        mostrando el nombre del libro y el nombre del autor.
         */

        try {
            conexion = DriverManager.getConnection(url,usuario,password);
            System.out.println("Conexion establecida.");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        ArrayList<String> resultado1;

        try {
             resultado1= listarTotalLibrosEditorial("SA", conexion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total libros por editorial: ");
        System.out.println("============================");
        for (int i = 0; i < resultado1.size(); i++) {
            System.out.println(resultado1.get(i));
        }

        System.out.println("==================================");
        System.out.println("Lista de libros por editorial: ");

        for (int i = 0; i < resultado1.size(); i++) {
            String[] nombreParts = resultado1.get(i).split(". Total de libros: ");
            String nombre = nombreParts[0];
            System.out.println("===============================");
            System.out.println("Editorial con nombre: " + nombre);
            System.out.println();

            ArrayList<String> librosPorEditorialBucle = null;
            try {
                librosPorEditorialBucle = prueba(nombre);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (int j = 0; j < librosPorEditorialBucle.size(); j++) {
                System.out.println(librosPorEditorialBucle.get(j));
            }

        }

    }

    public static ArrayList<String> prueba(String nombreEditorial) throws SQLException {

        ArrayList<String> librosPorEditorial = new ArrayList<>();

        String sentenciaSQL = "select l.nombre_libro, es.NOMBRE_ESCRITOR \n" +
                "from libros l, editoriales ed, escritores es\n" +
                "where ed.cod_editorial = l.EDITORIAL \n" +
                "and es.cod_escritor = l.autor\n" +
                "and ed.NOMBRE_EDITORIAL = ?";

        PreparedStatement pst = conexion.prepareStatement(sentenciaSQL);
        pst.setString(1, nombreEditorial);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String nombreLibro = rs.getString(1);
            String autor = rs.getString(2);
            String resultado = nombreLibro + " - " + autor;
            librosPorEditorial.add(resultado);
        }

        return librosPorEditorial;
    }

    public static ArrayList<String> listarTotalLibrosEditorial (String tipoEditorial, Connection conexion) throws SQLException{

        ArrayList<String> lista = new ArrayList<>();

        String sentencia1 = "select ed.nombre_editorial, count(l.cod_libro) as total_libros \n" +
                "\n" +
                "from libros l, editoriales ed\n" +
                "where ed.cod_editorial = l.editorial\n" +
                "and ed.tipo = ?" +
                "group by ed.nombre_editorial\n;";

        PreparedStatement pst = conexion.prepareStatement(sentencia1);
        pst.setString(1, tipoEditorial);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            String nombreEditorial  = rs.getString("nombre_editorial");
            int totalLibros = rs.getInt("total_libros");
            String resultado =  nombreEditorial + ". Total de libros: " + totalLibros;

            lista.add(resultado);
        }

        return lista;
    }



}
