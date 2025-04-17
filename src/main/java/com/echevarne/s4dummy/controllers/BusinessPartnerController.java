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
    
    @GetMapping("/$metadata")
    public ResponseEntity<String> getMetadata() {
        String metadataXml = """
            <?xml version="1.0" encoding="utf-8"?>
            <edmx:Edmx xmlns:edmx="http://schemas.microsoft.com/ado/2008/09/edm" Version="4.0">
              <edmx:DataServices>
                <Schema xmlns="http://docs.oasis-open.org/odata/ns/edm" Namespace="API_BUSINESS_PARTNER">
                  <EntityType Name="A_BusinessPartner">
                    <Key>
                      <PropertyRef Name="BusinessPartner"/>
                    </Key>
                    <Property Name="BusinessPartner" Type="Edm.String"/>
                    <Property Name="FirstName" Type="Edm.String"/>
                    <Property Name="LastName" Type="Edm.String"/>
                    <Property Name="BusinessPartnerFullName" Type="Edm.String"/>
                    <Property Name="BusinessPartnerCategory" Type="Edm.String"/>
                    <Property Name="Gender" Type="Edm.String"/>
                    <Property Name="Language" Type="Edm.String"/>
                  </EntityType>
                  <EntityContainer Name="container">
                    <EntitySet Name="A_BusinessPartner" EntityType="API_BUSINESS_PARTNER.A_BusinessPartner"/>
                  </EntityContainer>
                </Schema>
              </edmx:DataServices>
            </edmx:Edmx>
            """;

        return ResponseEntity.ok()
            .header("Content-Type", "application/xml")
            .body(metadataXml);
    }
}
