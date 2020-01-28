package com.codeoftheweb.salvo;

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

    private int player_Id;
    private String userName;
    private String email;


    public Player(){
    }

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers= new HashSet<>();


    public Player(int player_Id, String userName, String email){
        this.player_Id = player_Id;
        this.userName = userName;
        this.email = email;
    }

    public int getPlayer_Id() { return player_Id; }

    public String getUserName() {
        return userName;
    }

    public String getEmail() { return email; }

    public void setPlayer_Id(int player_Id) {
        this.player_Id = player_Id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
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
