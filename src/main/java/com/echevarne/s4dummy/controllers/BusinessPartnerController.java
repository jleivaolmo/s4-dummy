package com.echevarne.s4dummy.controllers;

import com.echevarne.s4dummy.model.BusinessPartner;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sap/opu/odata/sap/API_BUSINESS_PARTNER")
public class BusinessPartnerController {

	@PostMapping("/A_BusinessPartner")
	public ResponseEntity<Object> createBusinessPartner(@RequestBody BusinessPartner bp) {
		System.out.println("Received BP: " + bp.getBusinessPartner());

		// Simula respuesta tipo OData
		return ResponseEntity.status(201).body(new DummyResponse(bp));
	}

	record DummyResponse(BusinessPartner d) {
	}

	@GetMapping("/$metadata")
	public ResponseEntity<String> getMetadata() {
		String metadataXml = """
				         <?xml version="1.0" encoding="utf-8"?>
				<edmx:Edmx xmlns:edmx="http://schemas.microsoft.com/ado/2007/06/edmx" Version="1.0">
				  <edmx:DataServices m:DataServiceVersion="2.0"
				      xmlns:m="http://schemas.microsoft.com/ado/2007/08/dataservices/metadata">
				    <Schema Namespace="API_BUSINESS_PARTNER" xmlns="http://schemas.microsoft.com/ado/2008/09/edm">
				      <EntityType Name="A_BusinessPartner">
				        <Key>
				          <PropertyRef Name="BusinessPartner"/>
				        </Key>
				        <Property Name="BusinessPartner" Type="Edm.String" Nullable="false"/>
				        <Property Name="FirstName" Type="Edm.String"/>
				        <Property Name="LastName" Type="Edm.String"/>
				        <Property Name="BusinessPartnerFullName" Type="Edm.String"/>
				        <Property Name="BusinessPartnerCategory" Type="Edm.String"/>
				        <Property Name="Gender" Type="Edm.String"/>
				        <Property Name="Language" Type="Edm.String"/>
				      </EntityType>
				      <EntityContainer Name="container" m:IsDefaultEntityContainer="true">
				        <EntitySet Name="A_BusinessPartner" EntityType="API_BUSINESS_PARTNER.A_BusinessPartner"/>
				      </EntityContainer>
				    </Schema>
				  </edmx:DataServices>
				</edmx:Edmx>
				         """;

		return ResponseEntity.ok().header("Content-Type", "application/xml").body(metadataXml.stripLeading());
	}

	@GetMapping(value = "/A_BusinessPartner", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getBusinessPartner(@RequestParam Map<String, String> params) {

		Object[] element = new Object[] { Map.of("__metadata",
				Map.of("id", "https://example.com/sap/opu/odata/sap/API_BUSINESS_PARTNER/A_BusinessPartner('10000001')", "uri",
						"https://example.com/sap/opu/odata/sap/API_BUSINESS_PARTNER/A_BusinessPartner('10000001')", "type",
						"API_BUSINESS_PARTNER.A_BusinessPartner"),
				"BusinessPartner", "10000001", "FirstName", "Juan", "LastName", "Pérez", "Customer", "", "to_BuPaIdentification",
				Map.of("results",
						new Object[] { Map.of("__metadata", Map.of("type", "API_BUSINESS_PARTNER.BuPaIdentification"), "IdentificationType", "XX1",
								"IdentificationNumber", "1234567890") }),
				"to_BusinessPartnerAddress",
				Map.of("results",
						new Object[] { Map.of("__metadata", Map.of("type", "API_BUSINESS_PARTNER.BusinessPartnerAddress"), "CityName", "Buenos Aires",
								"StreetName", "Av. Siempre Viva", "PostalCode", "1000", "to_EmailAddress",
								Map.of("results", new Object[] { Map.of("EmailAddress", "juan.perez@example.com") }), "to_MobilePhoneNumber",
								Map.of("results", new Object[] { Map.of("PhoneNumber", "+5491112345678") }), "to_PhoneNumber",
								Map.of("results", new Object[] { Map.of("PhoneNumber", "01112345678") })) }),
				"to_BusinessPartnerRole", Map.of("results", new Object[] { Map.of("BusinessPartnerRole", "FLCU01") }), "to_BusinessPartnerTax",
				Map.of("results", new Object[] { Map.of("BPTaxType", "VAT", "BPTaxNumber", "20304050607") }), "to_Customer",
				Map.of("results", new Object[] { Map.of("Customer", "10000001", "CustomerName", "Juan Pérez") })) };
		
		Map<String, Object> response = Map.of("d", Map.of("results", element));
		return response;
	}
}
