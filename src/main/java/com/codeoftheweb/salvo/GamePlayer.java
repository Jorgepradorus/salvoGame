package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
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

      //  this.creationDate = creationDate;
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



    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setPlayers(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
