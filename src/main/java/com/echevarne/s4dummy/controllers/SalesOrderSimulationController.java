package com.echevarne.s4dummy.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sap/opu/odata/sap/API_SALES_ORDER_SIMULATION_SRV")
@Slf4j
public class SalesOrderSimulationController extends AbstractController {

	@GetMapping(value = "/$metadata", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> getMetadata(@RequestHeader(value = "x-csrf-token", required = false) String csrfToken) {
		String metadataXml_1 = """
				         <?xml version="1.0" encoding="utf-8"?>
				<edmx:Edmx xmlns:edmx="http://schemas.microsoft.com/ado/2007/06/edmx" Version="1.0">
				  <edmx:DataServices m:DataServiceVersion="2.0"
				      xmlns:m="http://schemas.microsoft.com/ado/2007/08/dataservices/metadata">
				    <Schema Namespace="API_SALES_ORDER_SIMULATION_SRV" xmlns="http://schemas.microsoft.com/ado/2008/09/edm">
				        <EntityType Name="A_SalesOrderCreditSimulationType">
				            <Key>
				                <PropertyRef Name="SalesOrder"/>
				            </Key>
				            <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				            <Property Name="TotalCreditCheckStatus" Type="Edm.String" MaxLength="1"/>
				            <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_26634135EFCF9268914EE7F62D4B5B39" FromRole="FromRole_assoc_26634135EFCF9268914EE7F62D4B5B39" ToRole="ToRole_assoc_26634135EFCF9268914EE7F62D4B5B39"/>
				        </EntityType>
				        <EntityType Name="A_SalesOrderItemPartnerSimlnType">
				            <Key>
				                <PropertyRef Name="SalesOrder"/>
				                <PropertyRef Name="SalesOrderItem"/>
				                <PropertyRef Name="PartnerFunction"/>
				            </Key>
				            <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"   />
				            <Property Name="SalesOrderItem" Type="Edm.String" Nullable="false" MaxLength="6"/>
				            <Property Name="PartnerFunction" Type="Edm.String" Nullable="false" MaxLength="2"/>
				            <Property Name="PartnerFunctionInternalCode" Type="Edm.String" MaxLength="2"/>
				            <Property Name="Customer" Type="Edm.String" MaxLength="10"/>
				            <Property Name="Supplier" Type="Edm.String" MaxLength="10"/>
				            <Property Name="Personnel" Type="Edm.String" MaxLength="8"/>
				            <Property Name="ContactPerson" Type="Edm.String" MaxLength="10"/>
				            <Property Name="WorkAssignmentExternalID" Type="Edm.String" MaxLength="100"/>
				            <Property Name="VATRegistration" Type="Edm.String" MaxLength="20"/>
				            <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_E0FE476A735DC637844E2A44BD30085C" FromRole="FromRole_assoc_E0FE476A735DC637844E2A44BD30085C" ToRole="ToRole_assoc_E0FE476A735DC637844E2A44BD30085C"/>
				            <NavigationProperty Name="to_SalesOrderItem" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_B7946EDD467E2BD88C87A57E636DB365" FromRole="FromRole_assoc_B7946EDD467E2BD88C87A57E636DB365" ToRole="ToRole_assoc_B7946EDD467E2BD88C87A57E636DB365"/>
				        </EntityType>
				        """;
		String metadataXml_2 = """
				<EntityType Name="A_SalesOrderItemSimulationType">
				    <Key>
				        <PropertyRef Name="SalesOrder"/>
				        <PropertyRef Name="SalesOrderItem"/>
				    </Key>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="SalesOrderItem" Type="Edm.String" Nullable="false" MaxLength="6"/>
				    <Property Name="HigherLevelItem" Type="Edm.String" MaxLength="6"/>
				    <Property Name="HigherLevelItemUsage" Type="Edm.String" MaxLength="1"/>
				    <Property Name="SalesOrderItemCategory" Type="Edm.String" MaxLength="4"/>
				    <Property Name="SalesOrderItemText" Type="Edm.String" MaxLength="40"/>
				    <Property Name="PurchaseOrderByCustomer" Type="Edm.String" MaxLength="35"/>
				    <Property Name="Material" Type="Edm.String" MaxLength="40"/>
				    <Property Name="MaterialByCustomer" Type="Edm.String" MaxLength="35"/>
				    <Property Name="PricingDate" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="PricingReferenceMaterial" Type="Edm.String" MaxLength="40"/>
				    <Property Name="RequestedQuantity" Type="Edm.Decimal" Precision="15" Scale="3"/>
				    <Property Name="RequestedQuantityUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="RequestedQuantitySAPUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="RequestedQuantityISOUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="OrderQuantityUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="OrderQuantitySAPUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="OrderQuantityISOUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConfdDelivQtyInOrderQtyUnit" Type="Edm.Decimal" Precision="15" Scale="3"/>
				    <Property Name="ItemGrossWeight" Type="Edm.Decimal" Precision="15" Scale="3"/>
				    <Property Name="ItemNetWeight" Type="Edm.Decimal" Precision="15" Scale="3"/>
				    <Property Name="ItemWeightUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ItemWeightSAPUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ItemWeightISOUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ItemVolume" Type="Edm.Decimal" Precision="15" Scale="3"/>
				    <Property Name="ItemVolumeUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ItemVolumeSAPUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ItemVolumeISOUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="OriginallyRequestedMaterial" Type="Edm.String" MaxLength="40"/>
				    <Property Name="TransactionCurrency" Type="Edm.String" MaxLength="5"/>
				    <Property Name="NetAmount" Type="Edm.Decimal" Precision="16" Scale="3"/>
				    <Property Name="TaxAmount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="CostAmount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="Subtotal1Amount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="Subtotal2Amount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="Subtotal3Amount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="Subtotal4Amount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="Subtotal5Amount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="Subtotal6Amount" Type="Edm.Decimal" Precision="14" Scale="3"/>
				    <Property Name="MaterialSubstitutionReason" Type="Edm.String" MaxLength="4"/>
				    <Property Name="MaterialGroup" Type="Edm.String" MaxLength="9"/>
				    <Property Name="MaterialPricingGroup" Type="Edm.String" MaxLength="2"/>
				    <Property Name="Batch" Type="Edm.String" MaxLength="10"/>
				    <Property Name="Plant" Type="Edm.String" MaxLength="4"/>
				    <Property Name="StorageLocation" Type="Edm.String" MaxLength="4"/>
				    <Property Name="DeliveryGroup" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ShippingPoint" Type="Edm.String" MaxLength="4"/>
				    <Property Name="ShippingType" Type="Edm.String" MaxLength="2"/>
				    <Property Name="DeliveryPriority" Type="Edm.String" MaxLength="2"/>
				    <Property Name="IncotermsClassification" Type="Edm.String" MaxLength="3"/>
				    <Property Name="IncotermsTransferLocation" Type="Edm.String" MaxLength="28"/>
				    <Property Name="IncotermsLocation1" Type="Edm.String" MaxLength="70"/>
				    <Property Name="IncotermsLocation2" Type="Edm.String" MaxLength="70"/>
				    <Property Name="ProductTaxClassification1" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification2" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification3" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification4" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification5" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification6" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification7" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification8" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ProductTaxClassification9" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ReferenceSDDocument" Type="Edm.String" MaxLength="10"/>
				    <Property Name="ReferenceSDDocumentItem" Type="Edm.String" MaxLength="6"/>
				    <Property Name="CustomerPaymentTerms" Type="Edm.String" MaxLength="4"/>
				    <Property Name="SalesDocumentRjcnReason" Type="Edm.String" MaxLength="2"/>
				    <Property Name="ZZ1_SIM_PMATN_SDI" Type="Edm.String" Nullable="true"/>
				    <Property Name="ZZ1_SIM_DELPR_SDI" Type="Edm.String" Nullable="true"/>
				    <Property Name="ZZ1_SIM_SPART_SDI" Type="Edm.String" Nullable="true"/>
				    <NavigationProperty Name="to_Partner" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_036B9A16D7EAF5FC436B5909FDAB55C7" FromRole="FromRole_assoc_036B9A16D7EAF5FC436B5909FDAB55C7" ToRole="ToRole_assoc_036B9A16D7EAF5FC436B5909FDAB55C7"/>
				    <NavigationProperty Name="to_PricingElement" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_1397E5E95667491CB0DAB8030565236A" FromRole="FromRole_assoc_1397E5E95667491CB0DAB8030565236A" ToRole="ToRole_assoc_1397E5E95667491CB0DAB8030565236A"/>
				    <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_53816724613E7ECBF4FB53952D6D1176" FromRole="FromRole_assoc_53816724613E7ECBF4FB53952D6D1176" ToRole="ToRole_assoc_53816724613E7ECBF4FB53952D6D1176"/>
				    <NavigationProperty Name="to_ScheduleLine" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_5953784DB807BE42507A68E85149A783" FromRole="FromRole_assoc_5953784DB807BE42507A68E85149A783" ToRole="ToRole_assoc_5953784DB807BE42507A68E85149A783"/>
				</EntityType>
				""";
		String metadataXml_3 = """
				<EntityType Name="A_SalesOrderItmPrcgElmntSimlnType">
				    <Key>
				        <PropertyRef Name="SalesOrder"/>
				        <PropertyRef Name="SalesOrderItem"/>
				        <PropertyRef Name="PricingProcedureStep"/>
				        <PropertyRef Name="PricingProcedureCounter"/>
				    </Key>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="SalesOrderItem" Type="Edm.String" Nullable="false" MaxLength="6"/>
				    <Property Name="PricingProcedureStep" Type="Edm.String" Nullable="false" MaxLength="3"/>
				    <Property Name="PricingProcedureCounter" Type="Edm.String" Nullable="false" MaxLength="3"/>
				    <Property Name="ConditionType" Type="Edm.String" MaxLength="4"/>
				    <Property Name="PriceConditionDeterminationDte" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="ConditionCalculationType" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionBaseValue" Type="Edm.Decimal" Precision="24" Scale="9"/>
				    <Property Name="ConditionRateValue" Type="Edm.Decimal" Precision="24" Scale="9"/>
				    <Property Name="ConditionCurrency" Type="Edm.String" MaxLength="5"/>
				    <Property Name="ConditionQuantity" Type="Edm.Decimal" Precision="5" Scale="0"/>
				    <Property Name="ConditionQuantityUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionQuantitySAPUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionQuantityISOUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionIsForStatistics" Type="Edm.Boolean"/>
				    <Property Name="ConditionOrigin" Type="Edm.String" MaxLength="1"/>
				    <Property Name="IsGroupCondition" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ConditionAmount" Type="Edm.Decimal" Precision="16" Scale="3"/>
				    <Property Name="TransactionCurrency" Type="Edm.String" MaxLength="5"/>
				    <Property Name="ConditionInactiveReason" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ConditionClass" Type="Edm.String" MaxLength="1"/>
				    <Property Name="PricingScaleBasis" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionScaleBasisValue" Type="Edm.Decimal" Precision="24" Scale="9"/>
				    <Property Name="ConditionIsManuallyChanged" Type="Edm.Boolean"/>
				    <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_C8327E2027C5F32FF63661754166938B" FromRole="FromRole_assoc_C8327E2027C5F32FF63661754166938B" ToRole="ToRole_assoc_C8327E2027C5F32FF63661754166938B"/>
				    <NavigationProperty Name="to_SalesOrderItem" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5" FromRole="FromRole_assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5" ToRole="ToRole_assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5"/>
				</EntityType>
				<EntityType Name="A_SalesOrderPartnerSimulationType">
				    <Key>
				        <PropertyRef Name="SalesOrder"/>
				        <PropertyRef Name="PartnerFunction"/>
				    </Key>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="PartnerFunction" Type="Edm.String" Nullable="false" MaxLength="2"/>
				    <Property Name="PartnerFunctionInternalCode" Type="Edm.String" MaxLength="2"/>
				    <Property Name="Customer" Type="Edm.String" MaxLength="10"/>
				    <Property Name="Supplier" Type="Edm.String" MaxLength="10"/>
				    <Property Name="Personnel" Type="Edm.String" MaxLength="8"/>
				    <Property Name="ContactPerson" Type="Edm.String" MaxLength="10"/>
				    <Property Name="WorkAssignmentExternalID" Type="Edm.String" MaxLength="100"/>
				    <Property Name="VATRegistration" Type="Edm.String" MaxLength="20"/>
				    <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_D80C4CA7BCFA91A324AFCB9CE1C08212" FromRole="FromRole_assoc_D80C4CA7BCFA91A324AFCB9CE1C08212" ToRole="ToRole_assoc_D80C4CA7BCFA91A324AFCB9CE1C08212"/>
				</EntityType>
				<EntityType Name="A_SalesOrderPrcgElmntSimlnType">
				    <Key>
				        <PropertyRef Name="SalesOrder"/>
				        <PropertyRef Name="PricingProcedureStep"/>
				        <PropertyRef Name="PricingProcedureCounter"/>
				    </Key>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="PricingProcedureStep" Type="Edm.String" Nullable="false" MaxLength="3"/>
				    <Property Name="PricingProcedureCounter" Type="Edm.String" Nullable="false" MaxLength="3"/>
				    <Property Name="ConditionType" Type="Edm.String" MaxLength="4"/>
				    <Property Name="PriceConditionDeterminationDte" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="ConditionCalculationType" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionBaseValue" Type="Edm.Decimal" Precision="24" Scale="9"/>
				    <Property Name="ConditionRateValue" Type="Edm.Decimal" Precision="24" Scale="9"/>
				    <Property Name="ConditionCurrency" Type="Edm.String" MaxLength="5"/>
				    <Property Name="ConditionQuantity" Type="Edm.Decimal" Precision="5" Scale="0"/>
				    <Property Name="ConditionQuantityUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionQuantitySAPUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionQuantityISOUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionIsForStatistics" Type="Edm.Boolean"/>
				    <Property Name="ConditionOrigin" Type="Edm.String" MaxLength="1"/>
				    <Property Name="IsGroupCondition" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ConditionAmount" Type="Edm.Decimal" Precision="16" Scale="3"/>
				    <Property Name="TransactionCurrency" Type="Edm.String" MaxLength="5"/>
				    <Property Name="ConditionInactiveReason" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ConditionClass" Type="Edm.String" MaxLength="1"/>
				    <Property Name="PricingScaleBasis" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ConditionScaleBasisValue" Type="Edm.Decimal" Precision="24" Scale="9"/>
				    <Property Name="ConditionIsManuallyChanged" Type="Edm.Boolean"/>
				    <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_2D7F894DE8E30E5BEDD13142E51B8F5F" FromRole="FromRole_assoc_2D7F894DE8E30E5BEDD13142E51B8F5F" ToRole="ToRole_assoc_2D7F894DE8E30E5BEDD13142E51B8F5F"/>
				</EntityType>
				<EntityType Name="A_SalesOrderPricingSimulationType">
				    <Key>
				        <PropertyRef Name="SalesOrder"/>
				    </Key>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="TotalNetAmount" Type="Edm.Decimal" Precision="16" Scale="3"/>
				    <Property Name="TransactionCurrency" Type="Edm.String" MaxLength="5"/>
				    <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_DE0AC028DA6E05F98B684C56601E6E40" FromRole="FromRole_assoc_DE0AC028DA6E05F98B684C56601E6E40" ToRole="ToRole_assoc_DE0AC028DA6E05F98B684C56601E6E40"/>
				</EntityType>
				<EntityType Name="A_SalesOrderScheduleLineSimlnType">
				    <Key>
				        <PropertyRef Name="SalesOrder"/>
				        <PropertyRef Name="SalesOrderItem"/>
				        <PropertyRef Name="ScheduleLine"/>
				    </Key>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="SalesOrderItem" Type="Edm.String" Nullable="false" MaxLength="6"/>
				    <Property Name="ScheduleLine" Type="Edm.String" Nullable="false" MaxLength="4"/>
				    <Property Name="RequestedDeliveryDate" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="ConfirmedDeliveryDate" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="OrderQuantityUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="OrderQuantitySAPUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="OrderQuantityISOUnit" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ScheduleLineOrderQuantity" Type="Edm.Decimal" Precision="13" Scale="3"/>
				    <Property Name="ConfdOrderQtyByMatlAvailCheck" Type="Edm.Decimal" Precision="13" Scale="3"/>
				    <Property Name="OpenConfdDelivQtyInOrdQtyUnit" Type="Edm.Decimal" Precision="13" Scale="3"/>
				    <Property Name="CorrectedQtyInOrderQtyUnit" Type="Edm.Decimal" Precision="13" Scale="3"/>
				    <NavigationProperty Name="to_SalesOrder" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_0A661E00C02ECA9E45F715E1F13EA2C2" FromRole="FromRole_assoc_0A661E00C02ECA9E45F715E1F13EA2C2" ToRole="ToRole_assoc_0A661E00C02ECA9E45F715E1F13EA2C2"/>
				    <NavigationProperty Name="to_SalesOrderItem" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB" FromRole="FromRole_assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB" ToRole="ToRole_assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB"/>
				</EntityType>
				""";
		String metadataXml_4 = """
				<EntityType Name="A_SalesOrderSimulationType">
				    <Key>
				        <PropertyRef Name="SalesOrder"/>
				    </Key>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="SalesOrderType" Type="Edm.String" MaxLength="4"/>
				    <Property Name="SalesOrganization" Type="Edm.String" MaxLength="4"/>
				    <Property Name="DistributionChannel" Type="Edm.String" MaxLength="2"/>
				    <Property Name="OrganizationDivision" Type="Edm.String" MaxLength="2"/>
				    <Property Name="SalesGroup" Type="Edm.String" MaxLength="3"/>
				    <Property Name="SalesOffice" Type="Edm.String" MaxLength="4"/>
				    <Property Name="SalesDistrict" Type="Edm.String" MaxLength="6"/>
				    <Property Name="SoldToParty" Type="Edm.String" MaxLength="10"/>
				    <Property Name="CustomerGroup" Type="Edm.String" MaxLength="2"/>
				    <Property Name="AdditionalCustomerGroup1" Type="Edm.String" MaxLength="3"/>
				    <Property Name="AdditionalCustomerGroup2" Type="Edm.String" MaxLength="3"/>
				    <Property Name="AdditionalCustomerGroup3" Type="Edm.String" MaxLength="3"/>
				    <Property Name="AdditionalCustomerGroup4" Type="Edm.String" MaxLength="3"/>
				    <Property Name="AdditionalCustomerGroup5" Type="Edm.String" MaxLength="3"/>
				    <Property Name="PurchaseOrderByCustomer" Type="Edm.String" MaxLength="35"/>
				    <Property Name="CustomerPurchaseOrderType" Type="Edm.String" MaxLength="4"/>
				    <Property Name="CustomerPurchaseOrderDate" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="SalesOrderDate" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="TransactionCurrency" Type="Edm.String" MaxLength="5"/>
				    <Property Name="AccountingDocExternalReference" Type="Edm.String" MaxLength="16"/>
				    <Property Name="CustomerTaxClassification1" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification2" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification3" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification4" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification5" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification6" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification7" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification8" Type="Edm.String" MaxLength="1"/>
				    <Property Name="CustomerTaxClassification9" Type="Edm.String" MaxLength="1"/>
				    <Property Name="TaxDepartureCountry" Type="Edm.String" MaxLength="3"/>
				    <Property Name="VATRegistrationCountry" Type="Edm.String" MaxLength="3"/>
				    <Property Name="PriceListType" Type="Edm.String" MaxLength="2"/>
				    <Property Name="CustomerPriceGroup" Type="Edm.String" MaxLength="2"/>
				    <Property Name="SDDocumentReason" Type="Edm.String" MaxLength="3"/>
				    <Property Name="PricingDate" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="SDPricingProcedure" Type="Edm.String" MaxLength="6"/>
				    <Property Name="RequestedDeliveryDate" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="ShippingCondition" Type="Edm.String" MaxLength="2"/>
				    <Property Name="CompleteDeliveryIsDefined" Type="Edm.Boolean"/>
				    <Property Name="ShippingType" Type="Edm.String" MaxLength="2"/>
				    <Property Name="IncotermsClassification" Type="Edm.String" MaxLength="3"/>
				    <Property Name="IncotermsTransferLocation" Type="Edm.String" MaxLength="28"/>
				    <Property Name="IncotermsLocation1" Type="Edm.String" MaxLength="70"/>
				    <Property Name="IncotermsLocation2" Type="Edm.String" MaxLength="70"/>
				    <Property Name="IncotermsVersion" Type="Edm.String" MaxLength="4"/>
				    <Property Name="ReferenceSDDocument" Type="Edm.String" MaxLength="10"/>
				    <Property Name="CustomerPaymentTerms" Type="Edm.String" MaxLength="4"/>
				    <Property Name="PaymentMethod" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ZZ1_SIM_CARGO_SDH" Type="Edm.String" Nullable="true"/>
				    <Property Name="ZZ1_SIM_FECP_SDH" Type="Edm.DateTime" Precision="0"/>
				    <Property Name="ZZ1_SIM_TIPOPET_SDH" Type="Edm.Int32" Nullable="true"/>
				    <Property Name="ZZ1_SIM_TIPOCON_SDH" Type="Edm.String" Nullable="true"/>
				    <Property Name="ZZ1_SIM_PLTYP_SDH" Type="Edm.String" Nullable="true"/>
				    <Property Name="ZZ1_SIM_KONDA_SDH" Type="Edm.String" Nullable="true"/>
				    <Property Name="ZZ1_SIM_KURST_SDH" Type="Edm.String" Nullable="true"/>
				    <NavigationProperty Name="to_Credit" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_09E895DA67A75176385AC79D884760B5" FromRole="FromRole_assoc_09E895DA67A75176385AC79D884760B5" ToRole="ToRole_assoc_09E895DA67A75176385AC79D884760B5"/>
				    <NavigationProperty Name="to_Item" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_86B3631913169957CBD0C2992D08B371" FromRole="FromRole_assoc_86B3631913169957CBD0C2992D08B371" ToRole="ToRole_assoc_86B3631913169957CBD0C2992D08B371"/>
				    <NavigationProperty Name="to_Partner" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_C5EED2E8FFB18945CB96D5EBA826FB83" FromRole="FromRole_assoc_C5EED2E8FFB18945CB96D5EBA826FB83" ToRole="ToRole_assoc_C5EED2E8FFB18945CB96D5EBA826FB83"/>
				    <NavigationProperty Name="to_Pricing" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B" FromRole="FromRole_assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B" ToRole="ToRole_assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B"/>
				    <NavigationProperty Name="to_PricingElement" Relationship="API_SALES_ORDER_SIMULATION_SRV.assoc_3B7A5DAD1E49589629F292F5755BF495" FromRole="FromRole_assoc_3B7A5DAD1E49589629F292F5755BF495" ToRole="ToRole_assoc_3B7A5DAD1E49589629F292F5755BF495"/>
				</EntityType>
				<EntityType Name="A_SlsOrdSimlnValAddedSrvcType">
				    <Key>
				        <PropertyRef Name="ValueAddedServiceType"/>
				        <PropertyRef Name="ValueAddedSubServiceType"/>
				        <PropertyRef Name="SalesOrder"/>
				        <PropertyRef Name="SalesOrderItem"/>
				    </Key>
				    <Property Name="ValueAddedServiceType" Type="Edm.String" Nullable="false" MaxLength="2"/>
				    <Property Name="ValueAddedSubServiceType" Type="Edm.String" Nullable="false" MaxLength="5"/>
				    <Property Name="SalesOrder" Type="Edm.String" Nullable="false" MaxLength="10"/>
				    <Property Name="SalesOrderItem" Type="Edm.String" Nullable="false" MaxLength="6"/>
				    <Property Name="ValAddedSrvcTransactionNumber" Type="Edm.String" MaxLength="10"/>
				    <Property Name="ValAddedSrvcItemGroup" Type="Edm.String" MaxLength="5"/>
				    <Property Name="ValAddedSrvcItemNumber" Type="Edm.String" MaxLength="5"/>
				    <Property Name="ValueAddedServiceProduct" Type="Edm.String" MaxLength="40"/>
				    <Property Name="ValAddedSrvcHasToBeOrdered" Type="Edm.Boolean"/>
				    <Property Name="ValAddedSrvcIncrement" Type="Edm.String" MaxLength="4"/>
				    <Property Name="ValueAddedServiceChargeCode" Type="Edm.String" MaxLength="3"/>
				    <Property Name="ValAddedSrvcIsCreatedManually" Type="Edm.String" MaxLength="1"/>
				    <Property Name="ValAddedSrvcItemNumberInSD" Type="Edm.String" MaxLength="6"/>
				    <Property Name="ValAddedSrvcIsRlvtForProcmt" Type="Edm.Boolean"/>
				    <Property Name="ValueAddedServiceText1" Type="Edm.String" MaxLength="20"/>
				    <Property Name="ValueAddedServiceText2" Type="Edm.String" MaxLength="20"/>
				    <Property Name="ValueAddedServiceText3" Type="Edm.String" MaxLength="20"/>
				    <Property Name="ValueAddedServiceLongText" Type="Edm.String" MaxLength="132"/>
				</EntityType>
				""";
		String metadataXml_5 = """
				<Association Name="assoc_036B9A16D7EAF5FC436B5909FDAB55C7" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="1" Role="FromRole_assoc_036B9A16D7EAF5FC436B5909FDAB55C7"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemPartnerSimlnType" Multiplicity="*" Role="ToRole_assoc_036B9A16D7EAF5FC436B5909FDAB55C7"/>
				</Association>
				<Association Name="assoc_1397E5E95667491CB0DAB8030565236A" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="1" Role="FromRole_assoc_1397E5E95667491CB0DAB8030565236A"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItmPrcgElmntSimlnType" Multiplicity="*" Role="ToRole_assoc_1397E5E95667491CB0DAB8030565236A"/>
				</Association>
				<Association Name="assoc_53816724613E7ECBF4FB53952D6D1176" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="1" Role="FromRole_assoc_53816724613E7ECBF4FB53952D6D1176"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_53816724613E7ECBF4FB53952D6D1176"/>
				</Association>
				<Association Name="assoc_5953784DB807BE42507A68E85149A783" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="1" Role="FromRole_assoc_5953784DB807BE42507A68E85149A783"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType" Multiplicity="*" Role="ToRole_assoc_5953784DB807BE42507A68E85149A783"/>
				</Association>
				<Association Name="assoc_09E895DA67A75176385AC79D884760B5" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="FromRole_assoc_09E895DA67A75176385AC79D884760B5"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderCreditSimulationType" Multiplicity="1" Role="ToRole_assoc_09E895DA67A75176385AC79D884760B5"/>
				</Association>
				<Association Name="assoc_86B3631913169957CBD0C2992D08B371" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="FromRole_assoc_86B3631913169957CBD0C2992D08B371"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="*" Role="ToRole_assoc_86B3631913169957CBD0C2992D08B371"/>
				</Association>
				<Association Name="assoc_C5EED2E8FFB18945CB96D5EBA826FB83" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="FromRole_assoc_C5EED2E8FFB18945CB96D5EBA826FB83"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPartnerSimulationType" Multiplicity="*" Role="ToRole_assoc_C5EED2E8FFB18945CB96D5EBA826FB83"/>
				</Association>
				<Association Name="assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="FromRole_assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPricingSimulationType" Multiplicity="1" Role="ToRole_assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B"/>
				</Association>
				<Association Name="assoc_3B7A5DAD1E49589629F292F5755BF495" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="FromRole_assoc_3B7A5DAD1E49589629F292F5755BF495"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPrcgElmntSimlnType" Multiplicity="*" Role="ToRole_assoc_3B7A5DAD1E49589629F292F5755BF495"/>
				</Association>
				<Association Name="assoc_C8327E2027C5F32FF63661754166938B" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItmPrcgElmntSimlnType" Multiplicity="1" Role="FromRole_assoc_C8327E2027C5F32FF63661754166938B"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_C8327E2027C5F32FF63661754166938B"/>
				</Association>
				<Association Name="assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItmPrcgElmntSimlnType" Multiplicity="1" Role="FromRole_assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="1" Role="ToRole_assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5"/>
				</Association>
				<Association Name="assoc_26634135EFCF9268914EE7F62D4B5B39" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderCreditSimulationType" Multiplicity="1" Role="FromRole_assoc_26634135EFCF9268914EE7F62D4B5B39"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_26634135EFCF9268914EE7F62D4B5B39"/>
				</Association>
				<Association Name="assoc_0A661E00C02ECA9E45F715E1F13EA2C2" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType" Multiplicity="1" Role="FromRole_assoc_0A661E00C02ECA9E45F715E1F13EA2C2"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_0A661E00C02ECA9E45F715E1F13EA2C2"/>
				</Association>
				<Association Name="assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType" Multiplicity="1" Role="FromRole_assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="1" Role="ToRole_assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB"/>
				</Association>
				<Association Name="assoc_D80C4CA7BCFA91A324AFCB9CE1C08212" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPartnerSimulationType" Multiplicity="1" Role="FromRole_assoc_D80C4CA7BCFA91A324AFCB9CE1C08212"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_D80C4CA7BCFA91A324AFCB9CE1C08212"/>
				</Association>
				<Association Name="assoc_DE0AC028DA6E05F98B684C56601E6E40" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPricingSimulationType" Multiplicity="1" Role="FromRole_assoc_DE0AC028DA6E05F98B684C56601E6E40"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_DE0AC028DA6E05F98B684C56601E6E40"/>
				</Association>
				<Association Name="assoc_2D7F894DE8E30E5BEDD13142E51B8F5F" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPrcgElmntSimlnType" Multiplicity="1" Role="FromRole_assoc_2D7F894DE8E30E5BEDD13142E51B8F5F"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_2D7F894DE8E30E5BEDD13142E51B8F5F"/>
				</Association>
				<Association Name="assoc_E0FE476A735DC637844E2A44BD30085C" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemPartnerSimlnType" Multiplicity="1" Role="FromRole_assoc_E0FE476A735DC637844E2A44BD30085C"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType" Multiplicity="1" Role="ToRole_assoc_E0FE476A735DC637844E2A44BD30085C"/>
				</Association>
				<Association Name="assoc_B7946EDD467E2BD88C87A57E636DB365" >
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemPartnerSimlnType" Multiplicity="1" Role="FromRole_assoc_B7946EDD467E2BD88C87A57E636DB365"/>
				    <End Type="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType" Multiplicity="1" Role="ToRole_assoc_B7946EDD467E2BD88C87A57E636DB365"/>
				</Association>
				""";
		String metadataXml_6 = """
				<EntityContainer Name="API_SALES_ORDER_SIMULATION_SRV_Entities" m:IsDefaultEntityContainer="true">
				    <EntitySet Name="A_SalesOrderCreditSimulation" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderCreditSimulationType"/>
				    <EntitySet Name="A_SalesOrderItemPartnerSimln" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemPartnerSimlnType"/>
				    <EntitySet Name="A_SalesOrderItemSimulation" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType"/>
				    <EntitySet Name="A_SalesOrderItmPrcgElmntSimln" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItmPrcgElmntSimlnType"/>
				    <EntitySet Name="A_SalesOrderPartnerSimulation" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPartnerSimulationType"/>
				    <EntitySet Name="A_SalesOrderPrcgElmntSimln" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPrcgElmntSimlnType"/>
				    <EntitySet Name="A_SalesOrderPricingSimulation" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPricingSimulationType"/>
				    <EntitySet Name="A_SalesOrderScheduleLineSimln" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType"/>
				    <EntitySet Name="A_SalesOrderSimulation" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType"/>
				    <EntitySet Name="A_SlsOrdSimlnValAddedSrvc" EntityType="API_SALES_ORDER_SIMULATION_SRV.A_SlsOrdSimlnValAddedSrvcType"/>
				    <AssociationSet Name="assoc_5953784DB807BE42507A68E85149A783" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_5953784DB807BE42507A68E85149A783">
				        <End EntitySet="A_SalesOrderItemSimulation" Role="FromRole_assoc_5953784DB807BE42507A68E85149A783"/>
				        <End EntitySet="A_SalesOrderScheduleLineSimln" Role="ToRole_assoc_5953784DB807BE42507A68E85149A783"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_036B9A16D7EAF5FC436B5909FDAB55C7" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_036B9A16D7EAF5FC436B5909FDAB55C7">
				        <End EntitySet="A_SalesOrderItemSimulation" Role="FromRole_assoc_036B9A16D7EAF5FC436B5909FDAB55C7"/>
				        <End EntitySet="A_SalesOrderItemPartnerSimln" Role="ToRole_assoc_036B9A16D7EAF5FC436B5909FDAB55C7"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB">
				        <End EntitySet="A_SalesOrderScheduleLineSimln" Role="FromRole_assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB"/>
				        <End EntitySet="A_SalesOrderItemSimulation" Role="ToRole_assoc_5F2ADB264BF23A2504FDED7DD5AE3EEB"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_2D7F894DE8E30E5BEDD13142E51B8F5F" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_2D7F894DE8E30E5BEDD13142E51B8F5F">
				        <End EntitySet="A_SalesOrderPrcgElmntSimln" Role="FromRole_assoc_2D7F894DE8E30E5BEDD13142E51B8F5F"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_2D7F894DE8E30E5BEDD13142E51B8F5F"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_3B7A5DAD1E49589629F292F5755BF495" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_3B7A5DAD1E49589629F292F5755BF495">
				        <End EntitySet="A_SalesOrderSimulation" Role="FromRole_assoc_3B7A5DAD1E49589629F292F5755BF495"/>
				        <End EntitySet="A_SalesOrderPrcgElmntSimln" Role="ToRole_assoc_3B7A5DAD1E49589629F292F5755BF495"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_C5EED2E8FFB18945CB96D5EBA826FB83" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_C5EED2E8FFB18945CB96D5EBA826FB83">
				        <End EntitySet="A_SalesOrderSimulation" Role="FromRole_assoc_C5EED2E8FFB18945CB96D5EBA826FB83"/>
				        <End EntitySet="A_SalesOrderPartnerSimulation" Role="ToRole_assoc_C5EED2E8FFB18945CB96D5EBA826FB83"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5">
				        <End EntitySet="A_SalesOrderItmPrcgElmntSimln" Role="FromRole_assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5"/>
				        <End EntitySet="A_SalesOrderItemSimulation" Role="ToRole_assoc_9F3D7582A67C0D2CE50C97F35CA6BCA5"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_B7946EDD467E2BD88C87A57E636DB365" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_B7946EDD467E2BD88C87A57E636DB365">
				        <End EntitySet="A_SalesOrderItemPartnerSimln" Role="FromRole_assoc_B7946EDD467E2BD88C87A57E636DB365"/>
				        <End EntitySet="A_SalesOrderItemSimulation" Role="ToRole_assoc_B7946EDD467E2BD88C87A57E636DB365"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_DE0AC028DA6E05F98B684C56601E6E40" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_DE0AC028DA6E05F98B684C56601E6E40">
				        <End EntitySet="A_SalesOrderPricingSimulation" Role="FromRole_assoc_DE0AC028DA6E05F98B684C56601E6E40"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_DE0AC028DA6E05F98B684C56601E6E40"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_1397E5E95667491CB0DAB8030565236A" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_1397E5E95667491CB0DAB8030565236A">
				        <End EntitySet="A_SalesOrderItemSimulation" Role="FromRole_assoc_1397E5E95667491CB0DAB8030565236A"/>
				        <End EntitySet="A_SalesOrderItmPrcgElmntSimln" Role="ToRole_assoc_1397E5E95667491CB0DAB8030565236A"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B">
				        <End EntitySet="A_SalesOrderSimulation" Role="FromRole_assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B"/>
				        <End EntitySet="A_SalesOrderPricingSimulation" Role="ToRole_assoc_0B9AFD90BACEE6F01CD6F67E38FB5A5B"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_09E895DA67A75176385AC79D884760B5" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_09E895DA67A75176385AC79D884760B5">
				        <End EntitySet="A_SalesOrderSimulation" Role="FromRole_assoc_09E895DA67A75176385AC79D884760B5"/>
				        <End EntitySet="A_SalesOrderCreditSimulation" Role="ToRole_assoc_09E895DA67A75176385AC79D884760B5"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_E0FE476A735DC637844E2A44BD30085C" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_E0FE476A735DC637844E2A44BD30085C">
				        <End EntitySet="A_SalesOrderItemPartnerSimln" Role="FromRole_assoc_E0FE476A735DC637844E2A44BD30085C"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_E0FE476A735DC637844E2A44BD30085C"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_D80C4CA7BCFA91A324AFCB9CE1C08212" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_D80C4CA7BCFA91A324AFCB9CE1C08212">
				        <End EntitySet="A_SalesOrderPartnerSimulation" Role="FromRole_assoc_D80C4CA7BCFA91A324AFCB9CE1C08212"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_D80C4CA7BCFA91A324AFCB9CE1C08212"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_C8327E2027C5F32FF63661754166938B" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_C8327E2027C5F32FF63661754166938B">
				        <End EntitySet="A_SalesOrderItmPrcgElmntSimln" Role="FromRole_assoc_C8327E2027C5F32FF63661754166938B"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_C8327E2027C5F32FF63661754166938B"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_0A661E00C02ECA9E45F715E1F13EA2C2" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_0A661E00C02ECA9E45F715E1F13EA2C2">
				        <End EntitySet="A_SalesOrderScheduleLineSimln" Role="FromRole_assoc_0A661E00C02ECA9E45F715E1F13EA2C2"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_0A661E00C02ECA9E45F715E1F13EA2C2"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_26634135EFCF9268914EE7F62D4B5B39" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_26634135EFCF9268914EE7F62D4B5B39">
				        <End EntitySet="A_SalesOrderCreditSimulation" Role="FromRole_assoc_26634135EFCF9268914EE7F62D4B5B39"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_26634135EFCF9268914EE7F62D4B5B39"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_86B3631913169957CBD0C2992D08B371" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_86B3631913169957CBD0C2992D08B371">
				        <End EntitySet="A_SalesOrderSimulation" Role="FromRole_assoc_86B3631913169957CBD0C2992D08B371"/>
				        <End EntitySet="A_SalesOrderItemSimulation" Role="ToRole_assoc_86B3631913169957CBD0C2992D08B371"/>
				    </AssociationSet>
				    <AssociationSet Name="assoc_53816724613E7ECBF4FB53952D6D1176" Association="API_SALES_ORDER_SIMULATION_SRV.assoc_53816724613E7ECBF4FB53952D6D1176">
				        <End EntitySet="A_SalesOrderItemSimulation" Role="FromRole_assoc_53816724613E7ECBF4FB53952D6D1176"/>
				        <End EntitySet="A_SalesOrderSimulation" Role="ToRole_assoc_53816724613E7ECBF4FB53952D6D1176"/>
				    </AssociationSet>
				</EntityContainer>
				""";
		String metadataXml_7 = """
				<Annotation Term="Core.SchemaVersion" String="1.0.0" xmlns="http://docs.oasis-open.org/odata/ns/edm"/>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Common.ApplyMultiUnitBehaviorForSortingAndFiltering" Bool="true"/>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SlsOrdSimlnValAddedSrvc" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.FilterRestrictions">
				        <Record>
				            <PropertyValue Property="NonFilterableProperties">
				                <Collection>
				                    <PropertyPath>ValueAddedServiceLongText</PropertyPath>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				    <Annotation Term="Capabilities.SortRestrictions">
				        <Record>
				            <PropertyValue Property="NonSortableProperties">
				                <Collection>
				                    <PropertyPath>ValueAddedServiceLongText</PropertyPath>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderCreditSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderItemPartnerSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrderItem"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="false"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderItemSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_Partner"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="false"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_PricingElement"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="false"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_ScheduleLine"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="false"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				""";
		String metadataXml_8 = """
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderItmPrcgElmntSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrderItem"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="false"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderPartnerSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderPrcgElmntSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderPricingSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				<Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderScheduleLineSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				    <Annotation Term="Capabilities.NavigationRestrictions">
				        <Record>
				            <PropertyValue Property="RestrictedProperties">
				                <Collection>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrder"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="true"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                    <Record>
				                        <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_SalesOrderItem"/>
				                        <PropertyValue Property="InsertRestrictions">
				                            <Record>
				                                <PropertyValue Property="Insertable" Bool="false"/>
				                            </Record>
				                        </PropertyValue>
				                    </Record>
				                </Collection>
				            </PropertyValue>
				        </Record>
				    </Annotation>
				</Annotations>
				""";
		String metadataXml_9 = """
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.NavigationRestrictions">
				                <Record>
				                    <PropertyValue Property="RestrictedProperties">
				                        <Collection>
				                            <Record>
				                                <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_Credit"/>
				                                <PropertyValue Property="InsertRestrictions">
				                                    <Record>
				                                        <PropertyValue Property="Insertable" Bool="false"/>
				                                    </Record>
				                                </PropertyValue>
				                            </Record>
				                            <Record>
				                                <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_Item"/>
				                                <PropertyValue Property="InsertRestrictions">
				                                    <Record>
				                                        <PropertyValue Property="Insertable" Bool="false"/>
				                                    </Record>
				                                </PropertyValue>
				                            </Record>
				                            <Record>
				                                <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_Partner"/>
				                                <PropertyValue Property="InsertRestrictions">
				                                    <Record>
				                                        <PropertyValue Property="Insertable" Bool="false"/>
				                                    </Record>
				                                </PropertyValue>
				                            </Record>
				                            <Record>
				                                <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_Pricing"/>
				                                <PropertyValue Property="InsertRestrictions">
				                                    <Record>
				                                        <PropertyValue Property="Insertable" Bool="false"/>
				                                    </Record>
				                                </PropertyValue>
				                            </Record>
				                            <Record>
				                                <PropertyValue Property="NavigationProperty" NavigationPropertyPath="to_PricingElement"/>
				                                <PropertyValue Property="InsertRestrictions">
				                                    <Record>
				                                        <PropertyValue Property="Insertable" Bool="false"/>
				                                    </Record>
				                                </PropertyValue>
				                            </Record>
				                        </Collection>
				                    </PropertyValue>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType/ConfdDelivQtyInOrderQtyUnit" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="OrderQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType/ItemGrossWeight" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="ItemWeightISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType/ItemNetWeight" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="ItemWeightISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType/ItemVolume" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="ItemVolumeISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItemSimulationType/RequestedQuantity" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="RequestedQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderItmPrcgElmntSimlnType/ConditionQuantity" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="ConditionQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderPrcgElmntSimlnType/ConditionQuantity" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="ConditionQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType/ConfdOrderQtyByMatlAvailCheck" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="OrderQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType/CorrectedQtyInOrderQtyUnit" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="OrderQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType/OpenConfdDelivQtyInOrdQtyUnit" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="OrderQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderScheduleLineSimlnType/ScheduleLineOrderQuantity" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Measures.UNECEUnit" Path="OrderQuantityISOUnit"/>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderCreditSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderItemPartnerSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderItemSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderItmPrcgElmntSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderPartnerSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderPrcgElmntSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderPricingSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderScheduleLineSimln" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				        </Annotations>
				        <Annotations Target="API_SALES_ORDER_SIMULATION_SRV.API_SALES_ORDER_SIMULATION_SRV_Entities/A_SalesOrderSimulation" xmlns="http://docs.oasis-open.org/odata/ns/edm">
				            <Annotation Term="Capabilities.ReadRestrictions">
				                <Record>
				                    <PropertyValue Property="Readable" Bool="false"/>
				                </Record>
				            </Annotation>
				            <Annotation Term="Capabilities.InsertRestrictions">
				                <Record>
				                    <PropertyValue Property="Description" String="Simulates the creation of a sales order."/>
				                    <PropertyValue Property="LongDescription" String="Simulates the creation of a sales order, including price calculation with results, Available-to-Promise (ATP) check, partner determination, and credit check."/>
				                </Record>
				            </Annotation>
				        </Annotations>
				    </Schema>
				  </edmx:DataServices>
				</edmx:Edmx>
				         """;

		HttpHeaders headers = new HttpHeaders();
		if ("Fetch".equalsIgnoreCase(csrfToken)) {
			headers.set("x-csrf-token", "fake-token-12345");
			headers.set("Set-Cookie", "SAP_SESSIONID=FAKE123SESSION");
		}
		String metadataXml = metadataXml_1.stripLeading() + metadataXml_2.stripLeading() + metadataXml_3.stripLeading() + metadataXml_4.stripLeading()
				+ metadataXml_5.stripLeading() + metadataXml_6.stripLeading() + metadataXml_7.stripLeading() + metadataXml_8.stripLeading()
				+ metadataXml_9.stripLeading();
		return new ResponseEntity<>(metadataXml, headers, HttpStatus.OK);
	}

	@PostMapping(value = "/A_SalesOrderSimulation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> createSalesOrderSimulation(@RequestBody Map<String, Object> input,
			@RequestHeader(value = "x-csrf-token", required = false) String csrfToken) {
		log.info("Se llama a salesOrderSimulation con parametros: " + input);
		Map<String, Object> response = null;
		try {
			response = Map.of("d", addMetadata(input));
		} catch (Exception e) {
			log.error("Error al crear salesOrderSimulation:" + e, e);
		}
		log.info("Se devuelve respuesta: " + response);
		return response;
	}

	// Helper para incluir __metadata en la respuesta
	@SuppressWarnings("unchecked")
	private Map<String, Object> addMetadata(Map<String, Object> data) {
		data.remove("to_PricingElement");
		data.put("ZZ1_SIM_PLTYP_SDH", "TR");
		data.put("ZZ1_SIM_KONDA_SDH", "GRUPOPRECIOCLIENTE");
		data.put("ZZ1_SIM_KURST_SDH", "TIPOCOTIZACION");
		ArrayList<LinkedHashMap<String, Object>> items = (ArrayList<LinkedHashMap<String, Object>>) data.get("to_Item");
		BigDecimal totalNetAmount = BigDecimal.ZERO;
		for (LinkedHashMap<String, Object> item : items) {
			ArrayList<LinkedHashMap<?, ?>> itemPricing = (ArrayList<LinkedHashMap<?, ?>>) item.get("to_PricingElement");
			itemPricing.clear();
			addItemPricing(itemPricing, "ZPR0", "EUR", "1", "UN");
			BigDecimal netAmount = addItemPricing(itemPricing, "ZPR1", "EUR", "0", "");
			totalNetAmount = totalNetAmount.add(netAmount);
			BigDecimal taxAmount = addTaxPricing(itemPricing, "MWST", "EUR", netAmount, "0", "");
			item.put("NetAmount", String.valueOf(netAmount));
			item.put("TaxAmount", String.valueOf(taxAmount));
		}
		LinkedHashMap<String, Object> pricing = (LinkedHashMap<String, Object>) data.get("to_Pricing");
		pricing.put("SalesOrder", "SALESORDER");
		pricing.put("TotalNetAmount", String.valueOf(totalNetAmount));
		pricing.put("TransactionCurrency", "EUR");
		Map<String, Object> result = new LinkedHashMap<>(data);
		result.put("__metadata", Map.of("id",
				"https://example.com/sap/opu/odata/sap/API_SALES_ORDER_SIMULATION_SRV/A_SalesOrderSimulation('" + data.get("PurchaseOrderByCustomer") + "')",
				"uri",
				"https://example.com/sap/opu/odata/sap/API_SALES_ORDER_SIMULATION_SRV/A_SalesOrderSimulation('" + data.get("PurchaseOrderByCustomer") + "')",
				"type", "API_SALES_ORDER_SIMULATION_SRV.A_SalesOrderSimulationType"));
		return result;
	}

	private BigDecimal addItemPricing(ArrayList<LinkedHashMap<?, ?>> itemPricing, String conditionType, String conditionCurrency, String conditionQuantity,
			String conditionQuantityUnit) {
		LinkedHashMap<String, Object> pricing = new LinkedHashMap<>();
		pricing.put("ConditionType", conditionType);
		double value = 100.0 * Math.random();
		BigDecimal bdValue = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
		pricing.put("ConditionRateValue", String.valueOf(bdValue));
		pricing.put("ConditionCurrency", conditionCurrency);
		pricing.put("ConditionAmount", String.valueOf(bdValue));
		pricing.put("TransactionCurrency", "EUR");
		pricing.put("ConditionQuantity", conditionQuantity);
		pricing.put("ConditionQuantityUnit", conditionQuantityUnit);
		itemPricing.add(pricing);
		return bdValue;
	}

	private BigDecimal addTaxPricing(ArrayList<LinkedHashMap<?, ?>> itemPricing, String conditionType, String conditionCurrency, BigDecimal netAmount,
			String conditionQuantity, String conditionQuantityUnit) {
		LinkedHashMap<String, Object> pricing = new LinkedHashMap<>();
		pricing.put("ConditionType", conditionType);
		double value = 21.0 * Math.random();
		BigDecimal bdValue = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
		pricing.put("ConditionRateValue", String.valueOf(bdValue));
		pricing.put("ConditionCurrency", conditionCurrency);
		BigDecimal amount = bdValue.multiply(netAmount).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
		pricing.put("ConditionAmount", String.valueOf(amount));
		pricing.put("TransactionCurrency", "EUR");
		pricing.put("ConditionQuantity", conditionQuantity);
		pricing.put("ConditionQuantityUnit", conditionQuantityUnit);
		itemPricing.add(pricing);
		return amount;
	}

}
