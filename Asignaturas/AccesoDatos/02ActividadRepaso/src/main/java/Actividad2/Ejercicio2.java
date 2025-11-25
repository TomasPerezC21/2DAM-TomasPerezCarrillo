package Actividad2;

import java.sql.*;

public class Ejercicio2 {

    private static Connection conexion;
    private static final String url ="jdbc:mysql://localhost:3306/libros";
    private static final String usuario = "root";
    private static final String password = "alumnoDAM";

    public static void main(String[] args) {

        Libro libro1 = new Libro(56, "FP DAM",
                420, "Informática",
                "Tomás Pérez Carrillo", "AprendeDAM");

        try {
            conexion = DriverManager.getConnection(url,usuario,password);
            System.out.println("Conexion establecida.");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

        try {
            boolean insertado = insertarLibro(conexion, libro1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean insertarLibro(Connection conex, Libro libro) throws SQLException {

        boolean condicion1 = comprobarCodigo(conex, libro);
        boolean condicion2 = comprobarAutor(conex,libro);
        boolean condicion3 = comprobarEditorial(conex,libro);

        if(condicion1 && condicion2 && condicion3){

            String sentenciaSQL = "INSERT INTO libros (cod_libro, nombre_libro, numero_paginas, tema, autor, editorial)" +
                    " VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = conex.prepareStatement(sentenciaSQL);

            pst.setInt(1, libro.getCod_libro());
            pst.setString(2, libro.getNombre_libro());
            pst.setInt(3, libro.getNumero_paginas());
            pst.setString(4, libro.getTema());
            pst.setString(5, libro.getAutor());
            pst.setString(6, libro.getEditorial());

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Libro insertado.");
            }
            return true;
        }else {
            System.out.println("Error al insertar libro.");
            return false;
        }


    }

    private static boolean comprobarCodigo(Connection conex, Libro libro) throws SQLException {

        int codigoLibroComprobar = libro.getCod_libro();

        String sentenciaSQL = "select * from libros where COD_LIBRO = ?";

        PreparedStatement pst = conex.prepareStatement(sentenciaSQL);

        pst.setInt(1, codigoLibroComprobar);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return false;
        }

        return true;
    }

    private static boolean comprobarAutor(Connection conex, Libro libro) throws SQLException {
        String nombreAutorComprobar = libro.getAutor();

        String sentenciaSQL = "select * from libros where autor = ?";

        PreparedStatement pst = conex.prepareStatement(sentenciaSQL);

        pst.setString(1, nombreAutorComprobar);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    private static boolean comprobarEditorial(Connection conex, Libro libro) throws SQLException {

        String nombreEditorialComprobar = libro.getEditorial();

        String sentenciaSQL = "select * from libros where editorial = ?";

        PreparedStatement pst = conex.prepareStatement(sentenciaSQL);

        pst.setString(1, nombreEditorialComprobar);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        }

        return false;

    }

}
