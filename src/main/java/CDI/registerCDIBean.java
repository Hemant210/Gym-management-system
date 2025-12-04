    package CDI;

    import ejb.adminBeanLocal;
    import entities.User;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.HashMap;
    import java.util.Map;
    import jakarta.annotation.PostConstruct;
    import jakarta.ejb.EJB;
    import jakarta.enterprise.context.RequestScoped;
    import jakarta.faces.application.FacesMessage;
    import jakarta.faces.context.FacesContext;
    import jakarta.inject.Inject;
    import jakarta.inject.Named;
    import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
    import jakarta.ws.rs.core.GenericType;

    @Named(value = "registerCDIBean")
    @RequestScoped
    public class registerCDIBean {

        @EJB
        private adminBeanLocal abl;

        @Inject
        private Pbkdf2PasswordHash pb;

        private String name;
        private String email;
        private String password;
        private String address;
        private Integer contact;
        private Integer groupmaster_ID = 1;

        public adminBeanLocal getAbl() {
            return abl;
        }

        public void setAbl(adminBeanLocal abl) {
            this.abl = abl;
        }

        public Pbkdf2PasswordHash getPb() {
            return pb;
        }

        public void setPb(Pbkdf2PasswordHash pb) {
            this.pb = pb;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getContact() {
            return contact;
        }

        public void setContact(Integer contact) {
            this.contact = contact;
        }

        public Integer getGroupmaster_ID() {
            return groupmaster_ID;
        }

        public void setGroupmaster_ID(Integer groupmaster_ID) {
            this.groupmaster_ID = groupmaster_ID;
        }

        public Collection<User> getUsers() {
            return users;
        }

        public void setUsers(Collection<User> users) {
            this.users = users;
        }

        public GenericType<Collection<User>> getGusers() {
            return gusers;
        }

        public void setGusers(GenericType<Collection<User>> gusers) {
            this.gusers = gusers;
        }

    //    String name, String email, String password, String address, Integer contact, Integer groupmaster_ID)
        // GenericType for users (not used in this code, but keep it if you need to fetch users)
        private Collection<User> users;
        private GenericType<Collection<User>> gusers;

        // Constructor - better to use @PostConstruct for initialization
        public registerCDIBean() {
            // Initialize the user collection in the constructor
            users = new ArrayList<>();
            gusers = new GenericType<Collection<User>>() {
            };
        }


        @PostConstruct
        public void init() {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("Pbkdf2PasswordHash.Iterations", "2048");
            parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
            parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "32");
            parameters.put("Pbkdf2PasswordHash.KeySizeBytes", "32");
            pb.initialize(parameters);
        }

        // Getters and Setters
        // User Add Method
        public String addUser() {
            try {

    //            User u = abl.getUserByEmail(email);
    //
    //            if (u != null) {
    //                FacesMessage emailExistError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Already Exists", null);
    //                FacesContext.getCurrentInstance().addMessage(null, emailExistError);
    //            } else {
//                    // Hash the password before saving it
//                    System.out.println("Method triggered");
//                    String hashedPassword = pb.generate(password.toCharArray());
    //            u.addUser(name, email, hashedPassword, address, number, String.valueOf(groupmaster_ID));
                    abl.addUser(name, email, password, address, contact, groupmaster_ID);

                    // Show success message
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "User added successfully", null));

                    // Navigate to login page on successful registration
                    return "login";  // Could be changed to return a navigation string defined in faces-config.xml
    //            }
    //            return null;

            } catch (Exception e) {
                // Log the exception (this can be logged using a logging framework like SLF4J or Java Util Logging)
                e.printStackTrace();

                // Show error message
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error adding user: " + e.getMessage(), null));

                return "register";  // Stay on the registration page or navigate to an error page like "error.xhtml"
            }
        }
    }

