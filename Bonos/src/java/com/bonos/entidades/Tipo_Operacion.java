/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonos.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Chuwi
 */
@Entity
public class Tipo_Operacion implements Serializable {

    private Integer id_tipo_operacion;
    private String nombre;

    public Tipo_Operacion() {

    }

    /**
     * @return the id_tipo_operacion
     */
    @Id
    @Column(name = "id_tipo_operacion")
    public Integer getId_tipo_operacion() {
        return id_tipo_operacion;
    }

    /**
     * @param id_tipo_operacion the id_tipo_operacion to set
     */
    public void setId_tipo_operacion(Integer id_tipo_operacion) {
        this.id_tipo_operacion = id_tipo_operacion;
    }

    /**
     * @return the nombre
     */
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
