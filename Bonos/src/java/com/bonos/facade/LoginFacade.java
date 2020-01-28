package com.bonos.facade;

import com.bonos.entidades.Usuario;
import com.bonos.persist.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;

@Stateless
public class LoginFacade {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public Usuario getPasswordByUser(String usuario) {

        List<Usuario> users = null;
        HashMap hm = new HashMap();
        hm.put("usuario", usuario);
        try {
            users = (List<Usuario>) em.selectNameQueryParamList(hm, "Usuario.findByUsuario");
        } catch (Exception ex) {
            Logger.getLogger(LoginFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (users == null || users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }

    }

    public boolean cambiarContrasenia(Usuario usuario, String pass) {

        try {
            usuario.setPassword(BCrypt.hashpw(pass, BCrypt.gensalt()));
            em.save2(usuario, "TUsuario");
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    public void cerrarSesiones() {
        em.cerrarSession();
    }
}
