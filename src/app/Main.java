package app;

import joueur.Chauvin;
import joueur.Frileux;
import joueur.JetSetteur;
import joueur.Parieur;
import saison.Saison;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Parieur> listeJoueurs = new ArrayList<>();
        JetSetteur jet = new JetSetteur();
        Frileux frileux = new Frileux();
        Chauvin chauvin = new Chauvin("Paris SG");

        listeJoueurs.add(jet);
        listeJoueurs.add(frileux);
        listeJoueurs.add(chauvin);

        Saison saison = new Saison();
        saison.lancerSaison("D:\\DUT_IFO2\\cours-s1\\MATHS\\ModelisationsMathematiques\\MATHMOD_saison_LIGUE1\\MATHMOD_paris_ligue1\\jeu_donnees_ligue1_reel.csv", 37, listeJoueurs);
    }
}
