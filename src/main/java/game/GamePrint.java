package main.java.game;

import main.java.game.model.Game;
import main.java.game.model.GameResult;
import main.java.game.model.Score;

import java.util.Iterator;

public class GamePrint {

    public void showGameStatus(Game game) {
        String canvas = Character.toString(183);
        String canvasLine = "";
        for(int i = 0;i<18;i++) {
            canvasLine = canvasLine + canvas;
        }


        System.out.println();
        if (game.getResult().equals(GameResult.ON)) System.out.println("The player in charge is " + game.getCurrentPlayer().getName() + " - " + game.getCurrentPlayer().getType());
        System.out.println();
        System.out.println("      A   B   C  ");

        String lineToPrint;
        Score tempScore;
        for(int c = 1;c<4;c++){
            int finalC = c;
            lineToPrint = " " + finalC + " " + canvas + "  ";
            Iterator<Score> line = game.getScore().stream().filter(score -> score.getPos().contains(Integer.toString(finalC))).iterator();
            tempScore = line.next();
            lineToPrint = lineToPrint + (tempScore.getType() != null ? tempScore.getType() : " ");

            tempScore = line.next();
            lineToPrint = lineToPrint + " | " + (tempScore.getType() != null ? tempScore.getType() : " ");
            tempScore = line.next();
            lineToPrint = lineToPrint + " | " + (tempScore.getType() != null ? tempScore.getType() : " ") + " " + canvas;


            System.out.println(lineToPrint);
            if (c!=3) System.out.println("     -----------");
        }

        System.out.println();
    }

    public void showWinner(Game game){
        System.out.println();
        System.out.println("THE GAME HAS A WINNER!!!");
        System.out.println();
        System.out.println("The winner is " + game.getWinner().getName() + "! Congratulations!");
    }

    public void showGameDraw(){
        System.out.println();
        System.out.println("The main.java.game has NO winner!");
    }

    public void showGameOver(Game game){
        System.out.println();
        System.out.println("Game Over. Bye-bye players " + game.getPlayerX().getName() + " and " + game.getPlayerO().getName() + ".");
    }

}
