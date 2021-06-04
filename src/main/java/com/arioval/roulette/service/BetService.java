package com.arioval.roulette.service;

import java.util.List;
import java.util.Map;

import com.arioval.roulette.models.BetModel;


public interface BetService {
	
    Map<String, BetModel> getBets();
    BetModel getBetById(String id);
    String createBet(BetModel bet);
    void deleteBet(String id);
	void updateBet(BetModel roulette, String id);
	List<BetModel> closeBet(String rouletteId);
	String makeBet(BetModel bet);
}
