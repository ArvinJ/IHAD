package com.ahhf.ljxbw.utils;

import org.slf4j.LoggerFactory;

import com.ahhf.ljxbw.filter.LogFilter;

import ch.qos.logback.classic.Logger;

public enum EnumArvin {
	UNIT_1("1", "1", "SEACTION_A"), UNIT_2("1", "2", "SEACTION_B"), UNIT_3("1", "3", "SEACTION_C");

	String value1, value2, value3;

	EnumArvin(String value1, String value2, String value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	
	public static void main(String[] args) {
		if(EnumArvin.UNIT_1.getValue2().equals("1")){
			System.out.println("---"+EnumArvin.UNIT_1.getValue3());
		} 
		
		EnumArvin [] units = EnumArvin.values();
		for (EnumArvin enumArvin : units) {
			if(enumArvin.value2.equals("q"))
				enumArvin.getValue3();
		}
		
	}
	
}
