package com.bonos.beans.creacion;

import com.bonos.entidades.Tercero;
import com.bonos.facade.TercerosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("crearTercerosBean")
@ViewScoped
public class CrearTercerosBean implements Serializable {

    private Tercero nuevoTercero;

    @EJB
    TercerosFacade tercerosFacade;

    @PostConstruct
    public void init() {
        System.out.println("Iniciando Interfaz crear Tercero");
        nuevoTercero = new Tercero();
    }

    public void guardarTercero() {
        System.out.println("Guardando Tercero");
        tercerosFacade.guardarTercero(nuevoTercero);
        nuevoTercero = new Tercero();
    }

    /**
     * @return the nuevoTercero
     */
    public Tercero getNuevoTercero() {
        return nuevoTercero;
    }

    /**
     * @param nuevoTercero the nuevoTercero to set
     */
    public void setNuevoTercero(Tercero nuevoTercero) {
        this.nuevoTercero = nuevoTercero;
    }

}
