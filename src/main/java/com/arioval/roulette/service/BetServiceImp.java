package com.arioval.roulette.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.arioval.roulette.models.*;


@Service
public class BetServiceImp implements BetService{

	@Autowired
	private RedisTemplate<String, BetModel> betRedisTemplate;
	
	@Autowired
	private RouletteServiceImp rouletteService;

	private static final String KEY_BET= "Bet";
	private RouletteModel roulette = null;
	private HashOperations<String, String, BetModel> operations;

	public BetServiceImp() {
	}

	@PostConstruct
	private void ini() {
		operations = betRedisTemplate.opsForHash();
	}

	@Override
	public Map<String, BetModel> getBets() {
		return getOperations().entries(KEY_BET);
	}

	@Override
	public BetModel getBetById(String id) {
		return (BetModel) getOperations().get(KEY_BET, id);
	}

	@Override
	public String createBet(BetModel bet) {
		bet.setId( UUID.randomUUID().toString());
		getOperations().put(KEY_BET, bet.getId(), bet);
		return bet.getId();
	}

	@Override
	public void deleteBet(String id) {
		getOperations().delete(KEY_BET, id);
	}

	@Override
	public void updateBet(BetModel bet, String id) {
		operations.put(KEY_BET, bet.getId(), bet);
	}
	
	@Override
	public List<BetModel> closeBet(String rouletteId) {
		
		roulette = rouletteService.getRouletteById(rouletteId);
		
		if(roulette == null) {
			return null;
		}
		
		if(roulette.getStatus().equalsIgnoreCase(StatusRoulette.OPEN)){
			
			roulette.setStatus(StatusRoulette.CLOSE);
	    	rouletteService.updateRoulette(roulette, rouletteId);
	    	
			return roulette.getBets();
		}
		else {
			return null;
		}
	}
	
	@Override
	public String makeBet(BetModel bet) {
		
		roulette = rouletteService.getRouletteById(bet.getRouletId());
		
		if(roulette == null)
			return "No existe la ruleta con id: "+bet.getRouletId();

		if(roulette.getStatus().equalsIgnoreCase(StatusRoulette.OPEN)){
			
			if(bet.getBetNum() < 0 || bet.getBetNum() > 36) {
				return "Número de apuesta no valido";
			}
			
			if(bet.getBetAmount() < 0 || bet.getBetAmount() > 10000.0)
				return "Valor de apuesta no permitido";
			
			this.createBet(bet);
			rouletteService.addBetRoulette(roulette, bet);
			rouletteService.updateRoulette(roulette, roulette.getId());

			return "Apuesta realizada con éxito";
		}
		else {
			return "La ruleta con id: "+bet.getRouletId()+" no esta abierta";
		}
	}

	public HashOperations<String, String, BetModel> getOperations() {
		return operations;
	}
}