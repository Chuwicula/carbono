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
import javax.persistence.ManyToOne;

/**
 * 
 *
 * @author Chuwi
 */
@Entity
public class Usuario implements Serializable {

    //variable propias
    private Integer id;
    private String usuario;
    private String password;
    private String nombre;
    private String identificacion;
    private String telefono;
    private String correo;

    private Roles id_rol;

    public Usuario() {

    }

    /**
     * @return the id
     */
    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    
    
    /**
     * @return the usuario
     */
    @Column(name = "usuario")
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the identificacion
     */
    @Column(name = "identificacion")
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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
     * @return the id_rol
     */
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    public Roles getId_rol() {
        return id_rol;
    }

    /**
     * @param id_rol the id_rol to set
     */
    public void setId_rol(Roles id_rol) {
        this.id_rol = id_rol;
    }

}
