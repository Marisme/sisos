/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g")
    , @NamedQuery(name = "Grupos.findById", query = "SELECT g FROM Grupos g WHERE g.id = :id")
    , @NamedQuery(name = "Grupos.findByGrupo", query = "SELECT g FROM Grupos g WHERE g.grupo = :grupo")})
public class Grupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "grupo")
    private String grupo;
    @JoinTable(name = "permissao", joinColumns = {
        @JoinColumn(name = "id_grupo", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_item", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Itens> itensCollection;
    @OneToMany(mappedBy = "idgrupo")
    private Collection<Usuarios> usuariosCollection;

    public Grupos() {
    }

    public Grupos(Integer id) {
        this.id = id;
    }

    public Grupos(Integer id, String grupo) {
        this.id = id;
        this.grupo = grupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @XmlTransient
    public Collection<Itens> getItensCollection() {
        return itensCollection;
    }

    public void setItensCollection(Collection<Itens> itensCollection) {
        this.itensCollection = itensCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.Grupos[ id=" + id + " ]";
    }
    
}
