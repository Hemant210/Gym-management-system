
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CDI;

import ejb.adminBeanLocal;
import entities.User;
import java.io.Serializable;
import java.util.Set;
import jakarta.inject.Named;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import static jakarta.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static jakarta.security.enterprise.AuthenticationStatus.SUCCESS;
import jakarta.security.enterprise.SecurityContext;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@Named(value = "loginBean")
@SessionScoped
public class loginCDIBean implements Serializable {

    @Inject
    SecurityContext ctx;

    @EJB
    adminBeanLocal abl;

    User u;

    public adminBeanLocal getAbl() {
        return abl;
    }

    public void setAbl(adminBeanLocal abl) {
        this.abl = abl;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    private String email;
    private String password;
    private Set<String> roles;
    private String errorstatus;
    private AuthenticationStatus status;

    public SecurityContext getCtx() {
        return ctx;
    }

    public void setCtx(SecurityContext ctx) {
        this.ctx = ctx;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getErrorstatus() {
        return errorstatus;
    }

    public void setErrorstatus(String errorstatus) {
        this.errorstatus = errorstatus;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public String login() {

        try {
            // Load user from DB
            this.u = abl.getUserByEmail(email);

            if (this.u == null) {
                errorstatus = "Invalid email or password";
                return "login";
            }

//            // VERIFY PASSWORD USING PBKDF2
//            Pbkdf2PasswordHashImpl hash = new Pbkdf2PasswordHashImpl();
//            boolean valid = hash.verify(password.toCharArray(), this.u.getPassword());
//
//            if (!valid) {
//                errorstatus = "Invalid email or password";
//                return "login";
//            }


            if(!password.equals(this.getPassword())){
             errorstatus = "Invalid email or password";
             return "login";
            }
            // Create session
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            HttpSession session = request.getSession(true);

            session.setAttribute("userId", u.getId());
            session.setAttribute("email", u.getEmail());
            session.setAttribute("name", u.getName());
            session.setAttribute("role", u.getRoleName());

            // Redirect by role
            if ("admin".equalsIgnoreCase(u.getRoleName())) {
                return "/admin/admin_dashboard.jsf?faces-redirect=true";
            } else {
                return "/home.jsf?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorstatus = "Something went wrong during login.";
            return "login";
        }
    }

    /**
     * public String login() { try { FacesContext context =
     * FacesContext.getCurrentInstance();
     *
     * // Load user by email this.u = abl.getUserByEmail(email);
     *
     * if (this.u == null) { errorstatus = "Invalid email or password"; return
     * "login"; }
     *
     * Pbkdf2PasswordHashImpl passwordHash = new Pbkdf2PasswordHashImpl();
     *
     * boolean valid = passwordHash.verify(password.toCharArray(),
     * this.u.getPassword());
     *
     * if (!valid) { errorstatus = "Invalid email or password"; return "login";
     * }
     *
     * // Load role String role = this.u.getRoleName(); System.out.println("User
     * role = " + role);
     *
     * // Authenticate so container knows user is logged in Credential
     * credential = new UsernamePasswordCredential(email, new
     * jakarta.security.enterprise.credential.Password(password));
     * HttpServletRequest request = (HttpServletRequest)
     * context.getExternalContext().getRequest(); HttpServletResponse response =
     * (HttpServletResponse) context.getExternalContext().getResponse();
     *
     * status = ctx.authenticate(request, response,
     * withParams().credential(credential));
     *
     * if (status.equals(SEND_CONTINUE)) { context.responseComplete(); return
     * null; }
     *
     * if (status.equals(SUCCESS)) { if ("admin".equalsIgnoreCase(role)) {
     * return "/admin/admin_dashboard.jsf?faces-redirect=true"; } if
     * ("user".equalsIgnoreCase(role)) { return "home.jsf?faces-redirect=true";
     * } }
     *
     * errorstatus = "Unauthorized access!"; return "login";
     *
     * } catch (Exception e) { errorstatus = "Login failed!";
     * e.printStackTrace(); return "login"; } }
     *
     * /**
     * public String login() { try { FacesContext context =
     * FacesContext.getCurrentInstance(); Credential credential = new
     * UsernamePasswordCredential(email, new
     * jakarta.security.enterprise.credential.Password(password));
     * HttpServletRequest request = (HttpServletRequest)
     * FacesContext.getCurrentInstance().getExternalContext().getRequest();
     * HttpServletResponse response = (HttpServletResponse)
     * FacesContext.getCurrentInstance().getExternalContext().getResponse();
     *
     * HttpSession session = request.getSession();
     * session.setAttribute("loginMB", this); this.u =
     * abl.getUserByEmail(email); session.setAttribute("userId",
     * this.u.getId()); session.setAttribute("name", this.u.getName());
     * System.out.println("user id" + this.u.getId());
     *
     * status = ctx.authenticate(request, response,
     * withParams().credential(credential));
     *
     * System.out.println("status from login bean" + status);
     *
     * // AuthenticationStatus mystatus = AuthenticationStatus.; if
     * (status.equals(SEND_CONTINUE)) { // Authentication mechanism has send a
     * redirect, should not // send anything to response from JSF now. The
     * control will now go into HttpAuthenticationMechanism
     * context.responseComplete(); }
     *
     * if (status.equals(SUCCESS)) { if (roles.contains("admin")) { return
     * "/admin/admin_dashboard.jsf?faces-redirect=true"; } else if
     * (roles.contains("user")) { System.out.println("roles username " +
     * getRoles()); return "home.jsf?faces-redirect=true"; }
     *
     * } else { errorstatus = "User Name or Password may be wrong"; return
     * "/login"; }
     *
     * } catch (Exception e) { System.out.println("Username or pass wrong");
     * errorstatus = "User Name or Password may be wrong"; //
     * e.printStackTrace(); } return "login"; }
     *
     * /**
     * Creates a new instance of LoginBean
     */
    public loginCDIBean() {

        // errorstatus="";
    }

    public String logout() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

            HttpSession session = request.getSession(false); // Get the current session, don't create a new one if it doesn't exist
            if (session != null) {
                session.invalidate();
            }

            request.logout();

            System.out.println("User logged out successfully.");

            facesContext.getExternalContext().redirect(request.getContextPath() + "/login.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Navigation case is handled by the redirect
    }

}
