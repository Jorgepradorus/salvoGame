package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")
    private long id;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column( name = "location")
    private List<String> locations = new ArrayList<>();

    public Ship(){
    }



    public Ship(String type, List<String>locations, GamePlayer gamePlayer){
        this.type = type;
        this.locations = locations;
        this.gamePlayer = gamePlayer;
    }

    //getters


    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }



    public List<String> getLocations() {
        return locations;
    }

    //setters


    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Ship{" +
                ", type='" + type + '\'' +
                ", game='" + gamePlayer+ '\'' +
                '}';

    }

}