package org.getfin.controlador;

import org.getfin.modelos.Producto;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class ProductoController {
    private static ProductoController instance;

    private ProductoController() {}

    public void guardaProducto(Producto producto) {

        try {
            IGenericService<Producto> service = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
            service.save(producto);
        } catch (Exception e) {
            System.err.println("Error al guardar el producto: " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("No se pudo guardar el producto.", e);
        }
    }

    public void eliminaProducto(Producto producto) {

        try {
            IGenericService<Producto> service = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
            service.delete(producto);
        } catch (Exception e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo eliminar el producto.", e);
        }
    }

    public void editaProducto(Producto producto) {

        try {
            IGenericService<Producto> service = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
            service.update(producto);
        } catch (Exception e) {
            System.err.println("Error al editar el producto: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo editar el producto.", e);
        }
    }

    public List<Producto> getProducto() {

        try {
            IGenericService<Producto> service = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
            return service.getAll();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de productos: " + e.getMessage());
            e.printStackTrace();

            return Collections.emptyList();
        }
    }

    public static synchronized ProductoController getInstance() {
        if (instance == null) {
            instance = new ProductoController();
        }
        return instance;
    }
}