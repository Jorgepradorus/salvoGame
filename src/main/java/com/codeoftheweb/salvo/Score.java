package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Score {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long score_Id;
    private double scoreGame;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_Id")
    private Game game;


 public Score(){
    }

    public Score( double scoreGame, Player player, Game game){
     //this.score_Id = score_Id;
     this.scoreGame = scoreGame;
     this.player = player;
     this.game = game;
    }


    //Getters
    public long getScore_Id() {
        return score_Id;
    }
    public double getScoreGame() {
        return scoreGame;
    }

    @JsonIgnore
    public Game getGame() { return game; }
    @JsonIgnore
    public Player getPlayer() { return player; }


    //Setters
    public void setScore_Id(long score_Id) {
        this.score_Id = score_Id;
    }
    public void setScoreGame(double scoreGame) {
        this.scoreGame = scoreGame;
    }

    public void setGame(Game game) { this.game = game; }

    public void setPlayer(Player player) { this.player = player; }

    @Override
    public String toString() {
        return "Score{" +
                "score_Id=" + score_Id +
                ", scoreGame=" + scoreGame +
                '}';
    }
}