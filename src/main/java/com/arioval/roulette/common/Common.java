package com.arioval.roulette.common;

import java.util.ArrayList;
import java.util.List;

import com.arioval.roulette.models.BetModel;
import com.arioval.roulette.models.WinnerModel;

public class Common {
	public static final int NUM_MAX = 36;
	public static final int NUM_MIN = 0;
	public static final String RED = "rojo";
	public static final String BLACK = "negro";
	public static final String KEY_BET= "Bet";
	public static final String KEY_ROULETTE = "Roulette";
	
	
	
	public static List<WinnerModel> getWinners(List<BetModel> listBet) {
		List<WinnerModel> listWinner = new ArrayList<>();
		int numberWinner = (int)(Math.random() * (Common.NUM_MAX - Common.NUM_MIN + 1)+ Common.NUM_MIN);
		String colorWinner = null;
		WinnerModel winner = new WinnerModel();
		
		if(numberWinner % 2 == 0) {
    		colorWinner = Common.RED;
    	} else {
    		colorWinner = Common.BLACK;
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
		return listWinner;
	}

}
