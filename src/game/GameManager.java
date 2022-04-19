package game;

import game.model.*;

import java.util.*;

public class GameManager {

    Scanner userInput = new Scanner(System.in);

    GamePrint gamePrint = new GamePrint();

    private List<ScoreRelation> scoreRelations = new ArrayList<>();


    private void createScoreRelation(){
        //A1
        scoreRelations.add(new ScoreRelation("A1","A2","A3"));
        scoreRelations.add(new ScoreRelation("A1","B1","C1"));
        scoreRelations.add(new ScoreRelation("A1","B2","C3"));
        //A2
        scoreRelations.add(new ScoreRelation("A2","A1","A3"));
        scoreRelations.add(new ScoreRelation("A2","B2","C2"));
        //A3
        scoreRelations.add(new ScoreRelation("A3","A2","A1"));
        scoreRelations.add(new ScoreRelation("A3","B3","C3"));
        scoreRelations.add(new ScoreRelation("A3","B2","C1"));

        //B1
        scoreRelations.add(new ScoreRelation("B1","B2","B3"));
        scoreRelations.add(new ScoreRelation("B1","A1","C1"));
        //B2
        scoreRelations.add(new ScoreRelation("B2","B1","B3"));
        scoreRelations.add(new ScoreRelation("B2","A1","C3"));
        scoreRelations.add(new ScoreRelation("B2","A3","C1"));
        scoreRelations.add(new ScoreRelation("B2","A2","C2"));
        //B3
        scoreRelations.add(new ScoreRelation("B3","B2","B1"));
        scoreRelations.add(new ScoreRelation("B3","A3","C3"));

        //C1
        scoreRelations.add(new ScoreRelation("C1","C2","C3"));
        scoreRelations.add(new ScoreRelation("C1","B1","A1"));
        scoreRelations.add(new ScoreRelation("C1","B2","A3"));
        //C2
        scoreRelations.add(new ScoreRelation("C2","C1","C3"));
        scoreRelations.add(new ScoreRelation("C2","B2","A2"));
        //C3
        scoreRelations.add(new ScoreRelation("C3","C2","C1"));
        scoreRelations.add(new ScoreRelation("C3","B3","A3"));
        scoreRelations.add(new ScoreRelation("C3","B2","A1"));


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
        Player oPlayer = new Player(oPlayerName, PlayerType.O);

        Game game = new Game(xPlayer,oPlayer);
        game.setCurrentPlayer(xPlayer);
        createScoreRelation();
        executeGameTurn(game);
    }

    private void executeGameTurn(Game game){
        gamePrint.showGameStatus(game);
        String turnCoordinates = getTurnCoordinates(game);
        GameTurn gameTurn = new GameTurn(game.getCurrentPlayer(),turnCoordinates.substring(0,1),turnCoordinates.substring(1));
        game.getTurnList().add(gameTurn);
        recordGameTurn(game,gameTurn);
        if (evaluateTurn(game)){
            game.setResult(GameResult.WINNER);
            game.setWinner(game.getCurrentPlayer());
            gamePrint.showWinner(game);
            gamePrint.showGameStatus(game);
            gamePrint.showGameOver(game);
        } else if(game.getTurnList().size() == 9) {
            game.setResult(GameResult.DRAW);
            gamePrint.showGameDraw();
            gamePrint.showGameStatus(game);
            gamePrint.showGameOver(game);
        }else {
            Player newPlayer;
            if (game.getCurrentPlayer().getType() == PlayerType.O) newPlayer = game.getPlayerX();
            else newPlayer = game.getPlayerO();
            game.setCurrentPlayer(newPlayer);
            executeGameTurn(game);

        }
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

        List<ScoreRelation> scoreRelationList = new ArrayList<>();
        for (ScoreRelation scoreRelation:scoreRelations) {
            if(scoreRelation.getMainPos().equals(lastTurn.getColumn() + lastTurn.getLine())) scoreRelationList.add(scoreRelation);
        }

        for (ScoreRelation scoreRelation:scoreRelationList) {
            Optional<Score> rel1 = game.getScore().stream().filter(score -> score.getPos().equals(scoreRelation.getRelatedPos1())).findFirst();
            Optional<Score> rel2 = game.getScore().stream().filter(score -> score.getPos().equals(scoreRelation.getRelatedPos2())).findFirst();
            if(rel1.isPresent() && rel2.isPresent()) if (rel1.get().getType() == lastType && rel2.get().getType() == lastType) return true;
        }

        return false;
    }

    private String getTurnCoordinates(Game game){

        boolean validCoordinates = false;
        String coordinates = "";

        while (!validCoordinates){
            System.out.print("Type the coordinates of your choice:");
            coordinates = userInput.nextLine();
            validCoordinates = evaluateCoordinates(game,coordinates);
            if(!validCoordinates) System.out.println("The coordinates you typed are invalid. Please try it again.");
        }

        return coordinates;
    }

    private boolean evaluateCoordinates(Game game, String userInput){
        if(userInput.length()!=2) return false;
        if(userInput.charAt(0) != 'A' && userInput.charAt(0) != 'B' && userInput.charAt(0) != 'C') return false;
        if(userInput.charAt(1) != '1' && userInput.charAt(1) != '2' && userInput.charAt(1) != '3') return false;
        for (GameTurn turn: game.getTurnList()) {
            if((turn.getColumn() + turn.getLine()).equals(userInput)) return false;
        }
        return true;
    }

}
