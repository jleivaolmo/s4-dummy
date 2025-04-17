package com.echevarne.s4dummy.controllers;

import com.echevarne.s4dummy.model.BusinessPartner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sap/opu/odata/sap/API_BUSINESS_PARTNER")
public class BusinessPartnerController {

    @PostMapping("/A_BusinessPartner")
    public ResponseEntity<Object> createBusinessPartner(@RequestBody BusinessPartner bp) {
        System.out.println("Received BP: " + bp.getBusinessPartner());

        // Simula respuesta tipo OData
        return ResponseEntity
            .status(201)
            .body(new DummyResponse(bp));
    }

    record DummyResponse(BusinessPartner d) {}
}
