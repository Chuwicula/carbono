/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonos.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Chuwi
 */
@Entity
public class Operacion implements Serializable {

    private Integer id_operacion;
    private String descripcion;
    private Date fecha;
    private Double valor;

    private Contratos id_contrato;
    private Tipo_Operacion id_tipo_operacion;
    private Proyecto id_proyecto;

    public Operacion() {

    }

    /**
     * @return the id_operacion
     */
    @Id
    @Column(name = "id_operacion")
    public Integer getId_operacion() {
        return id_operacion;
    }

    /**
     * @param id_operacion the id_operacion to set
     */
    public void setId_operacion(Integer id_operacion) {
        this.id_operacion = id_operacion;
    }

    /**
     * @return the descripcion
     */
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fecha
     */
    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the valor
     */
    @Column(name = "valor")
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the id_contrato
     */
    @JoinColumn(name = "id_contrato")
    @ManyToOne(fetch = FetchType.LAZY)
    public Contratos getId_contrato() {
        return id_contrato;
    }

    /**
     * @param id_contrato the id_contrato to set
     */
    public void setId_contrato(Contratos id_contrato) {
        this.id_contrato = id_contrato;
    }

    /**
     * @return the id_tipo_operacion
     */
    @JoinColumn(name = "id_tipo_operacion")
    @ManyToOne(fetch = FetchType.LAZY)
    public Tipo_Operacion getId_tipo_operacion() {
        return id_tipo_operacion;
    }

    /**
     * @param id_tipo_operacion the id_tipo_operacion to set
     */
    public void setId_tipo_operacion(Tipo_Operacion id_tipo_operacion) {
        this.id_tipo_operacion = id_tipo_operacion;
    }

    /**
     * @return the id_proyecto
     */
    @JoinColumn(name = "id_proyecto")
    @ManyToOne(fetch = FetchType.LAZY)
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
