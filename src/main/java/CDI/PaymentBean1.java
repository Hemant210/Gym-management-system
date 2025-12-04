package cdi;

import ejb.PaymentEJB;
import entities.Ordertbl;
import entities.PaymentTable;
import java.util.Collection;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@Named(value = "paymentBean")
@RequestScoped
public class PaymentBean1 {

    @Inject
    private PaymentEJB paymentEJB;  // Inject the PaymentEJB for order creation

    private int totalAmount;
    private String orderId;
    private String paymentStatusMessage;
    private Collection<Ordertbl> orders;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Collection<Ordertbl> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Ordertbl> orders) {
        this.orders = orders;
    }

    public String createOrder() {
        System.out.println(totalAmount);
        // Call PaymentEJB to create the Razorpay order
        orderId = paymentEJB.createRazorpayOrder(totalAmount);
        if (orderId != null) {
            return "paymentPage.xhtml";  // Redirect to payment page
        }
        return "errorPage";  // If there was an error, show error page
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void onPaymentSuccess(String razorpayPaymentId, String razorpaySignature) {
        boolean isPaymentValid = paymentEJB.verifyPayment(razorpayPaymentId, razorpaySignature);

        if (isPaymentValid) {
            this.paymentStatusMessage = "Payment Successful!";
        } else {
            this.paymentStatusMessage = "Payment verification failed.";
        }
    }

    public String getPaymentStatusMessage() {
        return paymentStatusMessage;
    }

    public void setPaymentStatusMessage(String paymentStatusMessage) {
        this.paymentStatusMessage = paymentStatusMessage;
    }
}