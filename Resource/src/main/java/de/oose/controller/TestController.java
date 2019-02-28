package de.oose.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public Map<String, String> getTest() {
		Map<String, String> map = new HashMap<>();
		map.put("data", "protected_data");
		return map;
	}

}
