package servicio;

import modelo.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Inventario {

    private Connection conexion;
    private final String url = "jdbc:mysql://localhost:3306/tienda_online";
    private final String user = "root";
    private final String password = "alumnoDAM";

    public Inventario() throws IOException, ClassNotFoundException, SQLException {

        conexion= DriverManager.getConnection(url,user,password);

    }
    /**
     *
     * @param producto
     * @return true si el inserta el producto en el invenario si el producto pasado cómo parametro el id no está en el inventario
     */

//    public boolean insertarProducto(Producto producto) throws SQLException {
//
//        if (producto instanceof Ropa){
//            insertarRopa(producto);
//        }else{
//            insertarElectronico(producto);
//        }
//
//        return false;
//    }

    public boolean insertarProducto(Producto producto) throws SQLException {

        if (producto instanceof Ropa){
            return insertarRopa(producto);
        }else{
            return insertarElectronico(producto);
        }

    }

    public boolean insertarElectronico(Producto producto) throws SQLException {

        conexion.setAutoCommit(false);
        Electronico electronido = (Electronico)producto;
        String sentenciaSQL = "INSERT INTO productos\n" +
                " (tipo, nombre, precio, stock)\n " +
                "VALUES (?,?,?,?)";

        PreparedStatement pst = conexion.prepareStatement(sentenciaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, "Electronico");
        pst.setString(2, electronido.getNombre());
        pst.setDouble(3, electronido.getPrecio());
        pst.setInt(4, electronido.getStock());
        int filas = pst.executeUpdate();
        if (filas > 0) {
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idProducto = rs.getInt(1);
                String sql1 = "INSERT INTO tienda_online.electronicos\n" +
                        "(id, marca, garantia)\n" +
                        "VALUES (?,?,?)";
                PreparedStatement pst1 = conexion.prepareStatement(sql1);
                pst1.setInt(1, idProducto);
                pst1.setString(2, electronido.getMarca());
                pst1.setDouble(3, electronido.getGarantia());
                int filas1 = pst1.executeUpdate();
                if (filas1 > 0) {
                    rs.close();
                    pst1.close();
                    pst.close();
                    conexion.setAutoCommit(true);
                    return true;
                }
            }
        }
        conexion.rollback();
        return false;
    }

    public boolean insertarRopa(Producto producto) throws SQLException {
        conexion.setAutoCommit(false);
        Ropa ropa = (Ropa)producto;
        String sql = "INSERT INTO productos\n" +
                    " (tipo, nombre, precio, stock)\n " +
                    "VALUES (?,?,?,?)";

        PreparedStatement pst = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, "Ropa");
        pst.setString(2, ropa.getNombre());
        pst.setDouble(3, ropa.getPrecio());
        pst.setInt(4, ropa.getStock());
        int filas = pst.executeUpdate();
        if (filas > 0) {
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idProducto = rs.getInt(1);
                String sql1 = "INSERT INTO tienda_online.ropa\n" +
                        "(id, talla, material)\n" +
                        "VALUES (?,?,?)";
                PreparedStatement pst1 = conexion.prepareStatement(sql1);
                pst1.setInt(1, idProducto);
                pst1.setString(2, ropa.getTalla());
                pst1.setString(3, ropa.getMaterial());
                int filas1 = pst1.executeUpdate();
                if (filas1 > 0) {
                    rs.close();
                    pst1.close();
                    pst.close();
                    conexion.setAutoCommit(true);
                    return true;
                }
            }
        }
        conexion.rollback();
        return false;
    }




    //obtener arrayList de productos Electronicos y Ropa
    public ArrayList<Producto> getListaProductos() throws SQLException {
        ArrayList<Producto> listaProductos=new ArrayList<>();

        String sentenciaSQL = "select p.id, p.nombre, p.precio, p.stock, marca, garantia\n" +
                "from productos p, electronicos e\n" +
                "where p.id = e.id ;\n";

        PreparedStatement pst = conexion.prepareStatement(sentenciaSQL);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){

            Electronico electronico = new Electronico(rs.getInt(1), rs.getString(2),
                    rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
            listaProductos.add(electronico);
        }

        String sentenciaSQL2 = "select p.id, p.nombre, p.precio, p.stock, talla, material\n" +
                "from productos p , ropa r\n" +
                "where p.id = r.id;\n";

        pst = conexion.prepareStatement(sentenciaSQL2);
        rs = pst.executeQuery();
        while(rs.next()){
            Ropa ropa = new Ropa(rs.getInt(1), rs.getString(2), rs.getDouble(3),
                    rs.getInt(4), rs.getString(5), rs.getString(6));
            listaProductos.add(ropa);
        }


        return listaProductos;

    }



    /**
     *
     * @param idProducto a vender
     * @param cantidad de producto a vender
     * @return true si realiza la venta porque hay suficente stock, devuelve false si no hay suficiente stock
     * @throws ProductoNoInventarioException si el producto a vender no está en el inventario
     */
    public boolean venderProducto(int idProducto, int cantidad) throws ProductoNoInventarioException, SQLException {
        String sql = "select stock\n" +
                "from productos\n" +
                "where id=?";

        PreparedStatement pst = conexion.prepareStatement(sql);
        pst.setInt(1, idProducto);

        ResultSet rs = pst.executeQuery();
        rs.next();
        if (rs.getInt(1) >= cantidad) {
            String sql1 = "update productos set stock = stock - ? where id = ?";
            PreparedStatement pst1 = conexion.prepareStatement(sql1);
            pst1.setInt(1, cantidad);
            pst1.setInt(2, idProducto);
            pst1.executeUpdate();
            return true;
        }else {
            return false;
        }

    }

    /**
     *
     * @param idProducto a reponer
     * @param cantidad de producto a reponer
     *
     */
    public boolean reponerProducto(int idProducto, int cantidad) throws SQLException {
        String sql1 = "update productos set stock = stock + ? where id = ?";
        PreparedStatement pst1 = conexion.prepareStatement(sql1);
        pst1.setInt(1, cantidad);
        pst1.setInt(2, idProducto);
        pst1.executeUpdate();
        return true;



    }
}
