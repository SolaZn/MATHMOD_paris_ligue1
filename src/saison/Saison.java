package saison;

import joueur.Parieur;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Saison {
    private ArrayList<Journee> journees;

    public Saison(){
        this.journees = new ArrayList<>();  
    }

    // fonctions d'initalisation de la simulation
    private void initialiserJournees(String cheminCSV, int nombreJournee) {
        int nombreMatchs = 0;
        ArrayList<Match> listeMatchs = new ArrayList<>();

        try{
            File fichierCSVsaison = new File(cheminCSV);
            Scanner sc = new Scanner(fichierCSVsaison);
            sc.nextLine();

            System.out.println("Importation des matchs en cours depuis \n" + cheminCSV);

            while(sc.hasNextLine()){
                nombreMatchs++;
                String[] match = sc.nextLine().split(";");

                ArrayList<Double> cotesMAX = new ArrayList<>();
                ArrayList<Double> cotesAVG = new ArrayList<>();

                cotesMAX.add(Double.valueOf(match[5]));
                cotesMAX.add(Double.valueOf(match[6]));
                cotesMAX.add(Double.valueOf(match[7]));

                cotesAVG.add(Double.valueOf(match[8]));
                cotesAVG.add(Double.valueOf(match[9]));
                cotesAVG.add(Double.valueOf(match[10]));

                Match matchLu = new Match(nombreMatchs, match[0], match[1], new String[]{match[2], match[3]}, cotesMAX, cotesAVG, Choix.bind(match[4]));
                listeMatchs.add(matchLu);
            }
            sc.close();

            System.out.println("Importation des matchs terminée");
            System.out.println("Création des journées en cours");


            // étape 2 : créer des journees à partir des matchs
            for (int i = 1; i <= nombreJournee; i++) {
                Journee J = new Journee(i);
                for(int y = 0; y < listeMatchs.size(); y++){
                    Match match = listeMatchs.get(y);
                    if (match.getJournee().equals("J" + i)){
                        J.addMatch(match);
                        listeMatchs.remove(match);
                        y -= 1; // on décale la recherche pour éviter de sauter le match qui a repris l'id du supprimé
                    }
                }
                System.out.println(J);
                journees.add(J);
            }

            System.out.println("Création des journées terminée");


        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    // fonction de simulation de la saison
    private void simulerSaison(Journee journee, ArrayList<Parieur> listeJoueurs) {
        for(Match match : journee.getMatchs()){
            for (Parieur parieur : listeJoueurs) {
                // 1 : on donne chaque match au parieur pour qu'il parie
                Pari pariEffectue = parieur.parier(match);
                System.out.println(pariEffectue);

                // 2 : s'il parie, on enregistre son pari dans la liste des paris du match
                if(pariEffectue != null)
                    match.getParis().add(pariEffectue);
            }
            // 3 : une fois que tous les parieurs ont parié, on fait le bilan de leur performance
            // en fonction du résultat du match
            match.bilanParis();
        }
    }

    // boucle principale
    public boolean lancerSaison(String cheminCSV, int nombreJournee, ArrayList<Parieur> listeJoueurs){
        // 1: on initialise les journees
        initialiserJournees(cheminCSV, nombreJournee);

        // 2: on lance la simulation
        for(Journee journee : journees) {
            simulerSaison(journee, listeJoueurs);
        }

        // 3 : on fait le bilan de la saison
        for(Parieur parieur : listeJoueurs){
            parieur.bilanSaison();
        }

        return false;
    }
}
