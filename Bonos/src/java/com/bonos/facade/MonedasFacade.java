package com.bonos.facade;

import com.bonos.entidades.Monedas;
import com.bonos.persist.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class MonedasFacade {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public Integer obtenerId() {
        try {
            return (Integer) em.selectNameQueryUniqueResult("Monedas.maxId");
        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "No se pudo generar id");
            Logger.getLogger(MonedasFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Monedas> obtenerMonedas() {
        try {
            return (List<Monedas>) em.selectNameQueryParamList(null, "Monedas.getAll");
        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "No se pudieron obtener datos de monedas");
            Logger.getLogger(MonedasFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
