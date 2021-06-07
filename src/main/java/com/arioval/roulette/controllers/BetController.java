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

import com.arioval.roulette.common.Common;
import com.arioval.roulette.models.*;
import com.arioval.roulette.service.*;

@RestController
@RequestMapping("/api")
public class BetController {

	@Autowired
	private BetServiceImp betService;
	
    @PutMapping("/bet/close/{RouletteId}")
	public ResponseEntity<List<WinnerModel>> closeRoulette(@PathVariable String RouletteId) {    	
    	List<BetModel> listBet = betService.closeBet(RouletteId);
    	List<WinnerModel> listWinner = new ArrayList<>();	

    	if(listBet == null) {
    		return ResponseEntity.ok(listWinner);
    	}
    	listWinner = Common.getWinners(listBet);
    	
    	return ResponseEntity.ok(listWinner);
	}
	
    @PostMapping(value = "/bet", consumes = "application/json", produces = "application/json")
    public String makeBet(@RequestBody BetModel bet) {
    	return betService.makeBet(bet);
	}
	
}
