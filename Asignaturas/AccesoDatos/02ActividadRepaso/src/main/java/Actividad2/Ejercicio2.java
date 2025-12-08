package Actividad2;

import java.sql.*;

public class Ejercicio2 {

    private static Connection conexion;
    private static final String url ="jdbc:mysql://localhost:3306/libros";
    private static final String usuario = "root";
    private static final String password = "ArdienteS21";

    public static void main(String[] args) {

        Libro libro1 = new Libro(57, "FP DAM",
                420, "Informática",
                1, 1);

        try {
            conexion = DriverManager.getConnection(url,usuario,password);
            System.out.println("Conexion establecida.");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }


        try {
            boolean insertado = insertarLibro(conexion, libro1);
            if (insertado) {
                System.out.println("Libro insertado");
            }else{
                System.out.println("Error al insertar el libro.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean insertarLibro(Connection conex, Libro libro) throws SQLException {

        boolean condicion1 = comprobarCodigo(conex, libro);
        boolean condicion2 = comprobarAutor(conex,libro);
        boolean condicion3 = comprobarEditorial(conex,libro);

        if (!condicion1) {
            System.out.println("Error: El código del libro ya existe.");
        }
        if (!condicion2) {
            System.out.println("Error: El autor no existe en la tabla de escritores.");
        }
        if (!condicion3) {
            System.out.println("Error: La editorial no existe en la tabla de editoriales.");
        }


        if(condicion1 && condicion2 && condicion3){

            String sentenciaSQL = "INSERT INTO libros (cod_libro, nombre_libro, numero_paginas, tema, autor, editorial)" +
                    " VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = conex.prepareStatement(sentenciaSQL);

            pst.setInt(1, libro.getCod_libro());
            pst.setString(2, libro.getNombre_libro());
            pst.setInt(3, libro.getNumero_paginas());
            pst.setString(4, libro.getTema());
            pst.setInt(5, libro.getAutor());
            pst.setInt(6, libro.getEditorial());

            pst.executeUpdate();

            return true;
        }else {
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
        int nombreAutorComprobar = libro.getAutor();

        String sentenciaSQL = "SELECT * FROM escritores WHERE cod_escritor = ?";

        PreparedStatement pst = conex.prepareStatement(sentenciaSQL);

        pst.setInt(1, nombreAutorComprobar);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    private static boolean comprobarEditorial(Connection conex, Libro libro) throws SQLException {

        int codEditorialComprobar = libro.getEditorial();

        String sentenciaSQL = "SELECT * FROM editoriales WHERE cod_EDITORIAL = ?";

        PreparedStatement pst = conex.prepareStatement(sentenciaSQL);

        pst.setInt(1, codEditorialComprobar);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        }

        return false;

    }

}
