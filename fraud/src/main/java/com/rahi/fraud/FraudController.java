package com.rahi.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster( @PathVariable("customerId") Integer customerId ) {
        boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(fraudulentCustomer);
    }

}