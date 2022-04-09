import java.util.Scanner;

public class GameManager {

    static Scanner userInput = new Scanner(System.in);

    public void showGameStatus(Game game) {
        String canvas = Character.toString(183);
        String canvasLine = "";
        for(int i = 0;i<18;i++) {
            canvasLine = canvasLine + canvas;
        }


        System.out.println();
        System.out.println("The player in charge is " + game.getCurrentPlayer().getName());
        System.out.println();
        System.out.println(canvas + canvas + canvas + "   A   B   C  "  + canvas);
        System.out.println(canvasLine);
        System.out.println(" 1 " + canvas + "    |   |    " + canvas);
        System.out.println("------------------");
        System.out.println(" 2 " + canvas + "    |   |    " + canvas);
        System.out.println("------------------");
        System.out.println(" 3 " + canvas + "    |   |    " + canvas);
        System.out.println(canvasLine);
        System.out.println();
    }

    public void startGame(){
        System.out.println();
        System.out.println("A new game is going do start!!!");
        System.out.println();
        System.out.print("Type the name of the X player: ");
        String xPlayerName = userInput.nextLine();
        System.out.print("Type the name of the O player: ");
        String oPlayerName = userInput.nextLine();

        Player xPlayer = new Player(xPlayerName,PlayerType.X);
        Player oPlayer = new Player(oPlayerName,PlayerType.O);

        Game game = new Game(xPlayer,oPlayer);
        game.setCurrentPlayer(xPlayer);
        executeGameTurn(game);
    }

    private void executeGameTurn(Game game){
        showGameStatus(game);
        String turnCoordinates = getTurnCoordinates();
        Player newPlayer;
        if (game.getCurrentPlayer().getType() == PlayerType.O) newPlayer = game.getPlayerX();
        else newPlayer = game.getPlayerO();
        GameTurn gameTurn = new GameTurn(newPlayer,turnCoordinates.substring(0,1),turnCoordinates.substring(1));
        game.getTurnList().add(gameTurn);
        game.setCurrentPlayer(gameTurn.getPlayer());
        //game.set
        evaluateTurn(game);
    }

    private void evaluateTurn(Game game){
        if (!game.getFinished()) executeGameTurn(game);
        else finishGame();
    }

    private void finishGame(){
        System.out.println();
        System.out.println("Game Over");
    }

    private String getTurnCoordinates(){
        System.out.print("Type the coordinates of your choice:");
        return userInput.nextLine();
    }



}
