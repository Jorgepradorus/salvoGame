package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
        private Integer salvo_Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_Id")
        private GamePlayer gamePlayer;
        private Integer turn_number;
     @ElementCollection
     @Column( name = "salvoLocation")
        private List<String> locations = new ArrayList<>();


    //Constructor
    public Salvo(){
    }

    public Salvo(Integer turn_number, GamePlayer gamePlayer,  List<String> locations){

        this.gamePlayer = gamePlayer;
        this.turn_number = turn_number;
        this.locations = locations;
    }

    //getters

    public Integer getId() {
        return salvo_Id;
    }
    public Integer getTurn_number() {
        return turn_number;
    }
    @JsonIgnore
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public List<String> getLocations() {
        return locations;
    }
    //Setters

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public void setTurn_number(Integer turn_number) {
        this.turn_number = turn_number;
    }

    @Override
    public String toString() {
        return "Salvo{" +
                "Id=" + salvo_Id +
                ", gamePlayer=" + gamePlayer +
                ", turn_number=" + turn_number +
                ", locations=" + locations +
                '}';
    }
}

