package com.bonos.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT g FROM Roles g")
    ,
    @NamedQuery(name = "Roles.findMaxId", query = "SELECT (CASE WHEN MAX(c.id_rol) IS NULL THEN 1 ELSE (MAX(c.id_rol) + 1) END) FROM Roles c"),})
public class Roles implements Serializable {

    private Integer id_rol;
    private String nombre_rol;
    private List<Usuario> usuarios;

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

    /**
     * @return the usuarios
     */
    @OneToMany(mappedBy = "id_rol")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
