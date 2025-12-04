/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Ankit Rathod
 */
@Entity
@Table(name = "groupmaster")
@NamedQueries({
    @NamedQuery(name = "Groupmaster.findAll", query = "SELECT g FROM Groupmaster g"),
    @NamedQuery(name = "Groupmaster.findByGroupMasterId", query = "SELECT g FROM Groupmaster g WHERE g.groupMasterId = :groupMasterId"),
    @NamedQuery(name = "Groupmaster.findByGroupName", query = "SELECT g FROM Groupmaster g WHERE g.groupName = :groupName")})
public class Groupmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupMasterId")
    private Integer groupMasterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "groupName")
    private String groupName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupmasterID")
    private Collection<User> userCollection;

    public Groupmaster() {
    }

    public Groupmaster(Integer groupMasterId) {
        this.groupMasterId = groupMasterId;
    }

    public Groupmaster(Integer groupMasterId, String groupName) {
        this.groupMasterId = groupMasterId;
        this.groupName = groupName;
    }

    public Integer getGroupMasterId() {
        return groupMasterId;
    }

    public void setGroupMasterId(Integer groupMasterId) {
        this.groupMasterId = groupMasterId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupMasterId != null ? groupMasterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupmaster)) {
            return false;
        }
        Groupmaster other = (Groupmaster) object;
        if ((this.groupMasterId == null && other.groupMasterId != null) || (this.groupMasterId != null && !this.groupMasterId.equals(other.groupMasterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groupmaster[ groupMasterId=" + groupMasterId + " ]";
    }
    
}
