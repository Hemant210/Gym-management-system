/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Ankit Rathod
 */
@Entity
@Table(name = "member")
@NamedQueries({
    @NamedQuery(name = "MemberTable.findAll", query = "SELECT m FROM MemberTable m"),
    @NamedQuery(name = "MemberTable.findByMemberId", query = "SELECT m FROM MemberTable m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "MemberTable.findByGender", query = "SELECT m FROM MemberTable m WHERE m.gender = :gender"),
    @NamedQuery(name = "MemberTable.findByAddress", query = "SELECT m FROM MemberTable m WHERE m.address = :address"),
    @NamedQuery(name = "MemberTable.findByPrice", query = "SELECT m FROM MemberTable m WHERE m.price = :price"),
    @NamedQuery(name = "MemberTable.findByName", query = "SELECT m FROM MemberTable m WHERE m.name = :name"),
    @NamedQuery(name = "MemberTable.findByEmail", query = "SELECT m FROM MemberTable m WHERE m.email = :email"),
    @NamedQuery(name = "MemberTable.findByContact", query = "SELECT m FROM MemberTable m WHERE m.contact = :contact")})
public class MemberTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "memberId")
    private Integer memberId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "contact")
    private String contact;
    @JoinColumn(name = "staffId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StaffTable staffId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public MemberTable() {
    }

    public MemberTable(Integer memberId) {
        this.memberId = memberId;
    }

    public MemberTable(Integer memberId, String gender, String address, int price, String name, String email, String contact) {
        this.memberId = memberId;
        this.gender = gender;
        this.address = address;
        this.price = price;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public StaffTable getStaffId() {
        return staffId;
    }

    public void setStaffId(StaffTable staffId) {
        this.staffId = staffId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberTable)) {
            return false;
        }
        MemberTable other = (MemberTable) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MemberTable[ memberId=" + memberId + " ]";
    }
    
}
