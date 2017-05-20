package game.player;

import mvc.GameController;

public abstract class Player {

    GameController controller;

    public abstract boolean makeMove();

}
