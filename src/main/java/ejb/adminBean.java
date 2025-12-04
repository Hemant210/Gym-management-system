/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entities.Groupmaster;
import entities.MemberTable;
import entities.MembershipTable;
import entities.OrderItem;
import entities.Ordertbl;
import entities.PaymentTable;
import entities.Product;
import entities.StaffTable;
import entities.User;
import java.util.Collection;
import java.util.Date;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author maharaja
 */
@Stateless
public class adminBean implements adminBeanLocal {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    @Override
    public void addUser(String name, String email, String password, String address, Integer contact, Integer groupmaster_ID) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Groupmaster gid = em.find(Groupmaster.class, groupmaster_ID);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setContact(contact);
        user.setGroupmasterID(gid);

        Collection<User> groupByUser = gid.getUserCollection();
        groupByUser.add(user);

        em.merge(gid);

        em.persist(user);
    }

    @Override
    public void updateuser(Integer id, String name, String email, String password, String address, Integer contact, Integer groupmaster_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User user = em.find(User.class, id);

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setContact(contact);

        Groupmaster gid = em.find(Groupmaster.class, groupmaster_ID);
        user.setGroupmasterID(gid);

        em.merge(user);
    }

    @Override
    public void deleteUser(Integer id, Integer groupmaster_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Groupmaster gid = em.find(Groupmaster.class, groupmaster_ID);
        User user = em.find(User.class, id);

        Collection<User> groupByUser = gid.getUserCollection();
        groupByUser.remove(user);

        em.merge(gid);

        em.remove(user);
    }

    @Override
    public User getUserById(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("User.findByName", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public Collection<User> getUserBuGroup(Integer groupmaster_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Groupmaster gid = em.find(Groupmaster.class, groupmaster_ID);
        return gid.getUserCollection();
    }

    @Override
    public Collection<User> getAllUSer() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void addStaff(String staff_name, String email, String contact, Integer salary, String staff_position, String hire_date, String staff_img) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        StaffTable staff = new StaffTable();
        staff.setStaffName(staff_name);
        staff.setEmail(email);
        staff.setHireDate(hire_date);
        staff.setContact(contact);
        staff.setSalary(salary);
        staff.setStaffPosition(staff_position);
        staff.setStaffImg(staff_img);

        em.persist(staff);
    }

    @Override
    public void updateStaff(Integer id, String staff_name, String email, String contact, Integer salary, String staff_position,String img) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        StaffTable s = em.find(StaffTable.class, id);
        s.setStaffName(staff_name);
        s.setEmail(email);
        s.setContact(contact);
        s.setSalary(salary);
        s.setStaffPosition(staff_position);
        s.setStaffImg(img);

        em.merge(s);
    }

    @Override
    public void deleteStaff(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        StaffTable s = em.find(StaffTable.class, id);
        em.remove(s);

    }

    @Override
    public Collection<StaffTable> getAllStaffs() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("StaffTable.findAll", StaffTable.class)
                .getResultList();
    }

    @Override
    public StaffTable getStaffById(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(StaffTable.class, id);
    }

    @Override
    public void addProduct(String name, String category, String description, String img, Double price, String ProductDate) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setImg(img); // Assuming 'img' is the image URL or path
        product.setPrice(price);
        product.setProductDate(ProductDate);

        // Persist the product entity to the database
        em.persist(product);
    }

    @Override
    public void updateProduct(Integer productId, String name, String category, String description, String img, Double price) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Product product = em.find(Product.class, productId);

        // Update the product's details
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice(price);

        // Merge the updated product entity
        em.merge(product);
    }

    @Override
    public void deleteProduct(Integer productid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Product product = em.find(Product.class, productid);

        // Remove the product entity from the database
        em.remove(product);
    }
    @Override
    public Product getProductbydAmout(Double amount){
      return (Product) em.createNamedQuery("Product.findByPrice").setParameter("price", amount).getResultList().iterator().next();
    }
    
    @Override
    public Collection<Product> getAllProducts() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }

    @Override
    public Product getProductById(Integer productId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Product.class, productId);
    }

    @Override
    public void addOrder(String deliveredAt, Boolean isDelivered, Boolean isPaid, String shipmentAddress, Integer shippingPrice, Integer totalPrice, Integer user_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User user = em.find(User.class, user_ID);

        // Create a new Ordertbl entity
        Ordertbl order = new Ordertbl();
        order.setDeliveredAt(deliveredAt);  // Set the delivery date
        order.setIsDelivered(isDelivered);  // Set if the order is delivered
        order.setIsPaid(isPaid);  // Set if the order is paid
        order.setOrderDate(new Date());
        order.setShipmentAddress(shipmentAddress);  // Set the shipment address
        order.setShippingPrice(shippingPrice);  // Set the shipping price
        order.setTotalPrice(totalPrice);  // Set the total price
        order.setUserID(user);  // Associate the order with the user

        // Persist the order entity to the database
        em.persist(order);

        // Update collections for user (optional if you want to maintain the collections manually)
        user.getOrdertblCollection().add(order);

        // Merge the updated user collection (if manually managing them)
        em.merge(user);
    }

    @Override
    public void deleteOrder(Integer id, Integer user_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User user = em.find(User.class, user_ID);

        // Find the Ordertbl entity by order ID
        Ordertbl order = em.find(Ordertbl.class, id);

        // Ensure that the order belongs to the user (optional validation)
        // Remove the order from the user's collection (optional)
        user.getOrdertblCollection().remove(order);

        // Merge the updated user collection
        em.merge(user);

        // Remove the order from the database
        em.remove(order);
    }

    @Override
    public Collection<Ordertbl> getAllOrders() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Ordertbl.findAll", Ordertbl.class)
                .getResultList();
    }

    @Override
    public Collection<Ordertbl> getAllOrdersOfUser(Integer user_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User user = em.find(User.class, user_ID);
        return user.getOrdertblCollection();

    }

    @Override
    public void addOrderItem(Integer orderId, Integer productId, Integer qty, Integer tprice) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Ordertbl order = em.find(Ordertbl.class, orderId);

        // Find the Product entity by its ID
        Product product = em.find(Product.class, productId);

        // Create a new OrderItem entity
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderID(order);  // Associate the order
        orderItem.setProductID(product);  // Associate the product
        orderItem.setQuantity(qty);  // Set the quantity
        orderItem.setTotalPrice(tprice);  // Set the total price

        // Persist the order item entity to the database
        em.persist(orderItem);

        // Optionally, add the OrderItem to the order's collection and merge the updated order entity
        order.getOrderItemCollection().add(orderItem);
        em.merge(order);

        // Optionally, add the OrderItem to the product's collection and merge the updated product entity
        product.getOrderItemCollection().add(orderItem);
        em.merge(product);
    }

    @Override
    public void deleteOrderItem(Integer orderItemId, Integer orderId, Integer productId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        OrderItem orderItem = em.find(OrderItem.class, orderItemId);

        Ordertbl order = em.find(Ordertbl.class, orderId);
        Product product = em.find(Product.class, productId);

        order.getOrderItemCollection().remove(orderItem);

        product.getOrderItemCollection().remove(orderItem);

        em.merge(order);
        em.merge(product);

        em.remove(orderItem);

    }

    @Override
    public Collection<OrderItem> getAllItemsOfOrder(Integer orderId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Ordertbl order = em.find(Ordertbl.class, orderId);

        // Return all items of the specified order from its collection
        return order.getOrderItemCollection();
    }

    @Override
    public Collection<OrderItem> getAllItemsOfProduct(Integer productId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Product product = em.find(Product.class, productId);

        // Return all order items related to the specified product
        return product.getOrderItemCollection();
    }

    @Override
    public Collection<OrderItem> getAllOrderItem() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("OrderItem.findAll", OrderItem.class)
                .getResultList();
    }

    @Override
    public void addMembershipType(String description, Integer price, String time_period) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        MembershipTable m = new MembershipTable();
        m.setDescription(description);
        m.setPrice(price);
        m.setTimePeriod(time_period);

        em.persist(m);
    }

    @Override
    public void updatreMembershipType(Integer id, String description, Integer price, String time_period) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        MembershipTable membership = em.find(MembershipTable.class, id);

        // Update the details of the membership
        membership.setTimePeriod(time_period);
        membership.setDescription(description);
        membership.setPrice(price);

        // Merge the updated membership entity
        em.merge(membership);
    }

    @Override
    public void deleteMembershipType(Integer membershipId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        // Find the MembershipTable entity by membershipId
        MembershipTable membership = em.find(MembershipTable.class, membershipId);

        // Remove the membership entity from the database
        em.remove(membership);
    }

    @Override
    public Collection<MembershipTable> getAllMembership() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("MembershipTable.findAll", MembershipTable.class)
                .getResultList();
    }

    @Override
    public void addPayment(Double amount, String method, Integer mambership_ID, Integer user_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User u = em.find(User.class, user_ID);

        MembershipTable membership = em.find(MembershipTable.class, mambership_ID);

        PaymentTable payment = new PaymentTable();
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setPaymentDate(new Date());  // Set the current date for payment
        payment.setUserID(u);  // Associate the payment with the member
        payment.setMambershipID(membership);  // Associate the payment with the membership

        // Persist the payment entity to the database
        em.persist(payment);

        // Optionally, update the collections for member and membership (if maintaining them manually)
        u.getPaymentTableCollection().add(payment);
        membership.getPaymentTableCollection().add(payment);

        // Merge the updated member and membership entities
        em.merge(u);
        em.merge(membership);
    }

    @Override
    public PaymentTable getPaymentById(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(PaymentTable.class, id);
    }

    @Override
    public Collection<PaymentTable> getAllPaymentsOfUser(Integer user_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User u = em.find(User.class, user_ID);

        // Return all payments associated with the member
        return u.getPaymentTableCollection();
    }

    @Override
    public Collection<PaymentTable> getAllPaymentsOfMembership(Integer membership_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        MembershipTable membership = em.find(MembershipTable.class, membership_ID);

        // Return all payments associated with the membership
        return membership.getPaymentTableCollection();
    }

    @Override
    public void addmember_participants(Integer user_ID, Integer membership_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User u = em.find(User.class, user_ID);

        // Find the MembershipTable entity by membership_ID
        MembershipTable membership = em.find(MembershipTable.class, membership_ID);

        u.getMembershipTableCollection().add(membership);
        membership.getUserCollection().add(u);

        em.merge(u);

        em.merge(membership);

    }

    @Override
    public void deletemember_participants(Integer user_ID, Integer membership_ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User u = em.find(User.class, user_ID);

        // Find the MembershipTable entity by membership_ID
        MembershipTable membership = em.find(MembershipTable.class, membership_ID);

        u.getMembershipTableCollection().remove(membership);

        membership.getUserCollection().remove(u);

        // Merge both entities to persist the changes
        em.merge(u);
        em.merge(membership);

    }

    @Override
    public void addMember(Integer staffId,Integer userId,String gender,String address,Integer price,String name,String email,String contact) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        User u = em.find(User.class, userId);
        StaffTable s = em.find(StaffTable.class, staffId);

        MemberTable mt = new MemberTable();
        mt.setStaffId(s);
        mt.setUserId(u);
        mt.setGender(gender);
        mt.setAddress(address);
        mt.setPrice(price);
        mt.setName(name);
        mt.setEmail(email);
        mt.setContact(contact);
        em.persist(mt);

        u.getMemberTableCollection().add(mt);
        s.getMemberTableCollection().add(mt);

        em.merge(u);
        em.merge(s);
    }

    @Override
    public void deleteMember(Integer memberId, Integer staffId, Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        MemberTable mt = em.find(MemberTable.class, memberId);

        StaffTable s = em.find(StaffTable.class, staffId);
        User u = em.find(User.class, userId);

        s.getMemberTableCollection().remove(mt);
        u.getMemberTableCollection().remove(mt);

        em.merge(u);
        em.merge(s);

        em.remove(mt);
    }

    @Override
    public Collection<MemberTable> getAllMembers() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("MemberTable.findAll").getResultList();
    }
}
