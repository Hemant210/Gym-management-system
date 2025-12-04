/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "payment_table")
@NamedQueries({
    @NamedQuery(name = "PaymentTable.findAll", query = "SELECT p FROM PaymentTable p"),
    @NamedQuery(name = "PaymentTable.findById", query = "SELECT p FROM PaymentTable p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentTable.findByAmount", query = "SELECT p FROM PaymentTable p WHERE p.amount = :amount"),
    @NamedQuery(name = "PaymentTable.findByMethod", query = "SELECT p FROM PaymentTable p WHERE p.method = :method"),
    @NamedQuery(name = "PaymentTable.findByPaymentDate", query = "SELECT p FROM PaymentTable p WHERE p.paymentDate = :paymentDate")})
public class PaymentTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "method")
    private String method;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paymentDate")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @JoinColumn(name = "user_ID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userID;
    @JoinColumn(name = "mambership_ID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MembershipTable mambershipID;

    public PaymentTable() {
    }

    public PaymentTable(Integer id) {
        this.id = id;
    }

    public PaymentTable(Integer id, double amount, String method, Date paymentDate) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.paymentDate = paymentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public MembershipTable getMambershipID() {
        return mambershipID;
    }

    public void setMambershipID(MembershipTable mambershipID) {
        this.mambershipID = mambershipID;
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
        if (!(object instanceof PaymentTable)) {
            return false;
        }
        PaymentTable other = (PaymentTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PaymentTable[ id=" + id + " ]";
    }
    
}
