package com.bonos.constantes;

import com.bonos.entidades.Proyecto;
import com.bonos.entidades.Tercero;
import com.bonos.facade.ProyectosFacade;
import com.bonos.facade.TercerosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "constantesBean")
@ViewScoped
public class ConstantesBean implements Serializable {

    @EJB
    TercerosFacade tercerosFacade;
    @EJB
    ProyectosFacade proyectosFacade;

    private List<Tercero> terceros;

    private List<Proyecto> proyectos;

    @PostConstruct
    public void init() {
        System.out.println("cargando constantes");
        terceros = tercerosFacade.obtenerTerceros();
        proyectos = proyectosFacade.obtenerProyectos();
    }

    /**
     * @return the terceros
     */
    public List<Tercero> getTerceros() {
        return terceros;
    }

    /**
     * @param terceros the terceros to set
     */
    public void setTerceros(List<Tercero> terceros) {
        this.terceros = terceros;
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
