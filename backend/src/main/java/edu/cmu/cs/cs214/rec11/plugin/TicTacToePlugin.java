package edu.cmu.cs.cs214.rec11.plugin;

import edu.cmu.cs.cs214.rec11.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec11.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec11.games.TicTacToe;

public class TicTacToePlugin implements GamePlugin<String> {

    private static final String GAME_NAME = "Tic Tac Toe";

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;

    private static final String PLAYER1_WON_MSG = "Player 1 won!";
    private static final String PLAYER2_WON_MSG = "Player 2 won!";
    private static final String GAME_TIED_MSG = "The game ended in a tie.";

    private static final String GAME_START_FOOTER = "";

    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        framework.setFooterText(GAME_START_FOOTER);
    }

    @Override
    public void onNewMove() { } // Nothing to do here.

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return true; // TicTacToe is a turn-based game
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.currentPlayer().toString());
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        if (game.winner() == TicTacToe.Player.X) {
            return PLAYER1_WON_MSG;
        } else if (game.winner() == TicTacToe.Player.O) {
            return PLAYER2_WON_MSG;
        } else {
            return GAME_TIED_MSG;
        }
    }

    @Override
    public String currentPlayer() {
        if (game.currentPlayer() == TicTacToe.Player.X) {
            return "Player 1";
        } else {
            return "Player 2";
        }
    }

    @Override
    public void onGameClosed() { } // Nothing to do here.
}
