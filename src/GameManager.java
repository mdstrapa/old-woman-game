import java.util.Iterator;
import java.util.Optional;
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
        System.out.println("The player in charge is " + game.getCurrentPlayer().getName() + " - " + game.getCurrentPlayer().getType());
        System.out.println();
        System.out.println(canvas + canvas + canvas + "   A   B   C "  + canvas);
        //System.out.println();

        String lineToPrint;
        Score tempScore;
        for(int c = 1;c<4;c++){
            Integer finalC = c;
            lineToPrint = " " + finalC + " " + canvas + "  ";
            Iterator<Score> line = game.getScore().stream().filter(score -> score.getPos().contains(finalC.toString())).iterator();
            tempScore = line.next();
            lineToPrint = lineToPrint + (tempScore.getType() != null ? tempScore.getType() : " ");

            tempScore = line.next();
            lineToPrint = lineToPrint + " | " + (tempScore.getType() != null ? tempScore.getType() : " ");
            tempScore = line.next();
            lineToPrint = lineToPrint + " | " + (tempScore.getType() != null ? tempScore.getType() : " ") + " " + canvas;


            System.out.println(lineToPrint);
            if (c!=3) System.out.println("------------------");
        }

        //System.out.println(canvasLine);
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
        GameTurn gameTurn = new GameTurn(game.getCurrentPlayer(),turnCoordinates.substring(0,1),turnCoordinates.substring(1));
        game.getTurnList().add(gameTurn);
        recordGameTurn(game,gameTurn);
        if (!evaluateTurn(game)){
            Player newPlayer;
            if (game.getCurrentPlayer().getType() == PlayerType.O) newPlayer = game.getPlayerX();
            else newPlayer = game.getPlayerO();
            game.setCurrentPlayer(newPlayer);
            executeGameTurn(game);
        } else finishGame();
    }

    private void recordGameTurn(Game game,GameTurn gameTurn){

        PlayerType playerType = gameTurn.getPlayer().getType();
        String column = gameTurn.getColumn();
        String line = gameTurn.getLine();

        Optional<Score> score = game.getScore().stream().filter(s -> (column + line).equals(s.getPos())).findAny();

        if(score.isPresent()) score.get().setType(playerType);

    }

    private boolean evaluateTurn(Game game){

        //antes da 5ª jogada nem avalia pq nao tem como ganhar
        if(game.getTurnList().size() < 5) return false;

        //pega a ultima jogada
        GameTurn lastTurn = game.getTurnList().get(game.getTurnList().size() - 1);

        //procura por player type do mesmo tipo relacionada a ultima jogada
        PlayerType lastType = lastTurn.getPlayer().getType();

        if(lastTurn.getColumn().equals("A") && lastTurn.getLine().equals("1")){ //A1
            //verificando as possíveis combinações de ganho:
            Optional<Score> a2 = game.getScore().stream().filter(score -> score.getPos().equals("A2")).findFirst();
            Optional<Score> a3 = game.getScore().stream().filter(score -> score.getPos().equals("A3")).findFirst();
            if(a2.isPresent() && a3.isPresent()) if (a2.get().getType() == lastType && a3.get().getType() == lastType) return true;
            //-----
            Optional<Score> b1 = game.getScore().stream().filter(score -> score.getPos().equals("B1")).findFirst();
            Optional<Score> c1 = game.getScore().stream().filter(score -> score.getPos().equals("C1")).findFirst();
            if(b1.isPresent() && c1.isPresent()) if (b1.get().getType() == lastType && c1.get().getType() == lastType) return true;
            //-----
            Optional<Score> b2 = game.getScore().stream().filter(score -> score.getPos().equals("B2")).findFirst();
            Optional<Score> c3 = game.getScore().stream().filter(score -> score.getPos().equals("C3")).findFirst();
            if(b2.isPresent() && c3.isPresent()) if (b2.get().getType() == lastType && c3.get().getType() == lastType) return true;
        }


        return false;
//        if (!game.getFinished()) executeGameTurn(game);
//        else finishGame();
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
