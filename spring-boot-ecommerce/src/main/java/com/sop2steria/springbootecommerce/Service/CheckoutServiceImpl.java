package com.sop2steria.springbootecommerce.Service;

import com.sop2steria.springbootecommerce.dao.CustomerRepository;
import com.sop2steria.springbootecommerce.dto.PaymentInfo;
import com.sop2steria.springbootecommerce.dto.Purchase;
import com.sop2steria.springbootecommerce.dto.PurchaseResponse;
import com.sop2steria.springbootecommerce.entity.Customer;
import com.sop2steria.springbootecommerce.entity.Order;
import com.sop2steria.springbootecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               @Value("${stripe.key.secret") String secretKey){
        this.customerRepository=customerRepository;

        //initialize Stripe Api with secret key
        Stripe.apiKey = secretKey;
    }


    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve the order info from dto
        Order order=purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item->order.add(item));

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();

        String theEmail=customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if(customerFromDB!= null){
            customer = customerFromDB;
        }

        customer.add(order);
        order.setCustomer(customer);

        //save to DB
        customerRepository.save(customer);

        //return a response

        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String,Object> params = new HashMap<>();
        params.put("ammount", paymentInfo.getAmmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        return null;
    }
}
