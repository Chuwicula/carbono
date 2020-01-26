/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonos.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Chuwi
 */
@Entity
public class Resumen_VCU implements Serializable {

    private Integer id_cosecha;
    private Integer anio;
    private Integer no_verificados;
    private Integer no_emitidos;
    private Integer no_retirados;

    private Proyecto id_proyecto;

    public Resumen_VCU() {

    }

    /**
     * @return the id_cosecha
     */
    @Id
    @Column(name = "id_cosecha")
    public Integer getId_cosecha() {
        return id_cosecha;
    }

    /**
     * @param id_cosecha the id_cosecha to set
     */
    public void setId_cosecha(Integer id_cosecha) {
        this.id_cosecha = id_cosecha;
    }

    /**
     * @return the anio
     */
    @Column(name = "anio")
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the no_verificados
     */
    @Column(name = "no_verificados")
    public Integer getNo_verificados() {
        return no_verificados;
    }

    /**
     * @param no_verificados the no_verificados to set
     */
    public void setNo_verificados(Integer no_verificados) {
        this.no_verificados = no_verificados;
    }

    /**
     * @return the no_emitidos
     */
    @Column(name = "no_emitidos")
    public Integer getNo_emitidos() {
        return no_emitidos;
    }

    /**
     * @param no_emitidos the no_emitidos to set
     */
    public void setNo_emitidos(Integer no_emitidos) {
        this.no_emitidos = no_emitidos;
    }

    /**
     * @return the no_retirados
     */
    @Column(name = "no_retidaros")
    public Integer getNo_retirados() {
        return no_retirados;
    }

    /**
     * @param no_retirados the no_retirados to set
     */
    public void setNo_retirados(Integer no_retirados) {
        this.no_retirados = no_retirados;
    }

    /**
     * @return the id_proyecto
     */
    @JoinColumn(name = "id_proyecto")
    @OneToMany(fetch = FetchType.LAZY)
    public Proyecto getId_proyecto() {
        return id_proyecto;
    }

    /**
     * @param id_proyecto the id_proyecto to set
     */
    public void setId_proyecto(Proyecto id_proyecto) {
        this.id_proyecto = id_proyecto;
    }
}
