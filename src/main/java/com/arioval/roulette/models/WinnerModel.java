package com.arioval.roulette.models;

public class WinnerModel {
	private String 	rouletteId;
	private String  userId;
	private Double 	winAmount;
	
	public WinnerModel() {
		
	}
	
	public String getRouletId() {
		return rouletteId;
	}
	public void setRouletteId(String rouletteId) {
		this.rouletteId = rouletteId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Double getWinAmount() {
		return winAmount;
	}
	public void setWinAmount(Double winAmount) {
		this.winAmount = winAmount;
	}
	@Override
	public String toString() {
		return  "Usuario [id=" + userId + ", id Ruleta=" + rouletteId + ", valor ganado=" + winAmount +"]";
	}
	
}