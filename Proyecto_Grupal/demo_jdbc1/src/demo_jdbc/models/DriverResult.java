package demo_jdbc.models;

public class DriverResult {
    private String constructorName;
    private int wins;
    private int totalPoints;
    private int rank;

    public DriverResult(String constructorName, int wins, int totalPoints, int rank) {
        this.constructorName = constructorName;
        this.wins = wins;
        this.totalPoints = totalPoints;
        this.rank = rank;
    }

    public String getConstructorName() {
        return constructorName;
    }

    public void setConstructorName(String constructorName) {
        this.constructorName = constructorName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

