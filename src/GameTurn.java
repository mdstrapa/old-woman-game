public class GameTurn {
    private Player player;
    private String column;
    private String line;

    public GameTurn(Player player, String column, String line) {
        this.player = player;
        this.column = column;
        this.line = line;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerType(Player player) {
        this.player = player;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
