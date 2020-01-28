package com.bonos.persist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import java.sql.*;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Transaction;
import utilidad.Utilidades;

/**
 * Esta clase permite realizar las operaciones basicas en base de datos usando
 * la session de hibernate
 */
public class EntityManager {

    private final TransactionManager transactionManager;//Administrador de sesiones
    private final String ruta;//Ruta de archivo de configuracion de hibernate.xml
    private Session session; // archivo de session de hibernate que permitira realizar todas las operaciones en base de datos
    private Connection con; //Conexión a la base de datos para realizar consultas a la base de datos
    private Statement stmt; // Variable que contiene el comando SQL a ejecutar
    /*
     * Singleton
     */
    private static List<EntityManager> instancias;//Coleccion de instancias del administrador de entidades

    private EntityManager(String ruta) {
        this.ruta = ruta;
        this.transactionManager = TransactionManager.getInstance(ruta);
    }

    /**
     * Carga la instancia con la base de datos por defecto para el entity
     * manager
     *
     * @param ruta
     * @return
     */
    public synchronized static EntityManager getInstance(String ruta) {
        if (instancias == null) {
            instancias = new ArrayList<>();
            instancias.add(new EntityManager(ruta));
            return instancias.get(0);
        }
        for (EntityManager element : instancias) {
            if (element.ruta.equals(ruta)) {
                return element;
            }
        }
        EntityManager nuevaInstancia = new EntityManager(ruta);
        instancias.add(nuevaInstancia);
        return nuevaInstancia;
    }

    /**
     * @param entity
     * @param clase
     * @return boolean
     * @throws Exception
     */
    public boolean save2(Object entity, String clase) throws Exception {
        try {
            abrirSession();
            Transaction t = session.beginTransaction();
            session.saveOrUpdate(clase, entity);
            t.commit();
            //session.flush();
            return true;
        } catch (NonUniqueObjectException e) {
            System.out.println("?????????????????????????????????DUPLICADO *************/*/*/*/*/*/*/*/*/**/*/*/*/*/*/*/*/*/*/*/");
            try {
                abrirSession();
                Object nuevo = session.merge(clase, entity);
                session.saveOrUpdate(clase, nuevo);
                session.getTransaction().commit();
                //   session.flush();
                System.out.println("Duplicado guardado");
                return true;
            } catch (Exception exec) {
                System.out.println("NO SE PUDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                //System.out.println(e.getMessage());
                //System.out.println(exec.getMessage());
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            }
            return false;
        } catch (Exception e) {
            // e.getMessage();
            session.getTransaction().rollback();
            //Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
            Utilidades.imprimir_msg("Error", "Error al ejecutar comando");
            return false;
        }
    }

    public Object selectNameQueryParam(HashMap param, String nameQuery) throws Exception {
        try {
            abrirSession();
            Query query = session.getNamedQuery(nameQuery);
            Iterator it = param.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                query.setParameter(e.getKey().toString(), e.getValue());
            }
            query.setReadOnly(false);
            param.clear();
            return query.uniqueResult();
        } catch (Exception e) {
            //Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            //cerrarSession();
        }
        return null;
    }

    /**
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public boolean delete(Object entity) throws Exception {
        try {
            abrirSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            //session.flush();
            return true;
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
            return false;
        } finally {
            //cerrarSession();
        }

    }

    /**
     *
     * @return @throws Exception
     */
    public Session getSession() throws Exception {
        abrirSession();
        return session;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * Permite abrir la conexion para hibernate, en caso de que esta ya no este
     * abierta
     *
     * @throws Exception
     */
    private void abrirSession() throws Exception {
        if (session != null && session.isOpen()) {
            return;
        }
        session = transactionManager.getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
    }

    //Este metodo permite actualizar una entidad de la  base dedatos
//    public void actualizar(Object objeto) {
//        session.refresh(objeto);
//    }
    /**
     * Si la conexion esta abierta permite cerrarla
     */
    public void cerrarSession() {
        if (session == null || !session.isOpen()) {
            return;
        }
        //session.close();
    }

    public Object selectNameQueryParamList(HashMap param, String nameQuery) throws Exception {
        try {
            abrirSession();
            Query query = session.getNamedQuery(nameQuery);
            if (param != null) {
                Iterator it = param.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry e = (Map.Entry) it.next();
                    query.setParameter(e.getKey().toString(), e.getValue());
                }
            }
            query.setReadOnly(false);
            
            return query.list();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println(ex.toString());
            //ex.getMessage();
           // session.getTransaction().rollback();
            System.out.println("Exepcion entity Manager"); 
           return null;
        }
    }

    public Object selectNameQueryUniqueResult(String nameQuery) throws Exception {
        try {
            abrirSession();
            Query query = session.getNamedQuery(nameQuery);
            query.setReadOnly(false);
            return query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            // cerrarSession();
        }
    }

    public Object selectNameQueryParamListMultipleEst(HashMap param, String nameQuery) throws Exception {
        try {
            abrirSession();
            Query query = session.getNamedQuery(nameQuery);
            if (param != null) {
                Iterator it = param.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry e = (Map.Entry) it.next();
                    query.setParameter(e.getKey().toString(), e.getValue());
                }
            }
            query.setReadOnly(false);
            return query.list();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.getMessage();
            return null;
        } finally {
            //cerrarSession();
        }
    }

    //Este  metodo permite cerrar la transacion entre la aplicacion y la base de datos
//    public void closeStatement() {
//        try {
//            stmt.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //Crear una nueva conexión a la base de datos de Postgres
//    public Connection createConnection(String dir, String user, String pass) {
//        //Capturar la clase del controlador de Postgres
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
//            return null;
//        }
//        //Usar las credenciales de los parámetros para realizar la conexión
//        try {
//            con = DriverManager.getConnection(dir, user, pass);
//            getCon().setAutoCommit(false);
//        } catch (SQLException ex) {
//            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return getCon();
//    }
//
//    /**
//     * Permite realizar la consulta de una tabla a traves del nombre del entity
//     *
//     * @param <T>
//     * @param tabla : nombre del entity a consultar
//     * @return listado resultado de la consulta del entity.
//     * @throws java.lang.Exception
//     */
//    @SuppressWarnings("unchecked")
//    public <T> List<T> selectTabla(String tabla) throws Exception {
//        try {
//            abrirSession();
//            session.beginTransaction();
//            Query query = session.createQuery("from " + tabla);
//            query.setReadOnly(true);
//            return query.list();
//        } finally {
//            //cerrarSession();
//        }
//    }
//    /**
//     * Permite realizar una consulta usando un query
//     *
//     * @param <T>
//     * @param sql : sentencia sql a realizar para la consulta
//     * @return
//     * @return: listado de entitys resultado de la consulta
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    public <T> List<T> selectNativo(String sql) throws Exception {
//        List cuenta = null;
//        try {
//            abrirSession();
//            Query query = session.createQuery(sql);
//            query.setReadOnly(true);
//            cuenta = query.list();
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }
//        return cuenta;
//    }
//
//    /**
//     * Consulta con un unico resultado
//     *
//     * @param sql
//     * @return
//     * @throws Exception
//     */
//    public Object selectNativoValor(String sql) throws Exception {
//        try {
//            abrirSession();
//            Query query = session.createQuery(sql);
//            query.setReadOnly(false);
//            return query.uniqueResult();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public boolean executeUpdate(String sql, Integer parameter, Integer where) throws Exception {
//        try {
//            abrirSession();
//            Query query = session.createQuery(sql);
//            query.setParameter("ecId", parameter);
//            query.setParameter("contratoId", where);
//            int rowsChanged = query.executeUpdate();
//            session.getTransaction().commit();
//            session.flush();
//            System.out.println(rowsChanged);
//            return true;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            session.getTransaction().rollback();
//            return false;
//        }
//    }
//    /**
//     * Permite realizar una consulta con un Query ya parametrizado en la entidad
//     * y retorna un objeto
//     *
//     * @param nameQuery Nombre del Query que se encuentra en la entidad
//     * @return
//     * @throws Exception
//     */
//    public Object selectNameQuery(String nameQuery) throws Exception {
//        try {
//            abrirSession();
//            Query query = session.getNamedQuery(nameQuery);
//            query.setReadOnly(false);
//            return query.list();
//        } finally {
//            //cerrarSession();
//        }
//    }
//    /**
//     * Permite realizar una consulta con un Query ya parametrizado en la entidad
//     * y retorna un unico objeto
//     *
//     * @param nameQuery Nombre del Query que se encuentra en la entidad
//     * @return
//     * @throws Exception
//     */
//    /**
//     * Permite realizar consulta de una entidad por parametros
//     *
//     * @param param Lista de Parametros a consultar
//     * @param nameQuery Nombre del NameQuery para la consulta
//     * @return
//     * @throws Exception
//     */
//
//    /**
//     * Permite realizar consulta de una entidad por parametros
//     *
//     * @param param Lista de Parametros a consultar
//     * @param nameQuery Nombre del NameQuery para la consulta
//     * @return
//     * @throws Exception
//     */
//   
//
//
//    /**
//     *
//     * @param <T>
//     * @param filtro
//     * @param sql
//     * @return
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    public <T> List<T> select(Object filtro, String sql) throws Exception {
//        try {
//            abrirSession();
//            Query query = session.createFilter(filtro, sql);
//            query.setReadOnly(true);
//            return query.list();
//        } finally {
//            //cerrarSession();
//        }
//    }
//
//    /**
//     *
//     * @param entities
//     * @throws Exception
//     */
//    public void saveall(final Collection entities) throws Exception {
//        try {
//            abrirSession();
//            session.beginTransaction();
//            int i = 0;
//            for (Object entity : entities) {
//                i = i + 1;
//                session.saveOrUpdate(entity);
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.getMessage();
//        } finally {
//            //cerrarSession();
//        }
//    }
    /**
     * Implementacion de los metodos principales de Hibernate
     *
     * @param entity
     * @return boolean
     * @throws Exception
     */
//    public boolean save(Object entity) throws Exception {
//        try {
//            abrirSession();
//            Transaction t = session.beginTransaction();
//            session.saveOrUpdate(entity);
//            t.commit();
//            session.flush();
//            return true;
//        } catch (Exception e) {
//            e.getMessage();
//            session.getTransaction().rollback();
//            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
//            return false;
//        } finally {
//            // cerrarSession();
//        }
//
//    }
//    /**
//     * La función de este metodo es solo crear un nuevo registro con el objeto
//     * enviado
//     *
//     * @param entity
//     * @throws Exception
//     */
//    public void onlySave(Object entity) throws Exception {
//        try {
//            abrirSession();
//            session.beginTransaction();
//            session.save(entity);
//            session.getTransaction().commit();
//            session.flush();
//        } catch (Exception e) {
//            e.getMessage();
//        } finally {
//            //cerrarSession();
//        }
//    }
//    /**
//     *
//     * @param entity
//     * @throws Exception
//     */
//    public void refresh(Object entity) throws Exception {
//        try {
//            abrirSession();
//            session.refresh(entity);
//        } finally {
//            //cerrarSession();
//        }
//    }
//    /**
//     *
//     * @param entity
//     * @return
//     * @throws Exception
//     */
//    public boolean merge(Object entity) throws Exception {
//        try {
//            abrirSession();
//            session.merge(entity);
//            session.getTransaction().commit();
//            session.flush();
//            return true;
//        } catch (Exception e) {
//            e.getMessage();
//            session.getTransaction().rollback();
//            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
//            return false;
//        } finally {
//            //cerrarSession();
//        }
//
//    }
}
