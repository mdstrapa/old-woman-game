package main.java.game.model;

public class ScoreRelation {
    private String mainPos;
    private String relatedPos1;
    private String relatedPos2;

    public ScoreRelation(String mainPos, String relatedPos1, String relatedPos2) {
        this.mainPos = mainPos;
        this.relatedPos1 = relatedPos1;
        this.relatedPos2 = relatedPos2;
    }

    public String getMainPos() {
        return mainPos;
    }

    public void setMainPos(String mainPos) {
        this.mainPos = mainPos;
    }

    public String getRelatedPos1() {
        return relatedPos1;
    }

    public void setRelatedPos1(String relatedPos1) {
        this.relatedPos1 = relatedPos1;
    }

    public String getRelatedPos2() {
        return relatedPos2;
    }

    public void setRelatedPos2(String relatedPos2) {
        this.relatedPos2 = relatedPos2;
    }
}
