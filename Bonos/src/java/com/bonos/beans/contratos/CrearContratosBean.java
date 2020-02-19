package com.bonos.beans.contratos;

import com.bonos.entidades.Contratos;
import com.bonos.entidades.Proyecto;
import com.bonos.entidades.Tercero;
import com.bonos.facade.ContratosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "crearContratosBean")
@ViewScoped
public class CrearContratosBean implements Serializable {

    private Contratos contratoNuevo;
    private Proyecto proyecto;
    private Tercero tercero;

    @EJB
    ContratosFacade contratosFacade;

    @PostConstruct
    public void init() {
        System.out.println("Init crear contratos");
        contratoNuevo = new Contratos();
    }

    public void crearContratos() {
        contratoNuevo.setId_proyecto(proyecto);
        contratoNuevo.setId_tercero(tercero);
        contratosFacade.guardarContrato(contratoNuevo);
    }

    /**
     * @return the contratoNuevo
     */
    public Contratos getContratoNuevo() {
        return contratoNuevo;
    }

    /**
     * @param contratoNuevo the contratoNuevo to set
     */
    public void setContratoNuevo(Contratos contratoNuevo) {
        this.contratoNuevo = contratoNuevo;
    }

    /**
     * @return the proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * @return the tercero
     */
    public Tercero getTercero() {
        return tercero;
    }

    /**
     * @param tercero the tercero to set
     */
    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

}
