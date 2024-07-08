package models;

public class AjukanFunding {
    private String title;
    private String teamName;
    private String background;
    private String teamMembers;
    private String objectives;
    private String budget;
    private String likes;

    public AjukanFunding(String title, String teamName, String background, String teamMembers, String objectives, String budget, String likes) {
        this.title = title;
        this.teamName = teamName;
        this.background = background;
        this.teamMembers = teamMembers;
        this.objectives = objectives;
        this.budget = budget;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getBackground() {
        return background;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public String getObjectives() {
        return objectives;
    }

    public String getBudget() {
        return budget;
    }

    public String getLikes() {
        return likes;
    }
}
