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
	public ResponseEntity<List<WinnerModel>> closeRoulette(@PathVariable String RouletteId) {
    	WinnerModel winner = new WinnerModel();
    	String colorWinner = null;
    	int MAX = 36;
    	int MIN = 0;
    	int numberWinner = (int)(Math.random() * (MAX - MIN + 1)+ MIN);
    	
    	if(numberWinner % 2 == 0) {
    		colorWinner = "rojo";
    	} else {
    		colorWinner = "negro";
    	}
    	System.out.println("Ganador " + colorWinner + " " + numberWinner);
    	
    	List<BetModel> listBet = betService.closeBet(RouletteId);
    	List<WinnerModel> listWinner = new ArrayList<>();
    	
    	if(listBet == null) {
    		return ResponseEntity.ok(listWinner);
    	}
    	
    	for(BetModel bet : listBet) {
    		String colorBet = bet.getBetColor();
    		int numBet = bet.getBetNum();
    		Double AmountWin = 0.0;
    		if (colorWinner.equalsIgnoreCase(colorBet)) {
    			AmountWin += bet.getBetAmount() * 1.8;
    		} else if (numberWinner == numBet && colorBet == null) {
    			AmountWin += bet.getBetAmount() * 5;
    		}
    		if (AmountWin != 0.0) {
    			winner.setWinAmount(AmountWin);
    			winner.setUserId(bet.getUserId());
    			winner.setRouletteId(bet.getRouletId());
    			listWinner.add(winner);
    		}
    	}
    	return ResponseEntity.ok(listWinner);
	}
	
    @PostMapping(value = "/bet", consumes = "application/json", produces = "application/json")
    public String makeBet(@RequestBody BetModel bet) {
    	return betService.makeBet(bet);
	}
	
}
