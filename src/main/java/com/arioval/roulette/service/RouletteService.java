package com.arioval.roulette.service;

import java.util.Map;

import com.arioval.roulette.models.BetModel;
import com.arioval.roulette.models.RouletteModel;


public interface RouletteService {
	
    Map<String, RouletteModel> getRoulettes();
    RouletteModel getRouletteById(String id);
    String createRoulette(RouletteModel roulette);
    void deleteRoulette(String id);
    void updateRoulette(RouletteModel roulette, String id);
    void addBetRoulette(RouletteModel roulette, BetModel bet);
	String openRoulette(String id);
	Map<String, String> getRoulettesWithStatus();
	
}