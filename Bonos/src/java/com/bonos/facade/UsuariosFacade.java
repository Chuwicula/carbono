package com.bonos.facade;

import com.bonos.entidades.Usuario;
import com.bonos.persist.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class UsuariosFacade {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public Integer nuevoIdUsuario() {
        try {
            return (Integer) em.selectNameQueryUniqueResult("Usuario.findMaxId");
        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "Error al generar id");
            Logger.getLogger(RolesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void guardarUsuario(Usuario usuario) {
        try {
            usuario.setId(nuevoIdUsuario());
            if (em.save2(usuario, "Usuario")) {
                utilidad.Utilidades.imprimir_msg("Hehco", "Usuario Guardado");
            } else {
                utilidad.Utilidades.imprimir_msg("Error", "No se pugo guardar la información");
            }
        } catch (Exception ex) {
            utilidad.Utilidades.imprimir_msg("Error", "No se pugo guardar la información");
            Logger.getLogger(UsuariosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> obtenerUsuarios() {
        try {
            return (List<Usuario>) em.selectNameQueryParamList(null, "Usuario.findAll");
        } catch (Exception ex) {
            Logger.getLogger(UsuariosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
