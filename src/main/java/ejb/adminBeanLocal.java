/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entities.MemberTable;
import entities.MembershipTable;
import entities.OrderItem;
import entities.Ordertbl;
import entities.PaymentTable;
import entities.Product;
import entities.StaffTable;
import entities.User;
import java.util.Collection;
import jakarta.ejb.Local;

/**
 *
 * @author maharaja
 */
@Local
public interface adminBeanLocal {

    //    user table
    void addUser(String name, String email, String password, String address, Integer contact, Integer groupmaster_ID);

    void updateuser(Integer id, String name, String email, String password, String address, Integer contact, Integer groupmaster_ID);

    void deleteUser(Integer id, Integer groupmaster_ID);

    User getUserById(Integer id);

    User getUserByName(String name);

    User getUserByEmail(String email);

    Collection<User> getUserBuGroup(Integer groupmaster_ID);

    Collection<User> getAllUSer();

    //    staff table
//    id	staff_name	email	hire_date	contact	salary	staff_position	
    void addStaff(String staff_name, String email, String contact, Integer salary, String staff_position,String hire_date,String staff_img);

    void updateStaff(Integer id, String staff_name, String email, String contact, Integer salary, String staff_position,String img);

    void deleteStaff(Integer id);

    Collection<StaffTable> getAllStaffs();

    StaffTable getStaffById(Integer id);

    //    products table
//    	id	category	description	img	name	price	productDate	
    void addProduct(String name, String category, String description, String img, Double price,String ProductDate);

    void updateProduct(Integer productId, String name, String category, String description, String img, Double price);

    void deleteProduct(Integer productid);

    Collection<Product> getAllProducts();
    Product getProductbydAmout(Double amount);
    Product getProductById(Integer productId);

    //    order table
//    id	deliveredAt	isDelivered	isPaid	orderDate	shipmentAddress	shippingPrice	totalPrice	user_ID	
    void addOrder(String deliveredAt, Boolean isDelivered, Boolean isPaid, String shipmentAddress, Integer shippingPrice, Integer totalPrice, Integer user_ID);

    void deleteOrder(Integer id, Integer user_ID);

    Collection<Ordertbl> getAllOrders();

    Collection<Ordertbl> getAllOrdersOfUser(Integer user_ID);

    //    order items table
    void addOrderItem(Integer orderId, Integer productId, Integer qty, Integer tprice);

    void deleteOrderItem(Integer orderItemId, Integer orderId, Integer productId);

    Collection<OrderItem> getAllItemsOfOrder(Integer orderId);

    Collection<OrderItem> getAllItemsOfProduct(Integer productId);

    Collection<OrderItem> getAllOrderItem();

    //    payment table
//    	id	amount	method	paymentDate	mambership_ID	user_ID	
    void addPayment(Double amount, String method, Integer mambership_ID, Integer user_ID);

    PaymentTable getPaymentById(Integer id);

    Collection<PaymentTable> getAllPaymentsOfUser(Integer user_ID);

    Collection<PaymentTable> getAllPaymentsOfMembership(Integer membership_ID);

    //    membership type table
//    	description	price	time_period	
    void addMembershipType(String description, Integer price, String time_period);

    void updatreMembershipType(Integer id, String description, Integer price, String time_period);

    void deleteMembershipType(Integer id);

    Collection<MembershipTable> getAllMembership();
    
    
    //    Membership_Participant
    void addmember_participants(Integer user_ID, Integer membership_ID);

    void deletemember_participants(Integer user_ID, Integer membership_ID);
    
    
    //   member table
    
    void addMember(Integer staffId,Integer userId,String gender,String address,Integer price,String name,String email,String contact);
    
    void deleteMember(Integer memberId,Integer staffId,Integer userId);
    
    Collection<MemberTable> getAllMembers();

}
