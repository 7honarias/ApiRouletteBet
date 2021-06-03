package com.arioval.roulette.controllers;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api")
public class RouletteController {



	@PostMapping(value = "/roulette", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> createRoulette(@RequestBody Ruleta ruleta) {
		ruletaService.crearRuleta(roulette);
		return new ResponseEntity<>(roulette.getId(),HttpStatus.CREATED);
	}

}