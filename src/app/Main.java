package app;

import joueur.Frileux;
import joueur.JetSetteur;
import saison.Choix;
import saison.Match;
import saison.Pari;
import saison.Saison;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        JetSetteur jet = new JetSetteur();
        Frileux frileux = new Frileux();

        ArrayList<Double> cotes = new ArrayList<>();
        cotes.add(2.);
        cotes.add(2.6);
        cotes.add(1.2);
        /*
        Match match = new Match(1,cotes, Choix.A);

        System.out.println(match + " gagnant : " + Choix.A);

        Pari pari = jet.parier(match);
        Pari pari2 = frileux.parier(match);*/

        Saison saison = new Saison();
        saison.lancerSaison("D:\\DUT_IFO2\\cours-s1\\MATHS\\ModelisationsMathematiques\\MATHMOD_saison_LIGUE1\\MATHMOD_paris_ligue1\\jeu_donnees_ligue1_reel.csv", 1);
    }
}
