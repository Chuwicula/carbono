package com.bonos.beans.contratos;

import com.bonos.entidades.Contratos;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "crearContratosBean")
@ViewScoped
public class CrearContratosBean implements Serializable {

    private Contratos contratoNuevo;

    @PostConstruct
    public void init() {
        System.out.println("Init crear contratos");
        contratoNuevo = new Contratos();
        
        
         
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

}
