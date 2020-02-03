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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Chuwi
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT DISTINCT c FROM Proyecto c LEFT JOIN FETCH c.resumenes e GROUP BY c,e"),
    @NamedQuery(name = "Proyecto.findNextId", query = "SELECT COALESCE(MAX(c.id_proyecto) + 1,1) FROM Proyecto c"),
   })
public class Proyecto implements Serializable {

    private Integer id_proyecto;
    private String nombre;
    private Usuario id_usuario;

    private List<Contratos> contratos;
    private List<Operacion> operaciones;
    private List<Resumen_VCU> Resumenes;

    public Proyecto() {

    }

    /**
     * @return the id_proyecto
     */
    @Id
    @Column(name = "id_proyecto")
    public Integer getId_proyecto() {
        return id_proyecto;
    }

    /**
     * @param id_proyecto the id_proyecto to set
     */
    public void setId_proyecto(Integer id_proyecto) {
        this.id_proyecto = id_proyecto;
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
     * @return the id_usuario
     */
    @JoinColumn(name = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the contratos
     */
    @OneToMany(mappedBy = "id_proyecto")
    public List<Contratos> getContratos() {
        return contratos;
    }

    /**
     * @param contratos the contratos to set
     */
    public void setContratos(List<Contratos> contratos) {
        this.contratos = contratos;
    }

    /**
     * @return the operaciones
     */
    @OneToMany(mappedBy = "id_proyecto")
    public List<Operacion> getOperaciones() {
        return operaciones;
    }

    /**
     * @param operaciones the operaciones to set
     */
    public void setOperaciones(List<Operacion> operaciones) {
        this.operaciones = operaciones;
    }

    /**
     * @return the Resumenes
     */
    @OneToMany(mappedBy = "id_proyecto")
    public List<Resumen_VCU> getResumenes() {
        return Resumenes;
    }

    /**
     * @param resumenes the resumenes to set
     */
    public void setResumenes(List<Resumen_VCU> resumenes) {
        this.Resumenes = resumenes;
    }

}
