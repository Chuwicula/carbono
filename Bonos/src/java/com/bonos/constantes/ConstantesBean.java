package com.bonos.constantes;

import com.bonos.entidades.Tercero;
import com.bonos.facade.TercerosFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "constantesBean")
@SessionScoped
public class ConstantesBean implements Serializable {

    @EJB
    TercerosFacade tercerosFacade;

    private List<Tercero> terceros;

    public void init() {
        terceros = tercerosFacade.obtenerTerceros();
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

}
