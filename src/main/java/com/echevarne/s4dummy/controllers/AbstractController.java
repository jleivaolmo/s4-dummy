package com.echevarne.s4dummy.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class AbstractController {
	
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
}
