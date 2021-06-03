package com.arioval.roulette.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RouletteModel implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private List<BetModel> bets = new ArrayList<>();
	
	public RouletteModel() {
		
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<BetModel> getBets() {
		return bets;
	}

	public void setBets(List<BetModel> bets) {
		this.bets = bets;
	}

	@Override
    public String toString() {
        return "Ruleta [id=" + id + ", estado=" + status + "]";
    }
}
