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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Chuwi
 */
@Entity
@Table(name = "contratos")
public class Contratos implements Serializable {

    //Variables Propias
    private Integer id_contrato;
    private String contrato_nombre;
    private String contrato_numero;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Double valor_firmado;
    private Double precio_venta;

    private Proyecto id_proyecto;
    private Tercero id_tercero;

    public Contratos() {

    }

    /**
     * @return the id_contrato
     */
    @Id
    @Column(name = "id_contrato")
    public Integer getId_contrato() {
        return id_contrato;
    }

    /**
     * @param id_contrato the id_contrato to set
     */
    public void setId_contrato(Integer id_contrato) {
        this.id_contrato = id_contrato;
    }

    /**
     * @return the contrato_nombre
     */
    @Column(name = "contrato_nombre")
    public String getContrato_nombre() {
        return contrato_nombre;
    }

    /**
     * @param contrato_nombre the contrato_nombre to set
     */
    public void setContrato_nombre(String contrato_nombre) {
        this.contrato_nombre = contrato_nombre;
    }

    /**
     * @return the contrato_numero
     */
    @Column(name = "contrato_numero")
    public String getContrato_numero() {
        return contrato_numero;
    }

    /**
     * @param contrato_numero the contrato_numero to set
     */
    public void setContrato_numero(String contrato_numero) {
        this.contrato_numero = contrato_numero;
    }

    /**
     * @return the fecha_inicio
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha_inicio")
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @return the fecha_fin
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha_fin")
    public Date getFecha_fin() {
        return fecha_fin;
    }

    /**
     * @param fecha_fin the fecha_fin to set
     */
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    /**
     * @return the valor_firmado
     */
    @Column(name = "valor_firmado")
    public Double getValor_firmado() {
        return valor_firmado;
    }

    /**
     * @param valor_firmado the valor_firmado to set
     */
    public void setValor_firmado(Double valor_firmado) {
        this.valor_firmado = valor_firmado;
    }

    /**
     * @return the precio_venta
     */
    @Column(name = "precio_venta")
    public Double getPrecio_venta() {
        return precio_venta;
    }

    /**
     * @param precio_venta the precio_venta to set
     */
    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
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

    /**
     * @return the id_tercero
     */
    @JoinColumn(name = "id_tercero")
    @OneToMany(fetch = FetchType.LAZY)
    public Tercero getId_tercero() {
        return id_tercero;
    }

    /**
     * @param id_tercero the id_tercero to set
     */
    public void setId_tercero(Tercero id_tercero) {
        this.id_tercero = id_tercero;
    }
}
