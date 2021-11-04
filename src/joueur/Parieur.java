package joueur;


import saison.Choix;
import saison.Match;
import saison.Pari;

import java.util.ArrayList;

public abstract class Parieur {
    private static final double MONTANTBANKROLL = 237.;
    double bankroll;
    double gain;
    static final double GROSSECOTE = 2.5;
    static final double PETITECOTE = 1.3; // est passée de 1.2 à 1.5 parce que pas assez dans notre jeu de données
    static final double MISEMOYENNE = 11.;

    //private double gainTotal; // pour récupérer tous les gains.
    //gain a la journée
    // gain mensuel
    //gain par match
    // on fait un tableau

    int nombreParisEffectues;
    int nombreParisPerdus;
    int nombreParisGagnes;
    int nbAway;
    int nbHome;
    String idJourneeSansleSou;
    int nbNul;

    public Parieur(){
        this.bankroll = MONTANTBANKROLL;
        this.gain = 0;
        nbAway=0;nbHome=0;
nbNul=0;
        this.nombreParisEffectues = 0;
        this.nombreParisGagnes = 0;
        this.nombreParisPerdus = 0;
        this.idJourneeSansleSou = "";
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
        double coteMoinsUn = cotePondereeTRJ - 1;
        double probEchec = 1 - probReussite;

        return (coteMoinsUn * probReussite - probEchec) / coteMoinsUn;
        // logiquement, si le critère est négatif, on ne devrait pas parier -> la mise correspondra donc à la mise moyenne par joueur
    }

    public abstract Pari parier(Match match);

    public void setBankroll(double montantPari){
        this.bankroll -= montantPari;
    }

    public void bilanPari(){
        // il faut que la bankroll soit mise a jour en fonction du résultat de ses paris et que les gains soient ajoutés aux gains quotidiens
        //récupère pari puis on prend le résultat pour voir s'il a gagné ou perdu

        //si le pari est gagné on ajoute a la bankroll le gain total sur le match
        //on compte le nombre de paris et on incrémente le nombre de victoires

        //s'il a perdu on lui ajoute un gain négatif correspondant à sa mise
        // nbParis - nbVictoires pour compter les défaites
    }

    public void bilanPari(Pari pari, Choix resultat) {
        if (resultat.equals(pari.getEquipe())) {
            System.out.println("Victoire en cours");
            System.out.println(bankroll);
            bankroll += pari.getMise() * pari.getCote();
            System.out.println(bankroll);
            System.out.println(this.getClass().getSimpleName() + " gagnant");
            nombreParisGagnes++;
        } else{
            System.out  .println(this.getClass().getSimpleName() + " perdant");
            nombreParisPerdus++;
        }
        // ajouter les idées de la fct au dessus
    }

    public void bilanSaison(){
        // a termes il faudra aussi renvoyer les données par joueur vers un CSV pour l'analyse ou alors faire le graphe sur java mais :/

        // on affiche le profil du joueur ainsi que son gain total et le nombre de paris effectués et compter le nombre de victoires et de défaites.
        System.out.println("--------------------------------------------------------------------" + this.getClass().getSimpleName() + "--------------------------------------------------------------------");
        System.out.println(this.getClass().getSimpleName() + " a effectué " + nombreParisEffectues + " paris au cours de la saison");
        System.out.println(this.getClass().getSimpleName() + " a gagné " + nombreParisGagnes + " paris");
        System.out.println(this.getClass().getSimpleName() + " a perdu " + nombreParisPerdus + " paris");
        System.out.print(this.getClass().getSimpleName() + " a tenu jusqu'à la ");
        System.out.println("La bankroll est de : " + getBankroll());
        System.out.println("Il a fait " +nbNul + " paris nul");
        System.out.println("Il a fait " +nbHome + " paris home");
        System.out.println("Il a fait " +nbAway + " paris away");
        if(idJourneeSansleSou == ""){
            System.out.print("fin\n");
        }else
            System.out.print(idJourneeSansleSou + "\n");
    }

    public void bilanJournée(){
        // on affiche le profil du joueur, son gain à la journée et le nombre de paris effectués.
        // ajouter les données vers le CSV, on aura donc 1 csv par profil et il est mis a jour chaque jour en fonction 
    }


    public double getBankroll() {
        return bankroll;
    }
}
