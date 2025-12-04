/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDI;

import ejb.adminBeanLocal;
import entities.User;
import java.io.IOException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Ankit Rathod
 */
@Named(value = "profileCDI")
@ViewScoped
public class ProfileCDI implements Serializable {

    @EJB
    private adminBeanLocal abl;

    private Integer userId;
    private User u;

    /**
     * Creates a new instance of ProfileCDI
     */
    public ProfileCDI() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        String userIdString = context.getExternalContext().getRequestParameterMap().get("userId");

        if (userIdString != null) {
            try {
                userId = Integer.valueOf(userIdString);
                loadUserDetails(userId);
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                HttpSession sesssion = request.getSession();
                sesssion.setAttribute("name", this.u.getName());
            } catch (NumberFormatException e) {

            }
        }
    }

    public void loadUserDetails(Integer userId) {
        this.u = abl.getUserById(userId);
    }

    public void updateProfile() {
        try {
            abl.updateuser(userId, u.getName(), u.getEmail(), u.getPassword(), u.getAddress(), u.getContact(), u.getGroupmasterID().getGroupMasterId());

            FacesContext.getCurrentInstance().getExternalContext().redirect("/gym_pro_new/user_profile.jsf?userId=" + u.getId());
        } catch (IOException ex) {
            Logger.getLogger(ProfileCDI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
