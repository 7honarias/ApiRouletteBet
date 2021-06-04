package com.arioval.roulette.controllers;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arioval.roulette.models.BetModel;
import com.arioval.roulette.models.RouletteModel;
import com.arioval.roulette.service.BetServiceImp;
import com.arioval.roulette.service.RouletteServiceImp;


@RestController
@RequestMapping("/api")
public class RouletteController {
	@Autowired
	private RouletteServiceImp ruletaService;
	
	private String statusOfOperation;

	@PostMapping(value = "/roulette", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> createRoulette(@RequestBody RouletteModel roulette) {
		ruletaService.createRoulette(roulette);
		return new ResponseEntity<>(roulette.getId(),HttpStatus.CREATED);
	}
	
	@PutMapping("/ruleta/open/{id}")
	public ResponseEntity<String> abrirRuleta(@PathVariable String id) {
		statusOfOperation = ruletaService.openRoulette(id);
    	return new ResponseEntity<>(statusOfOperation,HttpStatus.CREATED);
	}
}