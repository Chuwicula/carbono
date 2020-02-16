package com.bonos.beans;

import com.bonos.entidades.Proyecto;
import com.bonos.persist.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "ProyectosBean")
@SessionScoped
public class ProyectosBean implements Serializable {

    private List<Proyecto> proyectos;

    EntityManager em = EntityManager.getInstance("hibernate.cfg.xml");

    @PostConstruct
    public void init() {
        try {
            //cargue todos los proyectos
            proyectos = (List<Proyecto>) em.selectNameQueryParamList(null, "Proyecto.findAll");
            System.out.println("Tamano:" + proyectos.size());
        } catch (Exception ex) {
            Logger.getLogger(ProyectosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the proyectos
     */
    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

}
