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
@Table(name = "monedas")
@NamedQueries({
    @NamedQuery(name = "Monedas.maxId", query = "SELECT (CASE WHEN MAX(c.moneda_id) IS NULL THEN 1 ELSE (MAX(c.moneda_id) + 1) END) FROM Monedas c")
    ,
 @NamedQuery(name = "Monedas.getAll", query = "SELECT c FROM Monedas c")}
)
public class Monedas implements Serializable {

    private Integer moneda_id;

    private String nombre;
    
    private List<Contratos> contratoses;

    public Monedas() {

    }

    /**
     * @return the moneda_id
     */
    @Id
    @Column(name = "moneda_id")
    public Integer getMoneda_id() {
        return moneda_id;
    }

    /**
     * @param moneda_id the moneda_id to set
     */
    public void setMoneda_id(Integer moneda_id) {
        this.moneda_id = moneda_id;
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
     * @return the contratoses
     */
    @OneToMany(mappedBy = "moneda_id")
    public List<Contratos> getContratoses() {
        return contratoses;
    }

    /**
     * @param contratoses the contratoses to set
     */
    public void setContratoses(List<Contratos> contratoses) {
        this.contratoses = contratoses;
    }

}
