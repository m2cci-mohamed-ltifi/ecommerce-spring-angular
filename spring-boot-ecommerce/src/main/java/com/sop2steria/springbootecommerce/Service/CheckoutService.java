package com.sop2steria.springbootecommerce.Service;

import com.sop2steria.springbootecommerce.dto.Purchase;
import com.sop2steria.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
