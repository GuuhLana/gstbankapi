package com.gtechsys.gtsbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API Status Check")
@RestController
@RequestMapping("/api/check")
public class ApiCheckStatusController {

	@GetMapping
	private String checkStatus() {
		return "API está funcionando";
	}
}
