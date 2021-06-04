package com.arioval.roulette.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arioval.roulette.models.RouletteModel;
import com.arioval.roulette.service.RouletteServiceImp;


@RestController
@RequestMapping("/api")
public class RouletteController {
	@Autowired
	private RouletteServiceImp rouletteService;
	
	private String statusOfOperation;

	@PostMapping(value = "/roulette", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> createRoulette(@RequestBody RouletteModel roulette) {
		rouletteService.createRoulette(roulette);
		return new ResponseEntity<>(roulette.getId(),HttpStatus.CREATED);
	}
	
	@PutMapping("/roulette/open/{id}")
	public ResponseEntity<String> openRoulette(@PathVariable String id) {
		statusOfOperation = rouletteService.openRoulette(id);
    	return new ResponseEntity<>(statusOfOperation,HttpStatus.CREATED);
	}
	
	@GetMapping("/roulette/status")
	public Map<String, String> getRouletteWithStatus() {
		Map<String, String> rouletteStatus= rouletteService.getRoulettesWithStatus();
		return rouletteStatus;
	}
    
	
	@GetMapping("/roulette")
	public Map<String, RouletteModel> getRoulettes() {
		return rouletteService.getRoulettes();
	}
	
	
	@GetMapping("/roulette/{id}")
	public RouletteModel getRouletteById(@PathVariable String id) {
		return rouletteService.getRouletteById(id);
	}
	
    
	@DeleteMapping("/roulette/{id}")
	public void deleteRoulete(@PathVariable String id)
	{
		rouletteService.deleteRoulette(id);
	}
}