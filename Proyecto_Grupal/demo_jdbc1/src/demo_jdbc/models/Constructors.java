package demo_jdbc.models;

public class Constructors {
    private String name;
    private int wins;
    private int totalPoints;
    private int rank;

    public Constructors(String name, int wins, int totalPoints, int rank) {
        this.name = name;
        this.wins = wins;
        this.totalPoints = totalPoints;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getRank() {
        return rank;
    }
}
