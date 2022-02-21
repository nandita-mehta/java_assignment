package com.SpringCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.SpringCalculator.SharedFunction.CalculatorFunctions;

@SpringBootTest
class CalculatorApplicationTests {
	
	@Autowired
	private CalculatorFunctions calc;
	
	@Test
	void contextLoads() {
		assertEquals(10,calc.add(6,4));
		assertEquals(2,calc.sub(6,4));
		assertEquals(24,calc.mul(6,4));
		assertEquals(1.5,calc.div(6,4));
	}
}
