package com.SpringCalculator.SharedFunction;

import org.springframework.stereotype.Service;

@Service
public class CalculatorFunctions {
	public Integer add(int i, int j) {
		return i+j;
	}
	
	public Integer sub(int i, int j) {
		return i-j;
	}
	
	public Integer mul(int i, int j) {
		return i*j;
	}
	
	public Double div(int i, int j) {
		return (double)i/(double)j;
	}
}
