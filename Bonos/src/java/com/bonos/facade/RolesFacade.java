package com.bonos.facade;

import com.bonos.entidades.Roles;
import com.bonos.persist.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class RolesFacade {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public Integer nuevoIdRol() {
        try {
            return (Integer) em.selectNameQueryUniqueResult("Roles.findMaxId");
        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "Error al generar id");
            Logger.getLogger(RolesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void guardarRol(Roles roles) {

        try {
            roles.setId_rol(nuevoIdRol());
            if (em.save2(roles, "Roles")) {
                utilidad.Utilidades.imprimir_msg("Hecho", "Rol creado");
            } else {
                utilidad.Utilidades.imprimir_msg("Error", "No se pudo guardad información del rol");
            }

        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "No se pudo guardad información del rol");
            Logger.getLogger(RolesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Roles> obtenerRoles() {
        try {
            return (List<Roles>) em.selectNameQueryParamList(null, "Roles.findAll");
        } catch (Exception ex) {
            Logger.getLogger(RolesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
