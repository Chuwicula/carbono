package com.bonos.persist;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//Clase encargada de  manejar la sesion de hibernate, consulta y administra el archivo hibernate.xml
public class TransactionManager {
    
    private final String ruta;//Variable que almacena la ruta del archivo hibernate.xml
    private final SessionFactory sessionFactory;//Fabrica administradora de sesiones
    private static List<TransactionManager> instancias;//Lista de sesiones de hibernate abiertas

    //Metodo constructor, recibe como paramatro la ruta del archivo hibernate.xml, es privado 
    //porque hay control sobre la creacion de sesiones
    private TransactionManager(String ruta) {
        this.sessionFactory = buildSessionFactory(ruta);
        this.ruta = ruta;
    }

    /**
     * Metodo  que retorna instancias de sesion de hibernate, se agregan todas las instancias a una coleccion
     * @param rutaConf ruta del archivo de configuracion hibernate.xml
     * @return
     */
    public synchronized static TransactionManager getInstance(String rutaConf) {
        //Si la coleccion no ha sido creada entonces se llama a su constructor y se aniade la primera sesion
        if (instancias == null) {
            instancias = new ArrayList<>();
            instancias.add(new TransactionManager(rutaConf));
            return instancias.get(0);
        }
        //Si la sesion existe se retorna
        for (TransactionManager element : instancias) {
            if (element.ruta.equals(rutaConf)) {
                return element;
            }
        }
        //Si la sesion no  existe  se crea una nueva y se aniade a la coleccion
        TransactionManager nuevaInstancia = new TransactionManager(rutaConf);
        instancias.add(nuevaInstancia);
        return nuevaInstancia;
    }

    /**
     * Permite mapear el archivo de configuracion de hibernate creando una
     * fabrica que provee las conexiones a base de datos.
     *
     * @param rutaConf : ruta del archivo de configuracion de hibernate.
     * @return: el constructor que permitira construir las conexiones
     * necesarias.
     */
    private static SessionFactory buildSessionFactory(String rutaConf) {
        try {
            Configuration conf = new Configuration();
            conf.configure(rutaConf);
            ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            return conf.buildSessionFactory(service);
        } catch (HibernateException ex) {
            System.out.println("El inicio de sesion fallo. para:" + rutaConf + " - " + ex.getMessage());
            throw new ExceptionInInitializerError("El inicio de sesion fallo. para:" + rutaConf + " - " + ex.getMessage());
        }
    }

    /**
     * *
     * Permite obtener una session de la conexion.
     *
     * @return conexion.
     * @throws Exception
     */
    public Session getCurrentSession() throws Exception {
        try {
            return sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new Exception("No se puede crear una conexion a base de datos", e);
        }
    }

}
