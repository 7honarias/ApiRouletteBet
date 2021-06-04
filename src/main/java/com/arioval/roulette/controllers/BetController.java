package com.arioval.roulette.controllers;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arioval.roulette.models.*;
import com.arioval.roulette.service.*;

@RestController
@RequestMapping("/api")
public class BetController {

	@Autowired
	private BetServiceImp betService;
		
    @PutMapping("/bet/close/{RouletteId}")
	public ResponseEntity<List<String>> closeRoulette(@PathVariable String RouletteId) {
    	int MAX = 36;
    	int MIN = 0;
    	int numberWinner = (int)(Math.random() * (MAX - MIN + 1)+ MIN);
    	String colorWinner = null;
    	String winner;
    	
    	if(numberWinner % 2 == 0) {
    		colorWinner = "rojo";
    	} else {
    		colorWinner = "Negro";
    	}
    	
    	List<BetModel> listBet = betService.closeBet(RouletteId);
    	List<String> listWinner = new ArrayList<>(); 
    	
    	for(BetModel bet : listBet) {
    		String colorBet = bet.getBetColor();
    		int numBet = bet.getBetNum();
    		Double AmountWin = 0.0;
    		if (colorWinner == colorBet) {
    			AmountWin += bet.getBetAmount() * 1.8;
    		}
    		if (numberWinner == numBet) {
    			AmountWin += bet.getBetAmount() * 5;
    		}
    		if (AmountWin != 0.0) {
    			winner = "Ganador " + bet.getUserId() + AmountWin;
    			listWinner.add(winner);
    		}
    	}
    	winner = "Numero ganador " + numberWinner + " " + colorWinner;
    	listWinner.add(winner);
    	if (listWinner.size() > 0) {
    		return ResponseEntity.ok(listWinner);
    	}
    	else {
    		return ResponseEntity.noContent().build();
    	}
	}
	
    @PostMapping(value = "/bet", consumes = "application/json", produces = "application/json")
    public String makeBet(@RequestBody BetModel bet) {
    	return betService.makeBet(bet);
	}
	
}
