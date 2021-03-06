package com.arioval.roulette.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.arioval.roulette.common.Common;
import com.arioval.roulette.models.*;


@Service
public class RouletteServiceImp implements RouletteService{

	@Autowired
	private RedisTemplate<String, RouletteModel> rouletteRedisTemplate;
	private HashOperations<String, String, RouletteModel> operations;
	
	public RouletteServiceImp() {
	}
	
	@PostConstruct
	private void ini() {
		operations = rouletteRedisTemplate.opsForHash();
	}
	
	@Override
	public Map<String, RouletteModel> getRoulettes() {
		return getOperations().entries(Common.KEY_ROULETTE);
	}
	
	@Override
	public RouletteModel getRouletteById(String id) {
		return (RouletteModel) getOperations().get(Common.KEY_ROULETTE, id);
	}
	
	@Override
	public String createRoulette(RouletteModel roulette) {
		roulette.setId( UUID.randomUUID().toString());
		roulette.setStatus(StatusRoulette.CREATE);
		getOperations().put(Common.KEY_ROULETTE, roulette.getId(), roulette);
		
		return roulette.getId();
	}
	
	@Override
	public void deleteRoulette(String id) {
		getOperations().delete(Common.KEY_ROULETTE, id);
	}
	
	@Override
	public void updateRoulette(RouletteModel roulette, String id) {
		operations.put(Common.KEY_ROULETTE, roulette.getId(), roulette);
	}
	
	@Override
	public void addBetRoulette(RouletteModel roulette, BetModel bet) {
		roulette.getBets().add(bet);
	}
	
	@Override
	public Map<String, String> getRoulettesWithStatus() {
		Map<String, String> rouletteWithStatus = new HashMap<>();
		Map<String, RouletteModel> roulettes = this.getRoulettes();
		
		for (RouletteModel roulette : roulettes.values()) { 
			rouletteWithStatus.put(roulette.getId(), roulette.getStatus());
		}
		return rouletteWithStatus;
	}
	
	@Override
	public String openRoulette(String id) {	
		RouletteModel roulette = this.getRouletteById(id);
		
		if(roulette == null) {
			return "Ruleta no existe";
		}
		if(roulette.getStatus().equalsIgnoreCase(StatusRoulette.CREATE)) {
			roulette.setStatus(StatusRoulette.OPEN);
			this.updateRoulette(roulette, id);
			
			return "Abierta exitosamente";
		}
		else {
			return "Esta ruleta ya cerro";
		}
	}
	
	public HashOperations<String, String, RouletteModel> getOperations() {
		return operations;
	}
	
}