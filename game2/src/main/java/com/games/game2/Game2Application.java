package com.games.game2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.games.game2.mapper"})
@SpringBootApplication
public class Game2Application {

	public static void main(String[] args) {
		SpringApplication.run(Game2Application.class, args);
	}

}
