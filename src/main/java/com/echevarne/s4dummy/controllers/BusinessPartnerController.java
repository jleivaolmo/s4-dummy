package com.echevarne.s4dummy.controllers;

import com.echevarne.s4dummy.model.BusinessPartner;
import lombok.extern.slf4j.Slf4j;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sap/opu/odata/sap/API_BUSINESS_PARTNER")
@Slf4j
public class BusinessPartnerController {
	
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
	public Map<String, Object> createBusinessPartner(@RequestBody Map<String, Object> input,
			@RequestHeader(value = "x-csrf-token", required = false) String csrfToken) {
		log.info("Se llama a createBusinessPartner con parametros: " + input);
		Map<String, Object> response = null;
		try {
			response = Map.of("d", addMetadata(input));
		} catch (Exception e) {
			log.error("Error al crear BusinessPartner:" + e, e);
		}
		return response;
	}
	
	@PatchMapping(value = "/A_BusinessPartner('{id}')", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateBusinessPartner(@PathVariable String id, @RequestBody Map<String, Object> input) {
        return Map.of("d", addMetadata(input));
    }

    // Helper para incluir __metadata en la respuesta
    private Map<String, Object> addMetadata(Map<String, Object> data) {
        Map<String, Object> result = new LinkedHashMap<>(data);
        result.put("__metadata", Map.of(
                "id", "https://example.com/sap/opu/odata/sap/API_BUSINESS_PARTNER/A_BusinessPartner('" + data.get("Customer") + "')",
                "uri", "https://example.com/sap/opu/odata/sap/API_BUSINESS_PARTNER/A_BusinessPartner('" + data.get("Customer") + "')",
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
				        <Property Name="Customer" Type="Edm.String"/>
				        <Property Name="BusinessPartnerCategory" Type="Edm.String"/>
				        <Property Name="FormOfAddress" Type="Edm.String"/>
				        <Property Name="Language" Type="Edm.String"/>
				        <Property Name="SearchTerm1" Type="Edm.String"/>
				        <Property Name="OrganizationBPName1" Type="Edm.String"/>
				        <Property Name="OrganizationBPName4" Type="Edm.String"/>
				        <Property Name="CorrespondenceLanguage" Type="Edm.String"/>
				        <Property Name="BusinessPartnerGrouping" Type="Edm.String"/>
				
				        <NavigationProperty Name="to_BusinessPartnerAddress" Relationship="API_BUSINESS_PARTNER.A_BusinessPartner_to_Address" ToRole="Address" FromRole="BusinessPartner"/>
				        <NavigationProperty Name="to_BusinessPartnerRole" Relationship="API_BUSINESS_PARTNER.A_BusinessPartner_to_Role" ToRole="Role" FromRole="BusinessPartner"/>
				        <NavigationProperty Name="to_Customer" Relationship="API_BUSINESS_PARTNER.A_BusinessPartner_to_Customer" ToRole="Customer" FromRole="BusinessPartner"/>
				      </EntityType>
				
				      <!-- Address -->
				      <EntityType Name="A_BusinessPartnerAddress">
				        <Key>
				          <PropertyRef Name="BusinessPartner"/>
				          <PropertyRef Name="AddressID"/>
				        </Key>
				        <Property Name="BusinessPartner" Type="Edm.String"/>
				        <Property Name="AddressID" Type="Edm.String"/>
				        <Property Name="Country" Type="Edm.String"/>
				        <Property Name="Language" Type="Edm.String"/>
				      </EntityType>
				
				      <!-- Role -->
				      <EntityType Name="A_BusinessPartnerRole">
				        <Key>
				          <PropertyRef Name="BusinessPartner"/>
				          <PropertyRef Name="BusinessPartnerRole"/>
				        </Key>
				        <Property Name="BusinessPartner" Type="Edm.String"/>
				        <Property Name="BusinessPartnerRole" Type="Edm.String"/>
				        <Property Name="ValidFrom" Type="Edm.DateTime"/>
				        <Property Name="ValidTo" Type="Edm.DateTime"/>
				      </EntityType>
				
				      <!-- Customer -->
				      <EntityType Name="A_Customer">
				        <Key>
				          <PropertyRef Name="Customer"/>
				        </Key>
				        <Property Name="Customer" Type="Edm.String"/>
				      </EntityType>
				
				      <!-- Relaciones -->
				      <Association Name="A_BusinessPartner_to_Address">
				        <End Type="API_BUSINESS_PARTNER.A_BusinessPartner" Role="BusinessPartner" Multiplicity="1"/>
				        <End Type="API_BUSINESS_PARTNER.A_BusinessPartnerAddress" Role="Address" Multiplicity="*"/>
				      </Association>
				
				      <Association Name="A_BusinessPartner_to_Role">
				        <End Type="API_BUSINESS_PARTNER.A_BusinessPartner" Role="BusinessPartner" Multiplicity="1"/>
				        <End Type="API_BUSINESS_PARTNER.A_BusinessPartnerRole" Role="Role" Multiplicity="*"/>
				      </Association>
				
				      <Association Name="A_BusinessPartner_to_Customer">
				        <End Type="API_BUSINESS_PARTNER.A_BusinessPartner" Role="BusinessPartner" Multiplicity="1"/>
				        <End Type="API_BUSINESS_PARTNER.A_Customer" Role="Customer" Multiplicity="1"/>
				      </Association>
				
				      <!-- Conjunto de entidades -->
				      <EntityContainer Name="container" m:IsDefaultEntityContainer="true" xmlns:m="http://schemas.microsoft.com/ado/2007/08/dataservices/metadata">
				        <EntitySet Name="A_BusinessPartner" EntityType="API_BUSINESS_PARTNER.A_BusinessPartner"/>
				        <EntitySet Name="A_BusinessPartnerAddress" EntityType="API_BUSINESS_PARTNER.A_BusinessPartnerAddress"/>
				        <EntitySet Name="A_BusinessPartnerRole" EntityType="API_BUSINESS_PARTNER.A_BusinessPartnerRole"/>
				        <EntitySet Name="A_Customer" EntityType="API_BUSINESS_PARTNER.A_Customer"/>
				
				        <!-- Asignación de navegación -->
				        <AssociationSet Name="A_BusinessPartner_to_Address" Association="API_BUSINESS_PARTNER.A_BusinessPartner_to_Address">
				          <End Role="BusinessPartner" EntitySet="A_BusinessPartner"/>
				          <End Role="Address" EntitySet="A_BusinessPartnerAddress"/>
				        </AssociationSet>
				
				        <AssociationSet Name="A_BusinessPartner_to_Role" Association="API_BUSINESS_PARTNER.A_BusinessPartner_to_Role">
				          <End Role="BusinessPartner" EntitySet="A_BusinessPartner"/>
				          <End Role="Role" EntitySet="A_BusinessPartnerRole"/>
				        </AssociationSet>
				
				        <AssociationSet Name="A_BusinessPartner_to_Customer" Association="API_BUSINESS_PARTNER.A_BusinessPartner_to_Customer">
				          <End Role="BusinessPartner" EntitySet="A_BusinessPartner"/>
				          <End Role="Customer" EntitySet="A_Customer"/>
				        </AssociationSet>
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
