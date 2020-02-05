package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name= "native", strategy= "native")

    private long player_Id;
    private String userName;
    private String email;



    public Player(){

    }

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers= new HashSet<>();

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<Score> score= new HashSet<>();


    public Player(String userName, String email){
        this.userName = userName;
        this.email = email;

    }

    public long getPlayer_Id() { return player_Id; }

    public String getUserName() {
        return userName;
    }

    public String getEmail() { return email; }


    public Set<Score> getScore() {
        return score;
    }

    //Setters
    public void setPlayer_Id(int player_Id) {
        this.player_Id = player_Id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Id=" + player_Id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
