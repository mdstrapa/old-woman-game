public class Score {
    private PlayerType type;
    private String pos;

    public Score(String pos) {
        this.pos = pos;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
