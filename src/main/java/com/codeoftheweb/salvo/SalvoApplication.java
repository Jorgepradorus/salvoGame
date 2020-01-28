package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}


	//Get current date time
	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	String formatDateTime = now.format(formatter);

	//Creando Jugadores
	Player player1 = new Player(1,"Jorge", "jorgepradorus@gmail.com");
	Player player2 = new Player(2,"Adrian", "adrian@gmail.com");

//Creando Juegos
	Game game1 = new Game(1,formatDateTime);
	Game game2 = new Game(2,now.plusHours(1).format(formatter));
	Game game3 = new Game(3,now.plusHours(2).format(formatter));

	//Creando Gameplayers
	GamePlayer gamePlayer1 = new GamePlayer(player1, game1);
	GamePlayer gamePlayer2 = new GamePlayer(player2, game1);
	GamePlayer gamePlayer3 = new GamePlayer(player1, game2);
	GamePlayer gamePlayer4 = new GamePlayer(player2, game3);

	Ship ship1 = new Ship("destroyer", Arrays.asList("A1","A2","A3"), gamePlayer1);
	Ship ship2 = new Ship( "aircraft carrier", Arrays.asList("B2","B3","B4","B5"), gamePlayer1);
	Ship ship5 = new Ship( "submarine", Arrays.asList("D7", "E7", "F7"),gamePlayer1);
	Ship ship3 = new Ship( "submarine", Arrays.asList("B1","C1","D1"),gamePlayer2);
	Ship ship4 = new Ship( "boat", Arrays.asList("C2"),gamePlayer2);

	//Creando salvos
Salvo salvo1 = new Salvo(1, gamePlayer1, Arrays.asList("RB2","RB3"));
Salvo salvo2 = new Salvo(2, gamePlayer1, Arrays.asList("RB4", "RB6"));
Salvo salvo3 = new Salvo(3, gamePlayer1, Arrays.asList("RC4","RC6"));
Salvo salvo4 = new Salvo(1, gamePlayer2, Arrays.asList("B2","B3"));
Salvo salvo5 = new Salvo(2, gamePlayer2, Arrays.asList("C7","C8"));
Salvo salvo6 = new Salvo(3, gamePlayer2, Arrays.asList("D7","D8"));
Salvo salvo7 = new Salvo(4, gamePlayer1, Arrays.asList("RF5", "RF6"));


	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository repositoryGamePlayers, ShipRepository shipRepository, SalvoRepository salvoRepository) {
		return (arg) -> {

			playerRepository.save(player1);
			playerRepository.save(player2);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);



			repositoryGamePlayers.save(gamePlayer1);
			repositoryGamePlayers.save(gamePlayer2);
			repositoryGamePlayers.save(gamePlayer3);
			repositoryGamePlayers.save(gamePlayer4);


			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);

			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);
			salvoRepository.save(salvo7);

		};
	}


}

