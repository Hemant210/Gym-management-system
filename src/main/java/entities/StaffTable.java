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
@Table(name = "staff_table")
@NamedQueries({
    @NamedQuery(name = "StaffTable.findAll", query = "SELECT s FROM StaffTable s"),
    @NamedQuery(name = "StaffTable.findById", query = "SELECT s FROM StaffTable s WHERE s.id = :id"),
    @NamedQuery(name = "StaffTable.findByStaffName", query = "SELECT s FROM StaffTable s WHERE s.staffName = :staffName"),
    @NamedQuery(name = "StaffTable.findByEmail", query = "SELECT s FROM StaffTable s WHERE s.email = :email"),
    @NamedQuery(name = "StaffTable.findByHireDate", query = "SELECT s FROM StaffTable s WHERE s.hireDate = :hireDate"),
    @NamedQuery(name = "StaffTable.findByContact", query = "SELECT s FROM StaffTable s WHERE s.contact = :contact"),
    @NamedQuery(name = "StaffTable.findBySalary", query = "SELECT s FROM StaffTable s WHERE s.salary = :salary"),
    @NamedQuery(name = "StaffTable.findByStaffPosition", query = "SELECT s FROM StaffTable s WHERE s.staffPosition = :staffPosition"),
    @NamedQuery(name = "StaffTable.findByStaffImg", query = "SELECT s FROM StaffTable s WHERE s.staffImg = :staffImg")})
public class StaffTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "staff_name")
    private String staffName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "hire_date")
    private String hireDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contact")
    private String contact;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private int salary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "staff_position")
    private String staffPosition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "staff_img")
    private String staffImg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffId")
    private Collection<MemberTable> memberTableCollection;

    public StaffTable() {
    }

    public StaffTable(Integer id) {
        this.id = id;
    }

    public StaffTable(Integer id, String staffName, String email, String hireDate, String contact, int salary, String staffPosition, String staffImg) {
        this.id = id;
        this.staffName = staffName;
        this.email = email;
        this.hireDate = hireDate;
        this.contact = contact;
        this.salary = salary;
        this.staffPosition = staffPosition;
        this.staffImg = staffImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getStaffImg() {
        return staffImg;
    }

    public void setStaffImg(String staffImg) {
        this.staffImg = staffImg;
    }

    public Collection<MemberTable> getMemberTableCollection() {
        return memberTableCollection;
    }

    public void setMemberTableCollection(Collection<MemberTable> memberTableCollection) {
        this.memberTableCollection = memberTableCollection;
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
        if (!(object instanceof StaffTable)) {
            return false;
        }
        StaffTable other = (StaffTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StaffTable[ id=" + id + " ]";
    }
    
}
