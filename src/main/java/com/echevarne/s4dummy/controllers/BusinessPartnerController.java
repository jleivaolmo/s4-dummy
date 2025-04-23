package com.echevarne.s4dummy.controllers;

import com.echevarne.s4dummy.model.BusinessPartner;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/sap/opu/odata/sap/API_BUSINESS_PARTNER")
public class BusinessPartnerController {
	
	// Simulación de base de datos en memoria
    private final Map<String, Map<String, Object>> dataStore = new ConcurrentHashMap<>();
    
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> handleHead(@RequestHeader(value = "x-csrf-token", required = false) String csrfFetch) {
        HttpHeaders headers = new HttpHeaders();
        
        // Simular el token CSRF solo si se solicita
        if ("Fetch".equalsIgnoreCase(csrfFetch)) {
            headers.set("x-csrf-token", "dummy-token-12345");
        }

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getServiceRoot() {
        return ResponseEntity.ok("Service is running.");
    }
    
	@PostMapping(value = "/A_BusinessPartner", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> createBusinessPartner(@RequestBody Map<String, Object> input) {
		String id = (String) input.get("BusinessPartner");
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing BusinessPartner ID");
		}
		dataStore.put(id, input);
		return Map.of("d", addMetadata(input));
	}
	
	@PatchMapping(value = "/A_BusinessPartner('{id}')", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateBusinessPartner(@PathVariable String id, @RequestBody Map<String, Object> input) {
        Map<String, Object> existing = dataStore.get(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BusinessPartner not found: " + id);
        }

        existing.putAll(input);
        return Map.of("d", addMetadata(existing));
    }

    // Helper para incluir __metadata en la respuesta
    private Map<String, Object> addMetadata(Map<String, Object> data) {
        Map<String, Object> result = new LinkedHashMap<>(data);
        result.put("__metadata", Map.of(
                "id", "https://example.com/sap/opu/odata/sap/API_BUSINESS_PARTNER/A_BusinessPartner('" + data.get("BusinessPartner") + "')",
                "uri", "https://example.com/sap/opu/odata/sap/API_BUSINESS_PARTNER/A_BusinessPartner('" + data.get("BusinessPartner") + "')",
                "type", "API_BUSINESS_PARTNER.A_BusinessPartner"
        ));
        return result;
    }

	record DummyResponse(BusinessPartner d) {
	}

	@GetMapping(value = "/$metadata", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> getMetadata(@RequestHeader(value = "x-csrf-token", required = false) String csrfToken) {
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
		
		HttpHeaders headers = new HttpHeaders();
	    if ("Fetch".equalsIgnoreCase(csrfToken)) {
	        headers.set("x-csrf-token", "fake-token-12345");
	        headers.set("Set-Cookie", "SAP_SESSIONID=FAKE123SESSION");
	    }

		return new ResponseEntity<>(metadataXml.stripLeading(), headers, HttpStatus.OK);
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
