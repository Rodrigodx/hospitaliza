package com.rodrigo.hospitaliza.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConverterDateService {
	
	
	public LocalDate converter(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}
	
}
