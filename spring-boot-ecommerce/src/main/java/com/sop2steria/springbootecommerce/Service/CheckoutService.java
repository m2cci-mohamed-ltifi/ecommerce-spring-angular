package com.sop2steria.springbootecommerce.Service;

import com.sop2steria.springbootecommerce.dto.PaymentInfo;
import com.sop2steria.springbootecommerce.dto.Purchase;
import com.sop2steria.springbootecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
