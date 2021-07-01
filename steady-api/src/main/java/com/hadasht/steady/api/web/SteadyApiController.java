package com.hadasht.steady.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SteadyApiController {

	@GetMapping("/api/list")
	public String list() {
		return "list";
	}
}
