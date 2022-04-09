import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private List<GameTurn> turnList;
    private Boolean finished;

    public Game(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.turnList = new ArrayList<>();
        this.finished = false;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<GameTurn> getTurnList() {
        return turnList;
    }

    public void setTurnList(List<GameTurn> turnList) {
        this.turnList = turnList;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }
}
