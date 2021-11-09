package joueur;


import saison.Choix;
import saison.Match;
import saison.Pari;

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Parieur {

    double bankroll;
    double gainBrut;
    double gainMatch;
    double totalMises;

    double evolutionCapital;
    double bénéfice;
    double ROI;

    static final DecimalFormat nombre = new DecimalFormat("###.##");
    static final double GROSSECOTE = 2.5;
    static final double PETITECOTE = 1.3; // est passée de 1.2 à 1.5 parce que pas assez dans notre jeu de données
    static final double MISEMOYENNE = 8.77 ; // a reculé à 8.77 euros pour correspondre à peu près à la dépense trimestrielle d'un parieur : 60-80 euros
    static final double DECOUVERTAUTORISE = -237;
    static final double DECOUVERTMARTINGALE = -948; // 237 * 4 pour compenser leur plus grande prise de risque
    static final double MONTANTBANKROLL = 237.0;

    int nbEff;
    int nbDef;
    int nbVic;
    int nbAway;
    int nbHome;
    int nbNul;

    String idJourFin;

    public Parieur(){
        this.bankroll = MONTANTBANKROLL;
        this.gainBrut = 0;
        this.gainMatch = 0;
        this.evolutionCapital = 0;
        this.bénéfice = 0;
        this.ROI = 0;
        this.totalMises = 0;


        nbAway=0;
        nbHome=0;
        nbNul=0;

        this.nbEff = 0;
        this.nbVic = 0;
        this.nbDef = 0;
        this.idJourFin = "";
    }

    public void resetParieur(){
        this.bankroll = MONTANTBANKROLL;
        this.gainBrut = 0;
        this.gainMatch = 0;
        this.evolutionCapital = 0;
        this.bénéfice = 0;
        this.ROI = 0;
        this.totalMises = 0;

        nbAway=0;
        nbHome=0;
        nbNul=0;

        this.nbEff = 0;
        this.nbVic = 0;
        this.nbDef = 0;

        this.idJourFin = "";
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
        totalMises += pari.getMise();
        if(pari.getEquipe().equals(Choix.H)){
            nbHome++;
        }else if(pari.getEquipe().equals(Choix.D)){
            nbNul++;
        }else if(pari.getEquipe().equals(Choix.A)){
            nbAway++;
        }

        if (resultat.equals(pari.getEquipe())) {/*
            System.out.println("Victoire en cours");
            System.out.println(bankroll);*/

            gainBrut += pari.getMise() * pari.getCote();
            bankroll += pari.getMise() * pari.getCote();

            /*System.out.println(bankroll);
            System.out.println(this.getClass().getSimpleName() + " gagnant");*/
            nbVic++;
            gainMatch = pari.getMise();
        } else{
            // System.out.println(this.getClass().getSimpleName() + " perdant");
            nbDef++;
        }
    }

    public void bilanSaison(){
        // a termes il faudra aussi renvoyer les données par joueur vers un CSV pour l'analyse ou alors faire le graphe sur java mais :/

        // on affiche le profil du joueur ainsi que son gain total et le nombre de paris effectués et compter le nombre de victoires et de défaites.
        System.out.println("--------------------------------------------------------------------" + this.getClass().getSimpleName());
        System.out.print(this.getClass().getSimpleName() + " a tenu sa stratégie jusqu'à la " );
        if(idJourFin == ""){
            System.out.print("fin\n");
        }else {
            System.out.print(idJourFin + "\n");
            if(this.getClass().getSimpleName().equals("Crack") || this.getClass().getSimpleName().equals("Professor") ||
                    this.getClass().getSimpleName().equals("Technicien")){
                System.out.println("(NDLR: martingale écourtée par manque d'argent pour rattraper les pertes)");
            }else{
                System.out.println("(NDLR: stratégie écourtée par manque d'argent pour jouer)");
            }
        }

        // calculs des variables ROI et ROC
        bénéfice = gainBrut - totalMises;
        evolutionCapital = (bénéfice / MONTANTBANKROLL) * 100;
        ROI = (bénéfice / totalMises) * 100;

        System.out.println("PARIS (1N2): 1|" + nbHome + "; N|" + nbNul + "; 2|" + nbAway);
        System.out.println("STATS : GAGNE|" + nbVic + "; PERDU|" + nbDef + "; EFFECTUE|" + nbEff);
        System.out.println("Le capital du joueur (ou ROC) a évolué de " + nombre.format(evolutionCapital) + "% !");
        System.out.println("Le retour sur investissement (ou ROI/yield) est de " + nombre.format(ROI) + "% !");

        System.out.println("La bankroll est de : " + nombre.format(getBankroll()) + " euros" );
        System.out.println("Il a généré un gain brut de : " + nombre.format(getGainBrut()) + " euros");
        System.out.println("... sur un montant total misé de : " + nombre.format(getTotalMises()) + " euros");

        // compter le nombre de fois que le ROI dépasse 5% sur plusieurs saisons pour voir quel est le profil le plus intéressant
        // https://www.comment-gagner-paris-sportifs.fr/le-roi-dans-les-paris-sportifs/

        // simuler plusieurs saisons d'un coup
    }

    private double getTotalMises() {
        return totalMises;
    }

    private double getGainBrut() {
        return gainBrut;
    }

    public void bilanJournée(){
        // on affiche le profil du joueur, son gain à la journée et le nombre de paris effectués.
        // ajouter les données vers le CSV, on aura donc 1 csv par profil et il est mis a jour chaque jour en fonction 
    }


    public double getBankroll() {
        return bankroll;
    }
}
