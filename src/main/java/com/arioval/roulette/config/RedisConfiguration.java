package com.arioval.roulette.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.arioval.roulette.models.*;


@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
	@Bean
    public LettuceConnectionFactory redisConnectionFactory(PropertiesRedis redisProperties) {
        return new LettuceConnectionFactory(redisProperties.getRedisHost(), redisProperties.getRedisPort());
    }
	
	@Bean
	public RedisTemplate<String, RouletteModel> redisTemplate_Ruleta(LettuceConnectionFactory connectionFactory) {
		final RedisTemplate<String, RouletteModel> redisTemplate = new RedisTemplate<>();
		redisTemplate .setConnectionFactory(connectionFactory);
		return redisTemplate;
	}
	
	@Bean
	public RedisTemplate<String, BetModel> redisTemplate_Apuesta(LettuceConnectionFactory connectionFactory) {
		final RedisTemplate<String, BetModel> redisTemplate = new RedisTemplate<>();
		redisTemplate .setConnectionFactory(connectionFactory);
		return redisTemplate;
	}
}