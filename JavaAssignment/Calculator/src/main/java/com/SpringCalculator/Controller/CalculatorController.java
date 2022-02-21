package com.SpringCalculator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringCalculator.SharedFunction.CalculatorFunctions;

@RestController
public class CalculatorController {
	
	@Autowired
	private CalculatorFunctions calc;
	
	@PostMapping("/+")
	public Integer Addition(@RequestParam int i, @RequestParam int j) {
		return calc.add(i, j);
	}
	
	@PostMapping("/-")
	public Integer Subtraction(@RequestParam int i, @RequestParam int j) {
		return calc.sub(i, j);
	}
	
	@PostMapping("/*")
	public Integer Multiplication(@RequestParam int i, @RequestParam int j) {
		return calc.mul(i, j);
	}
	
	@PostMapping("//")
	public Double Division(@RequestParam int i, @RequestParam int j) {
		return calc.div(i, j);
	}
}