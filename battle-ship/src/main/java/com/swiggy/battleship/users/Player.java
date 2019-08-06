package com.swiggy.battleship.users;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {

    private String playerName;
    @Setter
    private Account accountDetails;
    private String playerId;

    public Player(final String playerName, final String playerId, final Account accountDetails) {
        this.playerName=playerName;
        this.playerId=playerId;
        this.accountDetails=accountDetails;
    }
    public Player(final String playerName, final String playerId) {
        this.playerName=playerName;
        this.playerId=playerId;
    }
}
