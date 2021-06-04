package com.arioval.roulette.models;

import java.io.Serializable;

public class BetModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String 	id;
	private String 	rouletteId;
	private int 	betNum;
	private String 	betColor;
	private Double 	betAmount;
	
	public BetModel() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRouletId() {
		return rouletteId;
	}
	public void setRouletteId(String rouletteId) {
		this.rouletteId = rouletteId;
	}
	public int getBetNum() {
		return betNum;
	}
	public void setBetNum(int betNum) {
		this.betNum = betNum;
	}
	public String getBetColor() {
		return betColor;
	}
	public void setBetColor(String betColor) {
		this.betColor = betColor;
	}
	public Double getBetAmount() {
		return betAmount;
	}
	public void setbetAmount(Double betAmount) {
		this.betAmount = betAmount;
	}
	@Override
	public String toString() {
		return  "Apuesta [id=" + id + ", idRuleta=" + rouletteId + ", numeroApuesta=" + betNum +
				", colorApuesta=" + betColor + ", valorApuesta=" + betAmount +"]";
	}
	
}
