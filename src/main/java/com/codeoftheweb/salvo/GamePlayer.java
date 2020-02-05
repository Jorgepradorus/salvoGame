package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String creationDate;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_Id")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Ship> ships = new HashSet<>();

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Salvo> salvoes = new HashSet<>();


    public GamePlayer(){
    }

    public GamePlayer(Player player, Game game){

        this.creationDate = creationDate;
        this.player = player;
        this.game = game;
    }


    //getters
    public int getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }


    public Player getPlayer() {
        return player;
    }
    @JsonIgnore
    public Game getGames() {
        return game;
    }

    @JsonIgnore
    public Set<Ship> getShip() {
        return ships;
    }

    @JsonIgnore
    public Set<Salvo> getSalvo() {
        return salvoes;
    }


    //Setters

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setPlayers(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }



    //Lo que hago es coger las puntuaciones de un jugador, para cada uno cojo las puntuaciones de todos los juegos y veo la que es igual.
    public Score getScore(){
        return this.player.getScore()
                .stream()
                //Flatmap lo que va a hacer es producir varios valores en vez de uno solo como el map. //TODO: Buscar mas informacion sobre esto.
                .flatMap(pScore -> game.getScore()
                        .stream()
                        .filter(gScore -> gScore.getScore_Id() == pScore.getScore_Id()))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Object> toDTOGameView() {
        return new LinkedHashMap<String, Object>(){{
            put("id", getId());
            put("player", getPlayer());
            put("score", getScore());
        }};
    }





}
