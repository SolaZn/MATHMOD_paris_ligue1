package saison;

import java.util.ArrayList;

public class Journee {
    private String nom;
    private ArrayList<Match> matches;

    public Journee(String nom){
        this.nom = nom;
        this.matches = new ArrayList<>();
    }

    public boolean addMatch(Match match){
        if(match.getJournee().equals(this.nom)){
            matches.add(match);
            return true;
        }
        return false;
    }
}
