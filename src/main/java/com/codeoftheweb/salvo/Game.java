package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Game {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private long game_Id;
        private String creationDate;


    public Game(){
    }

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayers.add(gamePlayer);
    }

    public Game(long game_Id, String creationDate){
        this.game_Id = game_Id;
        this.creationDate = creationDate;
    }

    public long getGame_Id() {
        return game_Id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.game_Id = game_Id;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Optional<GamePlayer> gamePlayerExist (long gameplayerId){
        return gamePlayers.stream().filter(gp -> gp.getId() == gameplayerId).findAny();
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + game_Id +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
