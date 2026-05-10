package org.getfin.dao;

import org.getfin.modelos.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UsuarioDAO {

    private SessionFactory sessionFactory;

    public UsuarioDAO() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("✅ Hibernate inicializado");
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // VALIDAR credenciales
    public Usuario validarCredenciales(String usuario, String contraseña) {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            String hql = "FROM Usuario u WHERE u.usuario = :usuario AND u.contraseña = :contraseña";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("usuario", usuario);
            query.setParameter("contraseña", contraseña);

            return query.uniqueResult();

        } catch (Exception e) {
            System.err.println("Error validar: " + e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    // REGISTRAR nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        Session session = null;
        try {
            // Verificar si ya existe
            if (usuarioExiste(usuario.getUsuario())) {
                System.out.println("❌ El usuario ya existe");
                return false;
            }

            session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();

            System.out.println("✅ Usuario registrado: " + usuario.getUsuario());
            return true;

        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error registrar: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    // Verificar si usuario existe
    public boolean usuarioExiste(String usuario) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = "SELECT COUNT(*) FROM Usuario WHERE usuario = :usuario";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("usuario", usuario);
            Long count = query.uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            return false;
        } finally {
            if (session != null) session.close();
        }
    }
}