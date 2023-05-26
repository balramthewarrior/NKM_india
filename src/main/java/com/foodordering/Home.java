package com.foodordering;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Home {

	@GetMapping("/")
	public String index() {
		System.out.println("i am here");
		return "home";
	}
}
