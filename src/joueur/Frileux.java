package joueur;

import saison.Match;
import saison.Pari;

import java.util.ArrayList;
import java.util.Collections;

public class Frileux extends Parieur {
    public Frileux(){
        super();
    }

    public Pari parier(Match match){
        ArrayList<Double> cotesMatch = new ArrayList<>();
        cotesMatch.addAll(match.getCotes());

        double TRJPari = calculTRJ(cotesMatch);

        Collections.sort(cotesMatch);
        Double coteChoisie = cotesMatch.get(0); // on récupère la première valeur, qui équivaut à la plus petite

        int MisePari = 10;

        double mise = pariOptimalparKelly(coteChoisie, TRJPari) * bankroll;


        if(coteChoisie > Parieur.PETITECOTE){
            return null;
        }else
            return new Pari(match, mise, match.getChoixbyCote(coteChoisie), this, coteChoisie);
    }
}
