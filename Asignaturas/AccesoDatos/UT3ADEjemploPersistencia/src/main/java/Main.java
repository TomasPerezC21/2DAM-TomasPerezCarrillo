import Entidades.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {


    static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplopersistenciaJPA");
        Producto producto = new Producto("Producto de prueba JPA", new BigDecimal(10), 100);
        insertarProducto(emf, producto);



        Producto p = obtenerProducto(emf, 1);
        if (p == null) {
            System.out.println("No existe el producto con ese id.");
        }else{
            System.out.println(p);
        }

        emf.close();
    }

    public static void insertarProducto(EntityManagerFactory emf, Producto producto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        em.close();

    }

    public static Producto obtenerProducto(EntityManagerFactory emf, Integer idProducto) {
        EntityManager em = emf.createEntityManager();
        Producto producto = em.find(Producto.class, idProducto);
        return producto;
    }

    public static boolean eliminarProducto(EntityManagerFactory emf, Integer idProducto) {
        EntityManager em = emf.createEntityManager();

        Producto p = em.find(Producto.class, idProducto);

        if (p!=null){
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            em.close();
            return true;
        }else{
            return false;
        }
    }

    public static boolean modificarCantidad(EntityManagerFactory emf, Integer idProducto, Integer cantidad) {
        EntityManager em = emf.createEntityManager();
        Producto p = em.find(Producto.class, idProducto);
        if (p!=null){
            p.setCantidad(cantidad);
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            em.close();
            return true;
        }else{
            return false;
        }
    }
}
