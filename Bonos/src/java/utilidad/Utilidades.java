/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidad;

import javax.faces.application.FacesMessage;
import org.primefaces.PrimeFaces;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrador
 */
public class Utilidades {

    public static void imprimir_msg(String top, String msg) {
        PrimeFaces reqcontext = PrimeFaces.current();
        reqcontext.dialog().showMessageDynamic(new FacesMessage(top, msg));

    }

}
