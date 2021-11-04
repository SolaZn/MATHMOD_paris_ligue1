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

        // on instancie les profils de joueurs
        JetSetteur jet = new JetSetteur();
        Frileux frileux = new Frileux();
        Chauvin chauvin = new Chauvin("Metz");

        // on les ajoute à la liste des joueurs
        listeJoueurs.add(jet);
        listeJoueurs.add(frileux);
        listeJoueurs.add(chauvin);

        // on crée une nouvelle saison
        Saison saison = new Saison();
        // on lance la saison, avec le CSV des matchs, le nombre de Journées voulues et la liste des joueurs
        saison.lancerSaison("D:\\DUT_IFO2\\cours-s1\\MATHS\\ModelisationsMathematiques\\MATHMOD_saison_LIGUE1\\MATHMOD_paris_ligue1\\jeu_donnees_ligue1_reel.csv", 37, listeJoueurs);
    }
}
