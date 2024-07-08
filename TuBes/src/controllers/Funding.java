package controllers;

public class Funding {

    private String title;
    private String teamName;
    private String budget;
    private int likes;

    public Funding(String title, String teamName, String budget, int likes) {
        this.title = title;
        this.teamName = teamName;
        this.budget = budget;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
