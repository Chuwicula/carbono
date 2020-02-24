package com.bonos.beans.creacion;

import com.bonos.entidades.Roles;
import com.bonos.facade.RolesFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("crearRolesBean")
@ViewScoped
public class CrearRolesBean implements Serializable {
    
    private Roles nuevoRol;
    
    @EJB
    RolesFacade rolesFacade;
    
    @PostConstruct
    public void init() {
        nuevoRol = new Roles();
        
    }
    
    public void guardarRol() {
        rolesFacade.guardarRol(nuevoRol);
    }

    /**
     * @return the nuevoRol
     */
    public Roles getNuevoRol() {
        return nuevoRol;
    }

    /**
     * @param nuevoRol the nuevoRol to set
     */
    public void setNuevoRol(Roles nuevoRol) {
        this.nuevoRol = nuevoRol;
    }
    
}
