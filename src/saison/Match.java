package saison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Match {
    private int idMatch;
    private ArrayList<Double> cotesMAX;
    private ArrayList<Double> cotesAVG;
    private String journee;
    private String date;
    private String[] equipes;
    private Choix resultat;
    private ArrayList<Pari> listeParis;

    public Match(int idMatch, String Journee, String date, String[] equipes, ArrayList<Double> cotesMAX, ArrayList<Double> cotesAVG, Choix resultat){
        this.idMatch = idMatch;

        this.journee = Journee;
        this.date = date;
        this.equipes = equipes;

        this.cotesMAX = new ArrayList<>();
        this.cotesAVG = new ArrayList<>();
        this.listeParis = new ArrayList<>();

        this.cotesMAX.addAll(cotesMAX);
        this.cotesAVG.addAll(cotesAVG);

        this.resultat = resultat;
    }

    public void resetMatch(){
         listeParis = new ArrayList<>();
         Collections.shuffle(cotesAVG);
    }

    public Match getMatch(){
        return this;
    }

    public ArrayList<Double> getCotes(){
        // pour des raisons pratiques, on exploitera que les cotesAVG, √† part √©ventuellement pour le profil Technicien qui
        // a besoin d'un large choix de cotes
        return cotesAVG;
    }

    public Choix getChoixbyCote(double coteChoisie){
        if(cotesAVG.get(0) == coteChoisie){
            return Choix.H;
        }else if (cotesAVG.get(1) == coteChoisie){
            return Choix.D;
        }else if (cotesAVG.get(2) == coteChoisie){
            return Choix.A;
        }
        return null;
    }

    public String getJournee() {
        return journee;
    }

    @Override
    public String toString() {
        return "Match{" +
                "idMatch=" + idMatch +
                ", cotesMAX=" + cotesMAX +
                ", cotesAVG=" + cotesAVG +
                ", journee='" + journee + '\'' +
                ", date='" + date + '\'' +
                ", equipes=" + Arrays.toString(equipes) +
                ", resultat=" + resultat +
                '}';
    }

    public String getDescMatch() {
        return this.equipes[0] + " contre " + this.equipes[1] + " le " + date;
    }

    public String[] getEquipes() {
        return equipes;
    }

    public ArrayList<Pari> getParis() {
        return this.listeParis;
    }

    public void bilanParis() {
        for (Pari pari : listeParis) {
            pari.getParieur().bilanPari(pari, this.resultat);
        }
    }
}
