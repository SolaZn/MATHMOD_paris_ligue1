package saison;

import java.util.ArrayList;

public class Journee {
    private int idJournee;
    private ArrayList<Match> matches;

    public Journee(int id){
        this.idJournee = id;
        this.matches = new ArrayList<>();
    }

    public boolean addMatch(Match match){
        if(match.getJournee().equals("J" + this.idJournee)){
            matches.add(match);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Journee{" +
                "idJournee=" + idJournee +
                ", matches=" + matches +
                '}';
    }
}
