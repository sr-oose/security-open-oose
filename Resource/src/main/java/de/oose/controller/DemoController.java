package de.oose.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo")
	public Map<String, String> getTest() {
		Map<String, String> map = new HashMap<>();
		map.put("data", "unprotected_data");
		return map;
	}
}
