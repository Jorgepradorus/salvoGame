package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class AppController {


    @Autowired
    private GameRepository gameRepository;
    @GetMapping("/games")

    public List getGames() {
        return gameRepository.findAll().stream().map(Game -> makeGameDTO(Game)).collect(Collectors.toList());
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getGame_Id());
        dto.put("date", game.getCreationDate());
        dto.put("gamePlayer", game.getGamePlayers());
        return dto;
    }


    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/players")

    public List getPlayers() {
        return playerRepository.findAll().stream().map(Player -> makePlayerDTO(Player)).collect(Collectors.toList());
    }

    private Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("player_id", player.getPlayer_Id());
        dto.put("userName", player.getUserName());
        dto.put("email", player.getEmail());
        return dto;
    }

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/gameplayers")
    public List getGamePlayers(){
        return gamePlayerRepository.findAll().stream().map(GamePlayer -> makeGamePlayerDTO(GamePlayer)).collect(Collectors.toList());
    }

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("player", gamePlayer.getPlayer());
        dto.put("game", gamePlayer.getGames());
        return dto;

    }


    @Autowired
    private ShipRepository shipRepository;

    @RequestMapping("/ships")
    public List getShips() {
        return shipRepository.findAll().stream().map(Ship -> makeShipDTO(Ship)).collect(Collectors.toList());
    }

    private Map<String, Object> makeShipDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", ship.getType());
        dto.put("location", ship.getLocations());

        return dto;
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public List gameView (@PathVariable Long gamePlayerId) {
        System.out.println(gamePlayerId);
        System.out.println(gameRepository.findAll());
        List <Game> todosJuegos = gameRepository.findAll();

        System.out.println("ADIOS");
        for(int i = 0; i < todosJuegos.size(); i++){
            System.out.println("HOLA");
            System.out.println(todosJuegos.get(i).getCreationDate());
        }
        return gameRepository.findAll().stream().filter(g -> g.gamePlayerExist(gamePlayerId).isPresent()).map(game -> new LinkedHashMap<String, Object>(){{
            put("id", game.getGame_Id());
            put("created", game.getCreationDate());
            put("gameplayers", game.getGamePlayers().stream());
            put("ships", game.getGamePlayers().stream().filter(gp -> gp.getId() == gamePlayerId).findAny().get().getShip());
            put("salvoes", game.getGamePlayers().stream().map(gp -> new LinkedHashMap<String, Object>(){{
                put("playerId", gp.getPlayer().getPlayer_Id());
                put("playerEmail", gp.getPlayer().getEmail());
                put("salvos", gp.getSalvo());
            }}));
            //put("salvoes", game.getGamePlayers().stream().findAny().get().getSalvo());


        }}).collect(Collectors.toList());

    }
}
