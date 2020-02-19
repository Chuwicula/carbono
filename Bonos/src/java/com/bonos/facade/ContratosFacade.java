package com.bonos.facade;

import com.bonos.entidades.Contratos;
import com.bonos.persist.EntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class ContratosFacade {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public boolean guardarContrato(Contratos contrato) {
        if (contrato != null) {
            contrato.setId_contrato(newContratoId());
            try {
                if (em.save2(contrato, "Contratos")) {
                    System.out.println("Contrato Guardado");
                    utilidad.Utilidades.imprimir_msg("Hecho", "Contrato Guardado");
                } else {
                    System.out.println("No Guardado contrato");
                    utilidad.Utilidades.imprimir_msg("Error", "Contrato NO Guardado");
                    return false;
                }
            } catch (Exception ex) {
                Logger.getLogger(ContratosFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public Integer newContratoId() {
        try {
            return (Integer) em.selectNameQueryUniqueResult("newIdContrato");
        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "No se puedo gerenar id");
            return null;
            //Logger.getLogger(ContratosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
