package saison;

import java.util.ArrayList;

public class Match {
    private int idMatch;
    private ArrayList<Double> cotes;
    private Choix resultat;

    public Match(int idMatch, ArrayList<Double> cotesMatch, Choix resultat){
        this.idMatch = idMatch;
        cotes = new ArrayList<>();
        cotes.addAll(cotesMatch);
        this.resultat = resultat;
    }

    public Match getMatch(){
        return this;
    }

    public ArrayList<Double> getCotes(){
        return cotes;
    }

    public Choix getChoixbyCote(double coteChoisie){
        if(cotes.get(0) == coteChoisie){
            return Choix.H;
        }else if (cotes.get(1) == coteChoisie){
            return Choix.D;
        }else if (cotes.get(2) == coteChoisie){
            return Choix.A;
        }

        return Choix.H;
    }


}
