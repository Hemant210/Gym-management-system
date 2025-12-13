/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDI;

import ejb.adminBeanLocal;
import entities.Ordertbl;
import entities.Product;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
//import javax.enterprise.inject.spi.CDI;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 *
 * @author Ankit Rathod
 */
@Named(value = "productCDIBean")
@ViewScoped
public class productCDIBean implements Serializable {

    @EJB
    adminBeanLocal abl;

    @Inject
    private mail emailSender;

    @Inject
    loginCDIBean lbean;
    Product p;

    private String category;
    private String des;
    private Part img;
    private String name;
    private Double price;
    private String productDate;
    private Integer productId;
    private String deliverAt;
    private boolean isDeliverd = true;
    private boolean isPaid = true;
    private String shippingAddress;
    private Integer shippingPrice = 500;
    private Double totalPrice;
    private Integer userId;
    private String userEmail;
    Collection<Product> products;
    private Collection<Ordertbl> ord;

    @PostConstruct
    public void init() {
        this.userId = lbean.getU().getId();
        this.userEmail = lbean.getU().getEmail();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession();

        String productIdString = context.getExternalContext().getRequestParameterMap().get("productId");
        if (productIdString != null) {
            try {
                this.productId = Integer.valueOf(productIdString);
                System.out.println("Parsed car ID from request: " + this.productId);
                loadProductDetails(productId);
                System.out.println("Product cat:" + this.p.getId());
            } catch (NumberFormatException e) {
                System.out.println("Invalid car ID format: " + productIdString);
                e.printStackTrace();
            }
            ord = abl.getAllOrders();
        }
        String getamout = context.getExternalContext().getRequestParameterMap().get("amout");
        if (getamout != null) {
            this.totalPrice = Double.valueOf(getamout);

            loadOrderItemDetails(totalPrice);
        }
    }

    private void loadOrderItemDetails(Double id) {
        this.p = abl.getProductbydAmout(id);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(String deliverAt) {
        this.deliverAt = deliverAt;
    }

    public boolean isIsDeliverd() {
        return isDeliverd;
    }

    public void setIsDeliverd(boolean isDeliverd) {
        this.isDeliverd = isDeliverd;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Integer getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Integer shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    private void loadProductDetails(Integer productId) {
        if (this.productId == null) {
            System.out.println("Product ID is null. Cannot load details.");
            return;
        }
        this.p = abl.getProductById(productId);

    }

    public Collection<Product> getAllProduct() {
        return abl.getAllProducts();
    }

    public adminBeanLocal getAbl() {
        return abl; 
    }

    public void setAbl(adminBeanLocal abl) {
        this.abl = abl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Part getImg() {
        return img;
    }

    public void setImg(Part img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = abl.getAllProducts();
    }

    public String addProduct() {
        String fileName = null;

        if (img != null) {
            try {
                fileName = getFileName(img);
                String uploadPath = "C:/Users/Ankit Rathod/OneDrive/Documents/NetBeansProjects/gym_pro_new/src/main/webapp/admin/Upload/";
                File directory = new File(uploadPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File uploadedFile = new File(uploadPath + fileName);
                try (InputStream in = img.getInputStream(); FileOutputStream out = new FileOutputStream(uploadedFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
                p.setImg("/admin/Upload/" + fileName);
                System.out.println("file name" + fileName);
//                System.out.println("set Image"+p.setImg(img));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        abl.addProduct(name, category, des, fileName, price, productDate);

        return "admin_product_show.jsf?faces-redirect=true";
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

    public String addorder() {
        Date orderDate = new Date();
//        this.totalPrice=this.price;
        abl.addOrder(deliverAt, isDeliverd, isPaid, shippingAddress, this.shippingPrice, totalPrice.intValue(), userId);

        emailSender.sendEmail(userEmail, orderDate);
        return "home.jsf?faces-redirect=true";
    }
//     

    public Collection<Ordertbl> getOrd() {
        return ord;
    }

    public void setOrd(Collection<Ordertbl> ord) {
        this.ord = ord;
    }

    public Collection<Product> getproduct() {
        Collection<Product> products = abl.getAllProducts();
        System.out.println("product from " + products);
        return products;
    }

    public void deleteProduct(Integer pid) {
        System.out.println("Method trige");
        System.out.println(pid);
        abl.deleteProduct(pid);
    }

    public String updateProduct() {
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
                p.setImg("/admin/Upload/" + filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Keep the existing image if no new file is uploaded
            Product existingP = abl.getProductById(this.productId); // Ensure you have a method to fetch a brand by ID
            if (existingP != null) {
                filename = existingP.getImg();
            }
        }

        // Call the EJB method to update the brand
        abl.updateProduct(this.productId, p.getName(), p.getCategory(), p.getDescription(), filename, p.getPrice());

        // Redirect back to the brand management page
        return "admin_product_show.jsf?faces-redirect=true";

    }

}
