package com.bonos.facade;

import com.bonos.entidades.Proyecto;
import com.bonos.persist.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class ProyectosFacade {
    
    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");
    
    public List<Proyecto> obtenerProyectos() {
        
        try {
            return (List<Proyecto>) em.selectNameQueryParamList(null, "Proyecto.findAllMin");
        } catch (Exception ex) {
            Logger.getLogger(ProyectosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public Integer obtenerId() {
        try {
            return (Integer) em.selectNameQueryUniqueResult("Proyecto.findNextId");
        } catch (Exception ex) {
            Logger.getLogger(ProyectosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void guardarProyecto(Proyecto proyecto) {
        try {
            proyecto.setId_proyecto(obtenerId());
            if (em.save2(proyecto, "Proyecto")) {
                utilidad.Utilidades.imprimir_msg("Hecho", "Proyecto guardado");
            } else {
                utilidad.Utilidades.imprimir_msg("Error", "No se pudo ejecutar la operación");
                
            }
        } catch (Exception ex) {
            Logger.getLogger(ProyectosFacade.class.getName()).log(Level.SEVERE, null, ex);
            utilidad.Utilidades.imprimir_msg("Error", "No se pudo ejecutar la operación");
        }
    }
    
}
