/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDI;

import ejb.adminBeanLocal;
import entities.StaffTable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 *
 * @author Ankit Rathod
 */
@Named(value = "staffCDIBean")
@ViewScoped
public class staffCDIBean implements Serializable{
    
    @EJB
    adminBeanLocal abl;
    
    StaffTable s;
    
    private Integer staffId;
    
    private String name;
    private String email;
    private String hire_date;
    private String contact;
    private Integer salary;
    private String satff_pos;
    private Part img;
    
    private Collection<StaffTable> staffs;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        
        String staffIdString = context.getExternalContext().getRequestParameterMap().get("staffId");
        if (staffIdString != null) {
            try {
                this.staffId = Integer.valueOf(staffIdString);
                System.out.println("Parsed staff ID from request: " + this.staffId);
                loadStaffDetails(staffId);
//                System.out.println("Product cat:"+this.p.getId());
            } catch (NumberFormatException e) {
                System.out.println("Invalid car ID format: " + staffIdString);
                e.printStackTrace();
            }
        }
    }
    
    private void loadStaffDetails(Integer sid) {
        if (this.staffId == null) {
            System.out.println("Staff ID is null. Cannot load details.");
            return;
        }
        this.s = abl.getStaffById(sid);
    }
    
    public Integer getStaffId() {
        return staffId;
    }
    
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    public adminBeanLocal getAbl() {
        return abl;
    }
    
    public void setAbl(adminBeanLocal abl) {
        this.abl = abl;
    }
    
    public StaffTable getS() {
        return s;
    }
    
    public void setS(StaffTable s) {
        this.s = s;
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
    
    public String getHire_date() {
        return hire_date;
    }
    
    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public Integer getSalary() {
        return salary;
    }
    
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
    public String getSatff_pos() {
        return satff_pos;
    }
    
    public void setSatff_pos(String satff_pos) {
        this.satff_pos = satff_pos;
    }
    
    public Part getImg() {
        return img;
    }
    
    public void setImg(Part img) {
        this.img = img;
    }
    
    public Collection<StaffTable> getStaffs() {
        return staffs;
    }
    
    public void setStaffs(Collection<StaffTable> staffs) {
        this.staffs = staffs;
    }
    
    public String addStaff() {
        String fileName = null;
        
        if (img != null) {
            try {
                // Get the file name from the part
                fileName = getFileName(img);

                // Define the exact upload path (your mentioned path)
                String uploadPath = "C:/Users/Ankit Rathod/OneDrive/Documents/NetBeansProjects/gym_pro_new/src/main/webapp/admin/Upload/";

                // Ensure the upload directory exists
                File directory = new File(uploadPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Save the uploaded file to the specified path
                File uploadedFile = new File(uploadPath + fileName);
                try (InputStream in = img.getInputStream(); FileOutputStream out = new FileOutputStream(uploadedFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }

                // Initialize the Product object if it's null
                if (s == null) {
                    s = new StaffTable();
                }

                // Set the relative image path in the Product object
                s.setStaffImg("admin/Upload/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Save product details using EJB
        abl.addStaff(name, email, contact, salary, satff_pos, hire_date, fileName);

        // Return to product list page
        return "admin_staff_show.jsf?faces-redirect=true";
    }
    
    public String updateStaff() {
        String filename = null;

        // Handle file upload
        if (img != null) {
            try {
                filename = getFileName(img);
                String uploadPath = "C:/Users/Ankit Rathod/OneDrive/Documents/NetBeansProjects/gym_pro_new/src/main/webapp/admin/Upload/";
                File directory = new File(uploadPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                
                File uploadedFile = new File(uploadPath + filename);
                try (InputStream in = img.getInputStream(); FileOutputStream out = new FileOutputStream(uploadedFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }

                // Set the new image path
                s.setStaffImg("/admin/Upload/" + filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Keep the existing image if no new file is uploaded
            StaffTable existingst = abl.getStaffById(this.staffId); // Ensure you have a method to fetch a brand by ID
            if (existingst != null) {
                filename = existingst.getStaffImg();
            }
        }

        // Call the EJB method to update the brand
        System.out.println("Salary:"+s.getSalary());
        System.out.println("Name:"+s.getStaffName());
        System.out.println("Email:"+s.getEmail());
        System.out.println("Contact:"+s.getContact());
        System.out.println("Position:"+s.getStaffPosition());
        System.out.println("Image:"+filename);
        abl.updateStaff(this.staffId, s.getStaffName(), s.getEmail(), s.getContact(), s.getSalary(), s.getStaffPosition(), filename);

        // Redirect back to the brand management page
        return "admin_staff_show.jsf?faces-redirect=true";
        
    }
    
    public Collection<StaffTable> getstaff() {
        return abl.getAllStaffs();
    }
    
    public void deleteStaff(Integer sid) {
        System.out.println("Method triggered");
        System.out.println(sid);
        abl.deleteStaff(sid);
    }
    
    private String getFileName(Part img) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        String contentDisposition = img.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        
        return null;
    }
    
    public Collection<StaffTable> getAllStaffs() {
        return abl.getAllStaffs();
    }
//    
//    public Collection<StaffTable> getAllStaffs() {
//        List<StaffTable> allStaffs = new ArrayList<>(abl.getAllStaffs());
//        return allStaffs.stream().limit(4).collect(Collectors.toList());
//    }

}
