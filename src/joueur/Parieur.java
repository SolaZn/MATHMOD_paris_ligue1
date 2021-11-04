package joueur;


import saison.Match;
import saison.Pari;

import java.util.ArrayList;

public abstract class Parieur {
    private static final double MONTANTBANKROLL = 237.0;
    double bankroll;
    double gain;
    static final double GROSSECOTE = 2.5;
    static final double PETITECOTE = 1.3; // est passée de 1.2 à 1.5 parce que pas assez dans notre jeu de données
    //private double gainTotal; // pour récupérer tous les gains.
    //gain a la journée
    // gain mensuel
    //gain par match
    // on fait un tableau
    //compteur paris effectués

    public Parieur(){
        this.bankroll = MONTANTBANKROLL;
        this.gain = 0;
    }

    static double calculTRJ(ArrayList<Double> cotesMatch){
        return 1 / (1 / cotesMatch.get(0) + 1 / cotesMatch.get(1) + 1 / cotesMatch.get(2)) * 100;
    }

    static double pariOptimalparKelly(double coteChoisie, double TRJ){
        // étape 1 : appliquer le TRJ à la cote choisie
        double cotePondereeTRJ = coteChoisie * (TRJ / 100);
        // étape 2 : calculer la probabilité de réussite de la cote
        double probReussite = 1/cotePondereeTRJ;
        // étape 3 : calculer le Critère de Kelly pour la cote spécifiée
        return ((cotePondereeTRJ - 1) * probReussite)  - (1 - probReussite) / (cotePondereeTRJ - 1);
        // logiquement, si le critère est négatif, on ne devrait pas parier -> la mise correspondra donc à la mise moyenne par joueur
    }

    public abstract Pari parier(Match match);

    public void bilanPari(){
        // il faut que la bankroll soit mise a jour en fonction du résultat de ses paris et que les gains soient ajoutés aux gains quotidiens


        //récupère pari puis on prend le résultat pour voir s'il a gagné ou perdu

        //si le pari est gagné on ajoute a la bankroll le gain total sur le match
        //on compte le nombre de paris et on incrémente le nombre de victoires

        //s'il a perdu on lui ajoute un gain négatif correspondant à sa mise
        // nbParis - nbVictoires pour compter les défaites



    }

    public void bilanSaison(){
        // a termes il faudra aussi renvoyer les données par joueur vers un CSV pour l'analyse ou alors faire le graphe sur java mais :/

        // on affiche le profil du joueur ainsi que son gain total et le nombre de paris effectués et compter le nombre de victoires et de défaites.

    }

    public void bilanJournée(){
        // on affiche le profil du joueur, son gain à la journée et le nombre de paris effectués.
        // ajouter les données vers le CSV, on aura donc 1 csv par profil et il est mis a jour chaque jour en fonction 
    }




}
