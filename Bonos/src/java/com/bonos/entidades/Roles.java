package com.bonos.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {

    private Integer id_rol;
    private String nombre_rol;

    public Roles() {

    }

    /**
     * @return the id_rol
     */
    @Id
    @Column(name = "id_rol")
    public Integer getId_rol() {
        return id_rol;
    }

    /**
     * @param id_rol the id_rol to set
     */
    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    /**
     * @return the nombre_rol
     */
    @Column(name = "nombre_rol")
    public String getNombre_rol() {
        return nombre_rol;
    }

    /**
     * @param nombre_rol the nombre_rol to set
     */
    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

}
