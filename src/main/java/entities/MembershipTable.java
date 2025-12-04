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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "membership_table")
@NamedQueries({
    @NamedQuery(name = "MembershipTable.findAll", query = "SELECT m FROM MembershipTable m"),
    @NamedQuery(name = "MembershipTable.findById", query = "SELECT m FROM MembershipTable m WHERE m.id = :id"),
    @NamedQuery(name = "MembershipTable.findByDescription", query = "SELECT m FROM MembershipTable m WHERE m.description = :description"),
    @NamedQuery(name = "MembershipTable.findByPrice", query = "SELECT m FROM MembershipTable m WHERE m.price = :price"),
    @NamedQuery(name = "MembershipTable.findByTimePeriod", query = "SELECT m FROM MembershipTable m WHERE m.timePeriod = :timePeriod")})
public class MembershipTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "time_period")
    private String timePeriod;
    @JoinTable(name = "membership_participants", joinColumns = {
        @JoinColumn(name = "membership_ID", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_ID", referencedColumnName = "id")})
    @ManyToMany
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mambershipID")
    private Collection<PaymentTable> paymentTableCollection;

    public MembershipTable() {
    }

    public MembershipTable(Integer id) {
        this.id = id;
    }

    public MembershipTable(Integer id, String description, int price, String timePeriod) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.timePeriod = timePeriod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<PaymentTable> getPaymentTableCollection() {
        return paymentTableCollection;
    }

    public void setPaymentTableCollection(Collection<PaymentTable> paymentTableCollection) {
        this.paymentTableCollection = paymentTableCollection;
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
        if (!(object instanceof MembershipTable)) {
            return false;
        }
        MembershipTable other = (MembershipTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MembershipTable[ id=" + id + " ]";
    }
    
}
