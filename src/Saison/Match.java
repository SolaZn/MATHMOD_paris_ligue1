package Saison;

import java.util.ArrayList;

public class Match {
    private int idMatch;
    private double coteH, coteD, coteA;
    private Resultat resultat;

    public Match(int idMatch, ArrayList<Double> cotesMatch, Resultat resultat){
        this.idMatch = idMatch;
        this.coteH = cotesMatch.get(0);
        this.coteD = cotesMatch.get(1);
        this.coteA = cotesMatch.get(2);
        this.resultat = resultat;
    }

    public Match getMatch(){
        return this;
    }

    public ArrayList<Double> getCotes(){
        ArrayList<Double> cotesMatch = new ArrayList<>();
        cotesMatch.add(coteH);
        cotesMatch.add(coteD);
        cotesMatch.add(coteA);

        return cotesMatch;
    }


}
