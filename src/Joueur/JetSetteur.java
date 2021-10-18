package Joueur;

import Saison.Match;
import Saison.Pari;

import java.util.ArrayList;
import java.util.Collections;

public class JetSetteur extends Parieur {

    public JetSetteur(){
        super();
    }

    public Pari parier(Match match){
        ArrayList<Double> cotesMatch = match.getCotes();
        Collections.sort(cotesMatch);
        Double coteChoisie = cotesMatch.get(2); // on récupère la dernière valeur, qui équivaut à la plus grosse

        // il faudrait déterminer à quoi correspond une grosse cote (genre 2.5 minimum) pour que le parieur ne parie que
        // sur les matches qui ont de grosses cotes (et pas seulement la plus grosse de chaque match)

        Pari pari = new Pari();
        // créer constructeur Pari
        // il faudra aussi retrouver à quelle équipe correspond la cote et parier en fonction
        return pari;
    }
}
