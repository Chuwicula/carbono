/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonos.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Chuwi
 */
@Entity
public class Tercero implements Serializable {

    private Integer id_tercero;
    private String nombre;
    private String nit;
    private String telefono;
    private String correo;
    private List<Contratos> contratos;

    public Tercero() {

    }

    /**
     * @return the id_tercero
     */
    @Id
    public Integer getId_tercero() {
        return id_tercero;
    }

    /**
     * @param id_tercero the id_tercero to set
     */
    public void setId_tercero(Integer id_tercero) {
        this.id_tercero = id_tercero;
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

    /**
     * @return the nit
     */
    @Column(name = "nit")
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the telefono
     */
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    @Column(name = "correo")
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contratos
     */
    @OneToMany(mappedBy = "id_tercero")
    public List<Contratos> getContratos() {
        return contratos;
    }

    /**
     * @param contratos the contratos to set
     */
    public void setContratos(List<Contratos> contratos) {
        this.contratos = contratos;
    }

}
