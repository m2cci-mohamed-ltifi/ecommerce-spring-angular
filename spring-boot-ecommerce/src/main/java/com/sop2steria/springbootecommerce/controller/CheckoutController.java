package com.sop2steria.springbootecommerce.controller;


import com.sop2steria.springbootecommerce.Service.CheckoutService;
import com.sop2steria.springbootecommerce.dto.Purchase;
import com.sop2steria.springbootecommerce.dto.PurchaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    private CheckoutController(CheckoutService checkoutService){
        this.checkoutService=checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }
}
