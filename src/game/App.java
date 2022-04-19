package game;

import java.util.Scanner;

public class App {

    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        GameManager newGame = new GameManager();

        System.out.println("Welcome Players!!");
        System.out.println("This is the Old-Woman game.model.Game");
        System.out.println("");
        System.out.print("Do you wanna start a new game? [Y | N] : ");
        String userAnswer = userInput.nextLine();

        if (userAnswer.equals("Y")) newGame.startGame();
        else endProgram();

    }

    private static void endProgram() {
        System.out.println("");
        System.out.println("Bye-bye then! See you!");
    }
}
