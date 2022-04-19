package test.java.game;

import main.java.game.GameManager;
import main.java.game.model.Game;
import main.java.game.model.GameTurn;
import main.java.game.model.Player;
import main.java.game.model.PlayerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {
    GameManager gameManager = new GameManager();
    Game game;
    Player playerX;
    Player playerO;

    @BeforeEach
    public void setUp(){
        playerX = new Player("Test 1", PlayerType.X);
        playerO = new Player("Test 2", PlayerType.O);
        game = new Game(playerX,playerO);
        gameManager.createScoreRelation();
    }

    @Test
    public void should_Return_False_Short_Input() {
        String userInput = "A";
        boolean evaluationResult = gameManager.evaluateCoordinates(game, userInput);
        assertEquals(false, evaluationResult);
    }

    @Test
    public void should_Return_False_Wrong_First_Char() {
        String userInput = "R1";
        boolean evaluationResult = gameManager.evaluateCoordinates(game, userInput);
        assertEquals(false, evaluationResult);
    }

    @Test
    public void should_Return_False_Wrong_Second_Char() {
        String userInput = "A5";
        boolean evaluationResult = gameManager.evaluateCoordinates(game, userInput);
        assertEquals(false, evaluationResult);
    }

    @Test
    public void should_Return_True_Valid_Input() {
        String userInput = "A1";
        boolean evaluationResult = gameManager.evaluateCoordinates(game, userInput);
        assertEquals(true, evaluationResult);
    }

    @Test
    public void should_Return_False_Few_Turns(){
        game.getTurnList().add(new GameTurn(playerX,"A","1"));
        game.getTurnList().add(new GameTurn(playerO,"A","2"));
        game.getTurnList().add(new GameTurn(playerX,"A","3"));
        boolean evaluationResult = gameManager.evaluateTurn(game);
        assertEquals(false,evaluationResult);
    }

    @Test
    public void should_Return_False_No_Winner_Turn(){
        game.getTurnList().add(new GameTurn(playerX,"B","1"));
        game.getTurnList().add(new GameTurn(playerO,"A","2"));
        game.getTurnList().add(new GameTurn(playerX,"B","2"));
        game.getTurnList().add(new GameTurn(playerO,"A","1"));
        game.getTurnList().add(new GameTurn(playerX,"C","1"));
        game.getTurnList().add(new GameTurn(playerO,"C","2"));
        boolean evaluationResult = gameManager.evaluateTurn(game);
        assertEquals(false,evaluationResult);
    }

    @Test
    public void should_Return_True_Winner_Turn(){
        GameTurn turn1 = new GameTurn(playerX,"B","1");
        game.getTurnList().add(turn1);
        gameManager.recordGameTurn(game,turn1);

        GameTurn turn2 = new GameTurn(playerO,"A","1");
        game.getTurnList().add(turn2);
        gameManager.recordGameTurn(game,turn2);

        GameTurn turn3 = new GameTurn(playerX,"B","2");
        game.getTurnList().add(turn3);
        gameManager.recordGameTurn(game,turn3);

        GameTurn turn4 = new GameTurn(playerO,"A","2");
        game.getTurnList().add(turn4);
        gameManager.recordGameTurn(game,turn4);

        GameTurn turn5 = new GameTurn(playerX,"B","3");
        game.getTurnList().add(turn5);
        gameManager.recordGameTurn(game,turn5);

        game.setCurrentPlayer(playerX);
        boolean evaluationResult = gameManager.evaluateTurn(game);
        assertEquals(true,evaluationResult);
    }

}