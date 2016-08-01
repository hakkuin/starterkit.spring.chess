package com.capgemini.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.capgemini.chess.aop.IdNumberingAdvisor;

@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
public class ChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}
	
    @Bean
    public IdNumberingAdvisor idNumberingAdvisor() {
        return new IdNumberingAdvisor();
    }
}
