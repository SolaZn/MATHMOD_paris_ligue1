package app;

import joueur.*;
import saison.Saison;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Parieur> listeJoueurs = new ArrayList<>();

        // on instancie les profils de joueurs
        JetSetteur jet = new JetSetteur();
        Frileux frileux = new Frileux();
        Chauvin chauvin = new Chauvin("Lille");
        Fou fou = new Fou();

        // on les ajoute à la liste des joueurs
        listeJoueurs.add(jet);
        listeJoueurs.add(frileux);
        listeJoueurs.add(chauvin);
        listeJoueurs.add(fou);
        // on crée une nouvelle saison
        Saison saison = new Saison();
        // on lance la saison, avec le CSV des matchs, le nombre de Journées voulues et la liste des joueurs
        saison.lancerSaison("src/jeu_donnees_ligue1_reel.csv", 37, listeJoueurs);
    }
}
