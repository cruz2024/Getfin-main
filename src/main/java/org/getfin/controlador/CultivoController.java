package org.getfin.controlador;

import org.getfin.modelos.Cultivo;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class CultivoController {
    private static CultivoController instance;

    private CultivoController() {}

    public void guardaCultivo(Cultivo cultivo) {
        try {
            IGenericService<Cultivo> service = new GenericServiceImpl<>(Cultivo.class, HibernateUtil.getSessionFactory());
            service.save(cultivo);
        } catch (Exception e) {
            System.err.println("Error al guardar el cultivo: " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("No se pudo guardar el cultivo.", e);
        }
    }

    public void eliminaCultivo(Cultivo cultivo) {

        try {
            IGenericService<Cultivo> service = new GenericServiceImpl<>(Cultivo.class, HibernateUtil.getSessionFactory());
            service.delete(cultivo);
        } catch (Exception e) {
            System.err.println("Error al eliminar el cultivo: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo eliminar el cultivo.", e);
        }
    }

    public void editaCultivo(Cultivo cultivo) {

        try {
            IGenericService<Cultivo> service = new GenericServiceImpl<>(Cultivo.class, HibernateUtil.getSessionFactory());
            service.update(cultivo);
        } catch (Exception e) {
            System.err.println("Error al editar el cultivo: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo editar el cultivo.", e);
        }
    }

    public List<Cultivo> getCultivo() {

        try {
            IGenericService<Cultivo> service = new GenericServiceImpl<>(Cultivo.class, HibernateUtil.getSessionFactory());
            return service.getAll();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de cultivo: " + e.getMessage());
            e.printStackTrace();

            return Collections.emptyList();
        }
    }

    public static synchronized CultivoController getInstance() {
        if (instance == null) {
            instance = new CultivoController();
        }
        return instance;
    }
}