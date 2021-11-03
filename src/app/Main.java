package app;

import joueur.Frileux;
import joueur.JetSetteur;
import saison.Choix;
import saison.Match;
import saison.Pari;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        JetSetteur jet = new JetSetteur();
        Frileux frileux = new Frileux();

        ArrayList<Double> cotes = new ArrayList<>();
        cotes.add(2.);
        cotes.add(2.6);
        cotes.add(1.2);
        Match match = new Match(1,cotes, Choix.A);

        System.out.println(match + " gagnant : " + Choix.A);

        Pari pari = jet.parier(match);
        Pari pari2 = frileux.parier(match);

    }
}
