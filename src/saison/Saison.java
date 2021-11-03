package saison;

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

            // étape 2 : créer des journees à partir des matchs

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public boolean lancerSaison(String cheminCSV, int nombreJournee){
        // 1: on initialise les journees
        initialiserJournees(cheminCSV, nombreJournee);


        return false;
    }
}
