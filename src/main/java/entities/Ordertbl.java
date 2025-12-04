/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Ankit Rathod
 */
@Entity
@Table(name = "ordertbl")
@NamedQueries({
    @NamedQuery(name = "Ordertbl.findAll", query = "SELECT o FROM Ordertbl o"),
    @NamedQuery(name = "Ordertbl.findById", query = "SELECT o FROM Ordertbl o WHERE o.id = :id"),
    @NamedQuery(name = "Ordertbl.findByDeliveredAt", query = "SELECT o FROM Ordertbl o WHERE o.deliveredAt = :deliveredAt"),
    @NamedQuery(name = "Ordertbl.findByIsDelivered", query = "SELECT o FROM Ordertbl o WHERE o.isDelivered = :isDelivered"),
    @NamedQuery(name = "Ordertbl.findByIsPaid", query = "SELECT o FROM Ordertbl o WHERE o.isPaid = :isPaid"),
    @NamedQuery(name = "Ordertbl.findByOrderDate", query = "SELECT o FROM Ordertbl o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Ordertbl.findByShipmentAddress", query = "SELECT o FROM Ordertbl o WHERE o.shipmentAddress = :shipmentAddress"),
    @NamedQuery(name = "Ordertbl.findByShippingPrice", query = "SELECT o FROM Ordertbl o WHERE o.shippingPrice = :shippingPrice"),
    @NamedQuery(name = "Ordertbl.findByTotalPrice", query = "SELECT o FROM Ordertbl o WHERE o.totalPrice = :totalPrice")})
public class Ordertbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "deliveredAt")
    private String deliveredAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isDelivered")
    private boolean isDelivered;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isPaid")
    private boolean isPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "shipmentAddress")
    private String shipmentAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shippingPrice")
    private int shippingPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalPrice")
    private int totalPrice;
    @JoinColumn(name = "user_ID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderID")
    private Collection<OrderItem> orderItemCollection;

    public Ordertbl() {
    }

    public Ordertbl(Integer id) {
        this.id = id;
    }

    public Ordertbl(Integer id, String deliveredAt, boolean isDelivered, boolean isPaid, Date orderDate, String shipmentAddress, int shippingPrice, int totalPrice) {
        this.id = id;
        this.deliveredAt = deliveredAt;
        this.isDelivered = isDelivered;
        this.isPaid = isPaid;
        this.orderDate = orderDate;
        this.shipmentAddress = shipmentAddress;
        this.shippingPrice = shippingPrice;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public boolean getIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }

    public int getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(int shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Collection<OrderItem> getOrderItemCollection() {
        return orderItemCollection;
    }

    public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
        this.orderItemCollection = orderItemCollection;
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
        if (!(object instanceof Ordertbl)) {
            return false;
        }
        Ordertbl other = (Ordertbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordertbl[ id=" + id + " ]";
    }
    
}
