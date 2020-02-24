package com.bonos.facade;

import com.bonos.entidades.Tercero;
import com.bonos.persist.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class TercerosFacade implements Serializable {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public void guardarTercero(Tercero tercero) {

        try {
            tercero.setId_tercero(newTerceroId());
            System.out.println("id " + tercero.getId_tercero());
            if (em.save2(tercero, "Tercero")) {
                utilidad.Utilidades.imprimir_msg("Hecho", "Tercero Guardado");
            } else {
                utilidad.Utilidades.imprimir_msg("Error", "Tercero NO Guardado");
            }
        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "Tercero NO Guardado");
            Logger.getLogger(TercerosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Integer newTerceroId() {
        try {
            return (Integer) em.selectNameQueryUniqueResult("newIdTercero");

        } catch (Exception ex) {
            Logger.getLogger(TercerosFacade.class.getName()).log(Level.SEVERE, null, ex);
            utilidad.Utilidades.imprimir_msg("Error", "No se puedo asignar ID");
            return null;
        }
    }

    public List<Tercero> obtenerTerceros() {

        try {
            return (List<Tercero>) em.selectNameQueryParamList(null, "allTercero");
        } catch (Exception ex) {
            Logger.getLogger(TercerosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
