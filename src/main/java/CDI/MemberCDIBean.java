/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDI;

import ejb.adminBeanLocal;
import entities.MemberTable;
import entities.StaffTable;
import java.io.Serializable;
import java.util.Collection;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;

/**
 *
 * @author Ankit Rathod
 */
@Named(value = "memberCDIBean")
@ViewScoped
public class MemberCDIBean implements Serializable {

    @EJB
    private adminBeanLocal abl;

    private Collection<MemberTable> members;

    private StaffTable staffId;
    private String name, address, email, contact, gender;

    private Collection<StaffTable> staffs;

    public Collection<MemberTable> getMembers() {
        return members;
    }

    /**
     * Creates a new instance of MemberCDIBean
     */
    public void setMembers(Collection<MemberTable> members) {
        this.members = abl.getAllMembers();
    }

    public StaffTable getStaffId() {
        return staffId;
    }

    public void setStaffId(StaffTable staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Collection<StaffTable> getStaffs() {
        return staffs;
    }

    public void setStaffs(Collection<StaffTable> staffs) {
        this.staffs = abl.getAllStaffs();
    }

    public MemberCDIBean() {

    }

    @PostConstruct
    public void init() {
        staffId = new StaffTable();
        this.staffs = abl.getAllStaffs();
        this.members=abl.getAllMembers();
    }

    public String addMember(Integer userId) {
        System.out.println("Staff id:" + staffId.getId());
        System.out.println("User id:" + userId);
        System.out.println("Gender:" + gender);
        System.out.println("Address:" + address);
        abl.addMember(staffId.getId(), userId, gender, address, 1000, name, email, contact);
        return "product.jsf?faces-redirect=true";
    }

    public void deleteMember(Integer memberId, Integer staffId, Integer userId) {
        abl.deleteMember(memberId, staffId, userId);
    }

    public Collection<MemberTable> getmember() {
        Collection<MemberTable> ms = abl.getAllMembers();
        return ms;
    }

}
